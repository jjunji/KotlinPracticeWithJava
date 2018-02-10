package com.lazysoul.kotlinwithandroid.injection.components

/**
 * Created by jhjun on 2018-02-10.
 */
interface BaseMvpPresenter<T: BaseMvpView> {
    fun attachView(view: T)
    fun destroy()
}