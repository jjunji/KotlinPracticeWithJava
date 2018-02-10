package com.lazysoul.kotlinwithandroid.injection.ui.detail

import android.content.Intent
import com.lazysoul.kotlinwithandroid.injection.components.BaseMvpView
import com.lazysoul.kotlinwithandroid.injection.components.RxPresenter
import com.lazysoul.kotlinwithandroid.injection.datas.Todo
import com.lazysoul.kotlinwithandroid.injection.singletons.TodoManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

/**
 * Created by jhjun on 2018-02-10.
 */
class DetailMvpPresentImpl<Mvpview: BaseMvpView>: RxPresenter(), DetailMvpPresenter<Mvpview> {
    lateinit var view: DetailMvpView
    lateinit var beforeTodo: Todo
    private val txtChangeSubject: PublishSubject<Boolean> = PublishSubject.create()
    private var requestType: Int = -1

    init {
        add(txtChangeSubject
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {isChanged ->
                    beforeTodo.isFixed = isChanged
                    view.onChangedSaveBt()
                }
        )
    }

    override fun attachView(view: Mvpview) {
        this.view = view as DetailMvpView
    }

    override fun destroy() {
        dispose()
    }

    override fun loadTodo(intent: Intent) {
        when(requestType){
            TodoManager.REQUEST_TYPE_CREATE -> {
                beforeTodo = Todo(-1, "", false).apply {
                    view.onUpdated(this, true)
                }
            }
            TodoManager.REQUEST_TYPE_VIEW -> {
                val id = intent.getIntExtra(TodoManager.KEY_ID, -1)
                if (id != -1){
                    view.onUpdated(beforeTodo.apply { TodoManager.getTodo(id) }, false)
                }
            }
        }
    }

    override fun onTextChanged(s: String) {
        if (!beforeTodo.isFixed && isChanged(s)) beforeTodo.isFixed = true

        txtChangeSubject.onNext(isChanged(s))
    }

    override fun isFixed(): Boolean =  beforeTodo.isFixed

    override fun saveTodo(txt: String) =
            beforeTodo.let {
                it.body = txt
                it.isFixed = false
                if (it.id == -1)
                    it.id = TodoManager.getMaxId() +1
                view.onSaved(requestType, beforeTodo)
                txtChangeSubject.onNext(false)
            }


    private fun isChanged(s: String) : Boolean = !beforeTodo.body.equals(s)
}