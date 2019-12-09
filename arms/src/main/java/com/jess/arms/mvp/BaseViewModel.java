/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jess.arms.mvp;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ComponentActivity;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;

import com.jess.arms.base.BaseVMActivity;
import com.jess.arms.integration.EventBusManager;
import com.jess.arms.integration.SingleLiveEvent;
import com.jess.arms.utils.Preconditions;
import com.trello.rxlifecycle2.RxLifecycle;

import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * ================================================
 * 基类 Presenter
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.4">Presenter wiki 官方文档</a>
 * Created by JessYan on 4/28/2016
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class BaseViewModel<M extends IModel> extends AndroidViewModel implements IViewModel {
    protected final String TAG = this.getClass().getSimpleName();
    protected CompositeDisposable mCompositeDisposable;
    protected M mModel;
    private UIChangeLiveData uiChangeLiveData;

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model
     */
    public BaseViewModel(@NonNull Application application, M model) {
        super(application);
        Preconditions.checkNotNull(model, "%s cannot be null", IModel.class.getName());
        this.mModel = model;
        onStart();

    }

    /**
     * 如果当前页面不需要操作数据,只需要 View 层,则使用此构造函数
     */
    public BaseViewModel(@NonNull Application application) {
        super(application);
        onStart();
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
    //将 LifecycleObserver 注册给 LifecycleOwner 后 @OnLifecycleEvent 才可以正常使用

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (useEventBus())//如果要使用 EventBus 请将此方法返回 true
            EventBusManager.getInstance().unregister(this);//注销 EventBus
        unDispose();//解除订阅
        if (mModel != null) {
            mModel.onDestroy();
        }
        this.mModel = null;
        this.mCompositeDisposable = null;
    }


    /**
     * 只有当 {@code mRootView} 不为 null, 并且 {@code mRootView} 实现了 {@link LifecycleOwner} 时, 此方法才会被调用
     * 所以当您想在 {@link Service} 以及一些自定义 {@link View} 或自定义类中使用 {@code Presenter} 时
     * 您也将不能继续使用 {@link OnLifecycleEvent} 绑定生命周期
     *
     * @param owner link {@link ComponentActivity} and {@link Fragment}
     */
//    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//    void onDestroy(LifecycleOwner owner) {
//        /**
//         * 注意, 如果在这里调用了 {@link #onDestroy()} 方法, 会出现某些地方引用 {@code mModel} 或 {@code mRootView} 为 null 的情况
//         * 比如在 {@link RxLifecycle} 终止 {@link Observable} 时, 在 {@link io.reactivex.Observable#doFinally(Action)} 中却引用了 {@code mRootView} 做一些释放资源的操作, 此时会空指针
//         * 或者如果你声明了多个 @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY) 时在其他 @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//         * 中引用了 {@code mModel} 或 {@code mRootView} 也可能会出现此情况
//         */
//        owner.getLifecycle().removeObserver(this);
//    }

    /**
     * 是否使用 EventBus
     * Arms 核心库现在并不会依赖某个 EventBus, 要想使用 EventBus, 还请在项目中自行依赖对应的 EventBus
     * 现在支持两种 EventBus, greenrobot 的 EventBus 和畅销书 《Android源码设计模式解析与实战》的作者 何红辉 所作的 AndroidEventBus
     * 确保依赖后, 将此方法返回 true, Arms 会自动检测您依赖的 EventBus, 并自动注册
     * 这种做法可以让使用者有自行选择三方库的权利, 并且还可以减轻 Arms 的体积
     *
     * @return 返回 {@code true} (默认为使用 {@code true}), Arms 会自动注册 EventBus
     */
    public boolean useEventBus() {
        return true;
    }

    /**
     * 将 {@link Disposable} 添加到 {@link CompositeDisposable} 中统一管理
     * 可在 {@link #onDestroy()} 中使用 {@link #unDispose()} 停止正在执行的 RxJava 任务,避免内存泄漏
     * 目前框架已使用 {@link RxLifecycle} 避免内存泄漏,此方法作为备用方案
     *
     * @param disposable
     */
    public void addDispose(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);//将所有 Disposable 放入容器集中处理
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public <VM extends BaseViewModel> void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//保证 Activity 结束时取消所有正在执行的订阅
        }
    }


    public void injectLifecycle(Lifecycle lifecycle) {
        if (lifecycle != null) {
            lifecycle.addObserver((LifecycleObserver) mModel);
            if (useEventBus())//如果要使用 EventBus 请将此方法返回 true
                EventBusManager.getInstance().register(this);//注册 EventBus
        }
    }

    public final class UIChangeLiveData extends SingleLiveEvent {
        private SingleLiveEvent<String> showDialogEvent;
        private SingleLiveEvent<Void> dismissDialogEvent;
        private SingleLiveEvent<Void> hideRefreshEvent;
        private SingleLiveEvent<Void> hideLoadMoreEvent;
        private SingleLiveEvent<Map<String, Object>> startActivityEvent;
        private SingleLiveEvent<Void> finishEvent;
        private SingleLiveEvent<Void> onBackPressedEvent;
        public SingleLiveEvent<String> getShowDialogEvent() {
            return showDialogEvent = createLiveData(showDialogEvent);
        }

        public SingleLiveEvent<Void> getDismissDialogEvent() {
            return dismissDialogEvent = createLiveData(dismissDialogEvent);
        }

        public SingleLiveEvent<Map<String, Object>> getStartActivityEvent() {
            return startActivityEvent = createLiveData(startActivityEvent);
        }

        public SingleLiveEvent<Void> getFinishEvent() {
            return finishEvent = createLiveData(finishEvent);
        }

        public SingleLiveEvent<Void> getOnBackPressedEvent() {
            return onBackPressedEvent = createLiveData(onBackPressedEvent);
        }
        private <T> SingleLiveEvent<T> createLiveData(SingleLiveEvent<T> liveData) {
            if (liveData == null) {
                liveData = new SingleLiveEvent<>();
            }
            return liveData;
        }
        @Override
        public void observe(LifecycleOwner owner, Observer observer) {
            super.observe(owner, observer);
        }
    }

    public UIChangeLiveData getUiChangeLiveData() {
        if(uiChangeLiveData==null) {
            uiChangeLiveData=new UIChangeLiveData();
        }
        return uiChangeLiveData;
    }
}
