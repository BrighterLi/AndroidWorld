package com.xiaoming.androidpoints.frame.mvx.mvvm.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.flow

/**
 * User: BrightLi
 * Date: 2023-10-15-20:07
 * DESC:
 */
class MyViewModel : ViewModel() {

    var flow = flow<Int> {
        for (i in 1..10) {
            emit(i)
        }
    }
}