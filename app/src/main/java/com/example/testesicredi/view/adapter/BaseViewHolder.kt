package com.example.testesicredi.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testesicredi.R
import com.example.testesicredi.databinding.EventViewHolderBinding
import com.example.testesicredi.model.Events
import com.example.testesicredi.view.EventDetailActivity

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class EventViewHolder(
    private val parent: ViewGroup,
    private val binding: EventViewHolderBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.event_view_holder,
        parent,
        false
    )
) : BaseViewHolder(binding.root) {
    fun bind(event: Events) {
        binding.event = event
        binding.container.setOnClickListener {
            val intent = Intent(binding.root.context, EventDetailActivity::class.java)
            intent.putExtra("eventId", event.id)
            binding.root.context.startActivity(intent)
        }
    }
}
