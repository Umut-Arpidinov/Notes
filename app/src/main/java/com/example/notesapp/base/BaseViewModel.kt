package com.example.notesapp.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

abstract class BaseViewModel(): ViewModel() {
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    protected fun <T : Any> Single<T>.request(
        delay: Long = 0,
        block: (T) -> Unit = {}
    ): Single<T> {
        compositeDisposable?.add(
            this@request
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delaySubscription(delay, TimeUnit.MILLISECONDS)
                .subscribeWith(object : DisposableSingleObserver<T>() {
                    override fun onSuccess(t: T) {
                        block(t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
        return this
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.clear()
        compositeDisposable = null
    }
}