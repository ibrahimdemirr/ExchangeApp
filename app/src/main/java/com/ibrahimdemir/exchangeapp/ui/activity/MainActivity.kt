package com.ibrahimdemir.exchangeapp.ui.activity

import com.ibrahimdemir.exchangeapp.MainApplication
import com.ibrahimdemir.exchangeapp.R
import com.ibrahimdemir.exchangeapp.base.BaseActivity
import com.ibrahimdemir.exchangeapp.ui.DashboardFragment
import com.ibrahimdemir.exchangeapp.utils.replaceFragment

class MainActivity : BaseActivity() {

    private val appComponents by lazy { MainApplication.appComponents }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        appComponents.inject(this)
        replaceFragment(DashboardFragment(), R.id.frameLayoutContainer)
    }
}