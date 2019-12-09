package me.jessyan.mvparms.demo.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View

import com.jess.arms.base.BaseVMActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.LogUtils
import kotlinx.android.synthetic.main.activity_demo.*

import me.jessyan.mvparms.demo.di.component.DaggerDemoComponent
import me.jessyan.mvparms.demo.di.module.DemoModule
import me.jessyan.mvparms.demo.mvp.contract.DemoContract
import me.jessyan.mvparms.demo.mvp.vm.DemoViewModel
import me.jessyan.mvparms.demo.databinding.ActivityDemoBinding;
import me.jessyan.mvparms.demo.R
import me.jessyan.mvparms.demo.BR


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
class DemoActivity : BaseVMActivity<ActivityDemoBinding, DemoViewModel>(), DemoContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerDemoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .demoModule(DemoModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_demo //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initVariableId(): Int {
        return BR.vm
    }

    override fun initData(savedInstanceState: Bundle?) {

    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }

    fun textClick(view: View) {
        tv_name.text="change"
        LogUtils.debugInfo(mViewModel?.name?.value)
    }
}
