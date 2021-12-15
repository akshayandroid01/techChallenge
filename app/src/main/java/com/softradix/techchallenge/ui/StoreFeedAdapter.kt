package com.softradix.techchallenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softradix.techchallenge.R
import com.softradix.techchallenge.network.model.StoreResponse

/**
 * A RecyclerView.Adapter to populate the screen with a store feed.
 */
class StoreFeedAdapter(val onClick: (StoreResponse) -> Unit) : RecyclerView.Adapter<StoreItemViewHolder>() {

    private var mDataList : ArrayList<StoreResponse> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        return StoreItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_store, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
       val item = mDataList[position]

        with(holder.itemView) {
            findViewById<TextView>(R.id.name).text = item.name
            findViewById<TextView>(R.id.description).text = item.description
            setOnClickListener { onClick(item) }
        }
    }

    override fun getItemCount() = mDataList.size

    fun addData(list:List<StoreResponse>){
        mDataList.addAll(list)
        notifyDataSetChanged()
    }
}

/**
 * Holds the view for the Store item.
 */
class StoreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
