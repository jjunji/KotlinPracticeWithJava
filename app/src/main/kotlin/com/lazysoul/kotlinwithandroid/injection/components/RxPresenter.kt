package com.lazysoul.kotlinwithandroid.injection.components

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by jhjun on 2018-02-10.
 */
open class RxPresenter {

    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    fun dispose(){
        // kotlin 스럽게 분기하고 싶어요
        disposables.apply {
            if (!this.isDisposed){
                dispose()
            }
        }
    }

    fun add(disposable: Disposable){
        disposables.add(disposable)
    }

}