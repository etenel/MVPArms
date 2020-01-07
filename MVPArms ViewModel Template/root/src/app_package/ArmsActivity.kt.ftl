package ${ativityPackageName}

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseVMActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import ${componentPackageName}.Dagger${pageName}Component
import ${moudlePackageName}.${pageName}Module
import ${contractPackageName}.${pageName}Contract
import ${viewModelPackageName}.${pageName}ViewModel
import ${packageName}.databinding.Activity${pageName}Binding;
import ${packageName}.R
import ${packageName}.BR


<#import "root://activities/MVPArmsTemplate/globals.xml.ftl" as gb>

<@gb.fileHeader />
/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class ${pageName}Activity : BaseVMActivity<Activity${pageName}Binding,${pageName}ViewModel>() , ${pageName}Contract.View {

    override fun setupActivityComponent(appComponent:AppComponent) {
        Dagger${pageName}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${extractLetters(pageName[0]?lower_case)}${pageName?substring(1,pageName?length)}Module(${pageName}Module(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState:Bundle?):Int {
              return R.layout.${activityLayoutName} //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }



    override fun initVariableId():Int {
    return BR.vm
    }
    override fun initData(savedInstanceState:Bundle?) {

    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message:String) {
        ArmsUtils.snackbarText(message)
    }
    override fun launchActivity(intent:Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }
}