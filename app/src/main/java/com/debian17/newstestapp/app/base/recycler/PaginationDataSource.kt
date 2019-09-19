package com.debian17.newstestapp.app.base.recycler

import androidx.paging.PageKeyedDataSource
import io.reactivex.subjects.PublishSubject

abstract class PaginationDataSource<K, V>(
    private val initialLoading: PublishSubject<Boolean>,
    private val pagination: PublishSubject<Boolean>,
    private val error: PublishSubject<Throwable>
) : PageKeyedDataSource<K, V>() {

    protected abstract fun loadInitialData(
        params: LoadInitialParams<K>,
        callback: LoadInitialCallback<K, V>
    )

    protected abstract fun loadDataAfter(params: LoadParams<K>, callback: LoadCallback<K, V>)

    override fun loadInitial(params: LoadInitialParams<K>, callback: LoadInitialCallback<K, V>) {
        initialLoading.onNext(true)
        try {
            loadInitialData(params, callback)
        } catch (e: RuntimeException) {
            error.onNext(e)
            return
        }
        initialLoading.onNext(false)
    }

    override fun loadAfter(params: LoadParams<K>, callback: LoadCallback<K, V>) {
        pagination.onNext(true)
        try {
            loadDataAfter(params, callback)
        } catch (e: RuntimeException) {
            error.onNext(e)
            return
        }
        pagination.onNext(false)
    }

    override fun loadBefore(params: LoadParams<K>, callback: LoadCallback<K, V>) {
        //not used
    }
}