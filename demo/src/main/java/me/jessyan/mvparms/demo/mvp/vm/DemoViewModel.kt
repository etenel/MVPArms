package me.jessyan.mvparms.demo.mvp.vm

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BaseViewModel
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import me.jessyan.mvparms.demo.mvp.contract.DemoContract


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/09/2019 09:31
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class DemoViewModel
@Inject
constructor(application: Application, model: DemoContract.Model) :
        BaseViewModel<DemoContract.Model>(application, model) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager
    var name: MutableLiveData<String> = MutableLiveData()
    override fun onCreate() {
        super.onCreate()
        name.value = "mvvm"
    }



    override fun onCleared() {
        super.onCleared()
    }
}
