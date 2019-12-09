package me.jessyan.mvparms.demo.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import me.jessyan.mvparms.demo.mvp.contract.DemoContract
import me.jessyan.mvparms.demo.mvp.model.DemoModel


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
@Module
//构建DemoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class DemoModule(private val view: DemoContract.View) {
    @ActivityScope
    @Provides
    fun provideDemoView(): DemoContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideDemoModel(model: DemoModel): DemoContract.Model {
        return model
    }
}
