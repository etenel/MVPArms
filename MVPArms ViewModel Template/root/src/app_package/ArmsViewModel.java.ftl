package ${viewModelPackageName};

import android.app.Application;

import com.jess.arms.integration.AppManager;
<#if needActivity && needFragment>
import com.jess.arms.di.scope.ActivityScope;
<#elseif needActivity>
import com.jess.arms.di.scope.ActivityScope;
<#elseif needFragment>
import com.jess.arms.di.scope.FragmentScope;
</#if>
import com.jess.arms.mvp.BaseViewModel;
import com.jess.arms.http.imageloader.ImageLoader;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import javax.inject.Inject;

import ${contractPackageName}.${pageName}Contract;

<#import "root://activities/MVPArmsTemplate/globals.xml.ftl" as gb>

<@gb.fileHeader />
<#if needActivity && needFragment>
@ActivityScope
<#elseif needActivity>
@ActivityScope
<#elseif needFragment>
@FragmentScope
</#if>
public class ${pageName}ViewModel extends BaseViewModel<${pageName}Contract.Model> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ${pageName}ViewModel ( Application application,${pageName}Contract.Model model) {
        super(application,model);
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
