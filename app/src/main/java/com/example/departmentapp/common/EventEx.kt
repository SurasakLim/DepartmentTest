package com.example.departmentapp.common

import android.annotation.SuppressLint
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


@SuppressLint("CheckResult")
infix fun ConstraintLayout.onClick(action: () -> Unit) {
    clicks()
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .onErrorResumeNext(Observable.never())
        .subscribe { action() }
}