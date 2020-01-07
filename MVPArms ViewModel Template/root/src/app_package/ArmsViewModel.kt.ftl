package ${viewModelPackageName}

import android.app.Application

import com.jess.arms.integration.AppManager
<#if needActivity && needFragment>
import com.jess.arms.di.scope.ActivityScope
<#elseif needActivity>
import com.jess.arms.di.scope.ActivityScope
<#elseif needFragment>
import com.jess.arms.di.scope.FragmentScope
</#if>
import com.jess.arms.mvp.BaseViewModel
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import ${contractPackageName}.${pageName}Contract

<#import "root://activities/MVPArmsTemplate/globals.xml.ftl" as gb>

<@gb.fileHeader />
<#if needActivity && needFragment>
@ActivityScope
<#elseif needActivity>
@ActivityScope
<#elseif needFragment>
@FragmentScope
</#if>
class ${pageName}ViewModel
@Inject
constructor(application: Application,model: ${pageName}Contract.Model) :
BaseViewModel<${pageName}Contract.Model>(application,model) {
    @Inject
    lateinit var mErrorHandler:RxErrorHandler
    @Inject
    lateinit var mApplication:Application
    @Inject
    lateinit var mImageLoader:ImageLoader
    @Inject
    lateinit var mAppManager:AppManager


    override fun onCleared() {
          super.onCleared()
    }
}
