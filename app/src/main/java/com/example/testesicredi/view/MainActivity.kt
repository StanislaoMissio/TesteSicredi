package com.example.testesicredi.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testesicredi.LoadingImageView
import com.example.testesicredi.R
import com.example.testesicredi.util.Utils
import com.example.testesicredi.view.adapter.EventsAdapter
import com.example.testesicredi.viewmodel.ListEventsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ListEventsViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private var adapter: EventsAdapter = EventsAdapter(mutableListOf())
    private lateinit var loading: LoadingImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.event_list)
        loading = findViewById(R.id.loading)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (Utils.isNetworkAvailable(this)) {
            viewModel.getListEvents()
        } else {
            Toast.makeText(this, "Você esta sem conexão com a internet", Toast.LENGTH_LONG).show()
        }
        viewModel.listEvents.observe(this) {
            adapter.update(it)
        }

        viewModel.loading.observe(this) {
            if (it) loading.visibility = View.VISIBLE
            else loading.visibility = View.GONE
        }

        viewModel.error.observe(this) {
            Toast.makeText(
                this,
                "Ocorreu um erro, tente novamente mais tarde",
                Toast.LENGTH_LONG
            ).show()
        }

    }
}