package com.lazysoul.kotlinwithandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lazysoul.kotlinwithandroid.injection.components.ActivityComponent
import com.lazysoul.kotlinwithandroid.injection.components.ApplicationComponent
import com.lazysoul.kotlinwithandroid.injection.components.DaggerActivityComponent
import com.lazysoul.kotlinwithandroid.injection.module.ActivityModule

abstract class BaseActivity : AppCompatActivity() {

    lateinit var component: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = DaggerActivityComponent
                .builder()
                .applicationComponent(getAppComponent())
                .activityModule(ActivityModule(this))
                .build()
    }

    fun getAppComponent(): ApplicationComponent {
        return (application as KotlinWithAndroid).component
    }
}
