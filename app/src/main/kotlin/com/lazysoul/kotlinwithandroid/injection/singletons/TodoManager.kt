package com.lazysoul.kotlinwithandroid.injection.singletons

import com.lazysoul.kotlinwithandroid.injection.datas.Todo

/**
 * Created by jhjun on 2018-02-10.
 */
 object TodoManager {

    const val KEY_ID: String = "id"
    const val KEY_BODY: String = "body"
    const val KEY_REQUEST_TYPE = "request_type"
    const val KEY_RESULT_TYPE = "result_type"
    const val REQUEST_TYPE_CREATE = 100
    const val REQUEST_TYPE_VIEW = 101
    const val RESUL_TYPE_CREATED = 200
    const val RESUL_TYPE_UPDATED = 201
    private val todoList: MutableList<Todo> = arrayListOf()

    fun createSamples(): MutableList<Todo> {
        for (i in 1..10) {
            todoList.add(Todo(i, "Todo : $i", false))
        }
        return todoList
    }

    // todo 수정
    fun getMaxId(): Int {
        var max = -1
        todoList.filter { it.id > max }
                .forEach { max = it.id }
        return max
    }

    // todo 수정
    fun getTodo(id: Int): Todo? = todoList.firstOrNull { it.id == id }


    // todo 수정
    fun search(txt: String): MutableList<Todo> {
        var result = arrayListOf<Todo>()
        if (txt.isNullOrEmpty()) {
            result.addAll(todoList)
        } else {
            todoList.filter { it.body.contains(txt) }
                    .forEach { result.add(it) }
        }
        return result
    }

    fun insert(id: Int, body: String) =
            Todo(id, body, false)
                    .apply { todoList.add(this) }

    fun update(id: Int, body: String): Todo? =
            todoList.firstOrNull { it.id == id }
                    ?.apply { this.body = body }

    fun checked(id: Int, isChecked: Boolean) {
        todoList.filter { it.id == id }
                .forEach { it.isChecked = isChecked }
    }
}