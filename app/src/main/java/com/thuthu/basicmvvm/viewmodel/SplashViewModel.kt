package com.thuthu.basicmvvm.viewmodel

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    private val SPLASH_DELAY: Long = 3000

    val liveData: LiveData<SplashState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()
    init {
        Handler().postDelayed(
            {
                // After the splash screen duration, route to the right activities
                mutableLiveData.postValue(SplashState.MainActivity())
            },
            SPLASH_DELAY
        )
    }

}

sealed class SplashState {
    class MainActivity : SplashState()
}
