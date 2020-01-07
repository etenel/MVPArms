<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    <#if needActivity>
    tools:context=".mvp.ui.activity.${pageName}Activity"
    </#if> >

    <data>
        <variable
            name="vm"
             type="${viewModelPackageName}.${pageName}ViewModel" />
    </data>
    <LinearLayout 
              android:orientation="vertical"
              android:layout_width="match_parent"
              android:layout_height="match_parent">

    </LinearLayout>
</layout>