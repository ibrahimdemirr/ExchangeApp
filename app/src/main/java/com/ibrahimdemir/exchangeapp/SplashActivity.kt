package com.ibrahimdemir.exchangeapp

import android.os.Handler
import com.ibrahimdemir.exchangeapp.base.BaseActivity
import com.ibrahimdemir.exchangeapp.utils.startActivity

class SplashActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initView() {
        Handler().postDelayed({
            startActivity<MainActivity>()
            finish()
        }, SPLASH_DISPLAY_TIME)
    }

    companion object {
        const val SPLASH_DISPLAY_TIME = 2000L
    }
}