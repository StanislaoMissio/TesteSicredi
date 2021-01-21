package com.example.testesicredi.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.example.testesicredi.viewmodel.EventDetailViewModel
import com.example.testesicredi.R
import com.example.testesicredi.databinding.ActivityEventDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding
    private val viewModel: EventDetailViewModel by viewModel()

    private var eventId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail)

        binding.lifecycleOwner = this

        eventId = intent.getStringExtra("eventId")!!

        viewModel.getListEvents(eventId)

        viewModel.eventDetail.observe(this) {
            binding.event = it
        }

        viewModel.loading.observe(this) {
            if (it) binding.loading.visibility = View.VISIBLE
            else binding.loading.visibility = View.GONE
        }

        viewModel.error.observe(this) {
            Toast.makeText(
                this,
                "Ocorreu um erro, tente novamente mais tarde",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun callOnCheckInDialog(view: View) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.addToBackStack(null)
        DialogUserData.newInstance(eventId).show(ft, "dialog")
    }

}