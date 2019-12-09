package me.jessyan.mvparms.demo.mvp.vm;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BaseViewModel;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.LogUtils;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import me.jessyan.mvparms.demo.mvp.contract.MainContract;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/07/2019 16:11
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class MainViewModel extends BaseViewModel<MainContract.Model> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MainViewModel(Application application, MainContract.Model model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.debugInfo("create1");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.debugInfo("start1");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.debugInfo("resume1");
    }

    @Override
    public void onCleared() {
        super.onCleared();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
