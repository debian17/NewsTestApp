package com.debian17.newstestapp.app.base.mvp

import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter

abstract class BasePresenter<T : BaseView> : MvpPresenter<T>() {

    private val subscriptions = CompositeDisposable()

    protected fun unsubscribeOnDestroy(disposable: Disposable) {
        subscriptions.add(disposable)
    }

    @CallSuper
    override fun onDestroy() {
        subscriptions.clear()
    }

}