package com.ibrahimdemir.exchangeapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ibrahimdemir.exchangeapp.R
import com.ibrahimdemir.exchangeapp.model.CoinDetailList

class DetailRecyclerViewAdapter(
    private val coinDetailList: List<CoinDetailList>
) : RecyclerView.Adapter<DetailRecyclerViewAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_detail_coin, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bindItems(coinDetailList[position])
        holder.itemView.setOnClickListener {
            //no-op
        }
    }

    override fun getItemCount(): Int {
        return coinDetailList.size
    }

    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewDesc: TextView = itemView.findViewById(R.id.textViewDesc)
        private val textViewClo: TextView = itemView.findViewById(R.id.textViewClo)
        private val textViewValueLas: TextView = itemView.findViewById(R.id.textViewValueLas)
        private val textViewValueBuy: TextView = itemView.findViewById(R.id.textViewValueBuy)
        private val textViewValueSel: TextView = itemView.findViewById(R.id.textViewValueSel)
        private val textViewValueLow: TextView = itemView.findViewById(R.id.textViewValueLow)
        private val textViewValueHig: TextView = itemView.findViewById(R.id.textViewValueHig)
        private val textViewValueDdi: TextView = itemView.findViewById(R.id.textViewValueDdi)
        private val textViewValuePdd: TextView = itemView.findViewById(R.id.textViewValuePdd)

        fun bindItems(item: CoinDetailList) {
            textViewDesc.text = item.desc
            textViewClo.text = item.clo
            textViewValueLas.text = item.fields?.las
            textViewValueBuy.text = item.fields?.buy
            textViewValueSel.text = item.fields?.sel
            textViewValueLow.text = item.fields?.low
            textViewValueHig.text = item.fields?.hig
            textViewValueDdi.text = item.fields?.ddi
            textViewValuePdd.text = item.fields?.pdd
        }
    }
}