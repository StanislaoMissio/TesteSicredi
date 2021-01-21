package com.example.testesicredi.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.testesicredi.R
import com.example.testesicredi.model.User
import com.example.testesicredi.util.Utils
import com.example.testesicredi.viewmodel.EventDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class DialogUserData : DialogFragment() {

    private val viewModel: EventDetailViewModel by viewModel()

    private var eventId = ""
    private lateinit var userName: EditText
    private lateinit var userEmail: EditText
    private lateinit var checkIn: Button

    companion object {
        fun newInstance(eventId: String): DialogUserData {
            val f = DialogUserData()

            val args = Bundle()
            args.putString("eventId", eventId)
            f.arguments = args

            return f
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.dialog_user_data, container)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventId = arguments?.getString("eventId")!!

        userEmail = view.findViewById(R.id.user_email)
        userName = view.findViewById(R.id.user_name)
        checkIn = view.findViewById(R.id.check_in_action)

        checkIn.setOnClickListener { callCheckInAction() }
    }

    private fun callCheckInAction() {
        val user = User(
            name = userName.text.toString(),
            email = userEmail.text.toString(),
            eventId = eventId
        )
        if (Utils.isNetworkAvailable(this.context!!)) {
            viewModel.checkInEvent(user)
        } else {
            Toast.makeText(this.context!!, "Sem conexão com a internet", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)

        return dialog
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        }
    }
}