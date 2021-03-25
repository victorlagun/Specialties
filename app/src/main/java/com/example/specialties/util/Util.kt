package com.example.specialties.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun isDataStale(lastUpdate: Long?, freshTimeout: Long) =
    Calendar.getInstance().timeInMillis - (lastUpdate ?: 0) > freshTimeout