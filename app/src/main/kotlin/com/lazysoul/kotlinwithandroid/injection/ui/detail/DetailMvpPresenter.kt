package com.lazysoul.kotlinwithandroid.injection.ui.detail

import android.content.Intent
import com.lazysoul.kotlinwithandroid.injection.components.BaseMvpPresenter
import com.lazysoul.kotlinwithandroid.injection.components.BaseMvpView

/**
 * Created by jhjun on 2018-02-10.
 */
interface DetailMvpPresenter<MvpView: BaseMvpView>: BaseMvpPresenter<MvpView> {
    fun loadTodo(intent: Intent)
    fun onTextChanged(s: String)
    fun isFixed(): Boolean
    fun saveTodo(txt: String)

}