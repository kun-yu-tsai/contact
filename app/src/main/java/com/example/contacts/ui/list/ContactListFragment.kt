package com.example.contacts.ui.list


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.R
import kotlinx.android.synthetic.main.fragment_contact_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import kotlin.coroutines.CoroutineContext

class ContactListFragment : Fragment(), KodeinAware, CoroutineScope {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override val kodein by kodein()
    private lateinit var viewModel: ContactListViewModel
    private val viewModelFactory: ContactListViewModelFactory by instance()

    private val contactListAdapter: ContactListAdapter by lazy {
        ContactListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button.setOnClickListener {
            launch {
                viewModel.getAllContact()
            }
        }

        contactList.layoutManager = LinearLayoutManager(context)
        contactList.adapter = contactListAdapter
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(ContactListViewModel::class.java)

        viewModel.contactList.observe(this, Observer {
            contactListAdapter.updateAllContact(it)
            Log.i("myapp", "update ${it.size}")
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    // each view click event to open another fragment -> in the navigation component?


}
