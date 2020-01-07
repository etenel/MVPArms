package ${ativityPackageName};

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.base.BaseVMActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import ${componentPackageName}.Dagger${pageName}Component;
import ${contractPackageName}.${pageName}Contract;
import ${viewModelPackageName}.${pageName}ViewModel;

import ${packageName}.databinding.Activity${pageName}Binding;
import ${packageName}.R;
import ${packageName}.BR;


import static com.jess.arms.utils.Preconditions.checkNotNull;

<#import "root://activities/MVPArmsTemplate/globals.xml.ftl" as gb>

<@gb.fileHeader />
public class ${pageName}Activity extends BaseVMActivity<Activity${pageName}Binding,${pageName}ViewModel> implements ${pageName}Contract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        Dagger${pageName}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.${activityLayoutName}; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }
    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    @Override
    public  int initVariableId(){
        return BR.vm;
    }
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}
