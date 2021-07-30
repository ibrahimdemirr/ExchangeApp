package com.ibrahimdemir.exchangeapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahimdemir.exchangeapp.MainApplication
import com.ibrahimdemir.exchangeapp.R
import com.ibrahimdemir.exchangeapp.ui.activity.MainActivity
import com.ibrahimdemir.exchangeapp.utils.show
import com.ibrahimdemir.exchangeapp.utils.viewModelProvider
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : Fragment() {

    private val appComponents by lazy { MainApplication.appComponents }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): ExchangeViewModel {
        return viewModelProvider(viewModelFactory)
    }

    private var cod: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appComponents.inject(this)
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cod = arguments?.getString("cod")
        getViewModel().getCoinDetail(cod)

        initObserLiveData()
        getDetailItems()
    }

    private fun initObserLiveData() {
        getViewModel().dataLoading.observe(viewLifecycleOwner, { dataLoading ->
            if (dataLoading) {
                (activity as? MainActivity)?.showProgressBar()
            } else {
                (activity as? MainActivity)?.hideProgressBar()
            }
        })
    }

    private fun getDetailItems() {
        getViewModel().getCoinDetailLiveData.observe(viewLifecycleOwner, { response ->
            response?.let {
                detailItemsRecyclerView.apply {
                    show()
                    layoutManager = LinearLayoutManager(activity)
                    adapter = DetailRecyclerViewAdapter(it.coinDetailList)
                }
            }
        })
    }
}