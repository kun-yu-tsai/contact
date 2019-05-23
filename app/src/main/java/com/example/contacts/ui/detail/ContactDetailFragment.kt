package com.example.contacts.ui.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.contacts.R

class ContactDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_detail, container, false)
    }

    // show the view only. Show it by ViewModel? shall be viewModel, because I need more data. And I might need to change the data.
    // provide editing feature?
    // provide calling and email feature?


}
