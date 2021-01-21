package com.example.testesicredi.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testesicredi.model.Events

class EventsAdapter(private var eventList: MutableList<Events>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        EventViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EventViewHolder) {
            holder.bind(eventList[position])
        }
    }

    override fun getItemCount(): Int = eventList.size

    fun update(eventList: MutableList<Events>) {
        this.eventList = eventList
        notifyDataSetChanged()
    }
}