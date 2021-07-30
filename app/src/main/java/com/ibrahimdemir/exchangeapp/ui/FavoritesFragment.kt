package com.ibrahimdemir.exchangeapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibrahimdemir.exchangeapp.MainApplication
import com.ibrahimdemir.exchangeapp.R
import kotlinx.android.synthetic.main.custom_toolbar.view.*

class FavoritesFragment : Fragment() {

    private val appComponents by lazy { MainApplication.appComponents }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appComponents.inject(this)
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.customToolbarTitle.text = getString(R.string.title_favorites)
    }
}