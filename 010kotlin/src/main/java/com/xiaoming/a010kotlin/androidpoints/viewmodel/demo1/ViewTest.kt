package com.xiaoming.a010kotlin.androidpoints.viewmodel.demo1

import android.util.Log
import androidx.lifecycle.ViewModel

class ViewModelTest : ViewModel() {

    companion object {
        const val TAG = "ViewModelTest"
    }

    var number = 0

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared")
    }
}