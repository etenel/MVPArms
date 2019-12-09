package me.jessyan.mvparms.demo.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import me.jessyan.mvparms.demo.di.module.DemoModule

import com.jess.arms.di.scope.ActivityScope
import me.jessyan.mvparms.demo.mvp.ui.activity.DemoActivity


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
@Component(modules = arrayOf(DemoModule::class), dependencies = arrayOf(AppComponent::class))
interface DemoComponent {
    fun inject(activity: DemoActivity)
}
