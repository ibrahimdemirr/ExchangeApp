package com.ibrahimdemir.exchangeapp.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahimdemir.exchangeapp.MainApplication
import com.ibrahimdemir.exchangeapp.R
import com.ibrahimdemir.exchangeapp.model.CoinList
import com.ibrahimdemir.exchangeapp.ui.activity.MainActivity
import com.ibrahimdemir.exchangeapp.utils.*
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DashboardFragment : Fragment() {

    private val appComponents by lazy { MainApplication.appComponents }

    private lateinit var dashboardRecyclerViewAdapter: DashboardRecyclerViewAdapter
    private var isFirstLoading: Boolean? = true

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun getViewModel(): ExchangeViewModel {
        return viewModelProvider(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appComponents.inject(this)
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().getAllCoins()
        getAllCoinsData()
        initObserLiveData()
        view.customToolbar.apply {
            inflateMenu(R.menu.favorites)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.fav -> {
                        (activity as? MainActivity)?.replaceFragment(
                            FavoritesFragment(),
                            R.id.frameLayoutContainer
                        )
                        true
                    }
                    else -> {
                        super.onOptionsItemSelected(it)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        callCoinsService()
    }

    private fun initObserLiveData() {
        getViewModel().dataLoading.observe(viewLifecycleOwner, { dataLoading ->
            if (dataLoading && isFirstLoading == true) {
                (activity as? MainActivity)?.showProgressBar()
                isFirstLoading = false
            } else {
                (activity as? MainActivity)?.hideProgressBar()
            }
        })
    }

    @SuppressLint("SimpleDateFormat")
    private fun getAllCoinsData() {
        getViewModel().getAllCoinsLiveData.observe(viewLifecycleOwner, { response ->
            response?.let {
                dashboardRecyclerViewAdapter = DashboardRecyclerViewAdapter(it.coinList)
                allCoinRecyclerView.adapter = dashboardRecyclerViewAdapter

                dashboardRecyclerViewAdapter.setOnItemClickListener(object :
                    DashboardRecyclerViewAdapter.DashboardItemClickListener {
                    override fun onItemClick(coinList: CoinList) {
                        val fragment = DetailFragment()
                        val args = Bundle()
                        args.putString("cod", coinList.cod)
                        fragment.arguments = args
                        (activity as? MainActivity)?.replaceFragment(
                            fragment,
                            R.id.frameLayoutContainer
                        )
                    }
                })

                dashboardRecyclerViewAdapter.setOnItemLongClickListener(object :
                    DashboardRecyclerViewAdapter.DashboardItemLongClickListener {
                    override fun onItemLongClick(coinList: CoinList) {
                        showAlertDialog()
                    }
                })

                allCoinRecyclerView.apply {
                    layoutManager = LinearLayoutManager(activity)
                    show()
                }
                view?.snack("Page refreshed " + getTime())
                dashboardRecyclerViewAdapter.updateData(it.coinList)
            } ?: run {
                allCoinRecyclerView.hide()
            }
        })
    }

    private fun callCoinsService() {
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                getViewModel().getAllCoins()
                handler.postDelayed(this, 5000)//5 sec delay
            }
        }, 0)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTime(): String {
        val sdf = SimpleDateFormat("HH:mm:ss")
        return sdf.format(Date())
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(activity)
        builder.apply {
            setTitle(R.string.dialogTitle)
            setMessage(R.string.dialogMessage)
            setIcon(android.R.drawable.ic_dialog_alert)
            setPositiveButton("Yes") { dialogInterface, which ->
                //no-op
            }
            setNegativeButton("No") { dialogInterface, which ->
                //no-op
            }
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}