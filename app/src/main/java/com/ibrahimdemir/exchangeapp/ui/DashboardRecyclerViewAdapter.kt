package com.ibrahimdemir.exchangeapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ibrahimdemir.exchangeapp.R
import com.ibrahimdemir.exchangeapp.model.CoinList

class DashboardRecyclerViewAdapter(
    private var coinList: ArrayList<CoinList>
) : RecyclerView.Adapter<DashboardRecyclerViewAdapter.DashboardViewHolder>() {

    private lateinit var itemClickListener: DashboardItemClickListener
    private lateinit var itemLongClickListener: DashboardItemLongClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dashboard_coins, parent, false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bindItems(coinList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(
                coinList[position]
            )
        }

        holder.itemView.setOnLongClickListener {
            itemLongClickListener.onItemLongClick(
                coinList[position]
            )
            true
        }
    }

    class DashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewDef: TextView = itemView.findViewById(R.id.textViewDef)
        private val textViewCod: TextView = itemView.findViewById(R.id.textViewCod)
        private val textViewClo: TextView = itemView.findViewById(R.id.textViewClo)
        private val textViewBuy: TextView = itemView.findViewById(R.id.textViewBuy)
        private val textViewSel: TextView = itemView.findViewById(R.id.textViewSel)

        fun bindItems(item: CoinList) {
            textViewDef.text = item.def
            textViewCod.text = item.cod
            textViewClo.text = item.clo
            textViewBuy.text = item.buy
            textViewSel.text = item.sel
        }
    }

    fun setOnItemClickListener(itemClickListener: DashboardItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    fun setOnItemLongClickListener(itemLongClickListener: DashboardItemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener
    }

    fun updateData(coinList: ArrayList<CoinList>) {
        notifyDataSetChanged()
    }

    interface DashboardItemClickListener {
        fun onItemClick(coinList: CoinList)
    }

    interface DashboardItemLongClickListener {
        fun onItemLongClick(coinList: CoinList)
    }
}