package com.lazysoul.kotlinwithandroid.injection.ui.detail

import com.lazysoul.kotlinwithandroid.injection.components.BaseMvpView
import com.lazysoul.kotlinwithandroid.injection.datas.Todo

/**
 * Created by jhjun on 2018-02-10.
 */
interface DetailMvpView: BaseMvpView {
    fun onUpdated(todo: Todo, editable: Boolean)
    fun onChangedSaveBt()
    fun onSaved(requestType: Int, todo: Todo)
}