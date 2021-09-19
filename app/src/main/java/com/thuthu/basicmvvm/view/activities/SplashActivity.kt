package com.thuthu.basicmvvm.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thuthu.basicmvvm.R
import com.thuthu.basicmvvm.viewmodel.SplashState
import com.thuthu.basicmvvm.viewmodel.SplashViewModel


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        splashViewModel.liveData.observe(this, Observer {
            when (it) {
                is SplashState.MainActivity -> {
                    goToMainActivity()
                }
            }
        })
    }

    private fun goToMainActivity() {

        val shared = getSharedPreferences("RememberLogin", MODE_PRIVATE)
        val rememberlogin = shared.getBoolean("rememberLogin", false)
        if(rememberlogin)
            startActivity(Intent(this, ListActivity::class.java))
        else
          startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}