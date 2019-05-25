package com.example.contacts.ui.list


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.R
import com.example.contacts.ui.MainActivity
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
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        contactList.layoutManager = LinearLayoutManager(context)
        contactList.adapter = contactListAdapter
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(ContactListViewModel::class.java)

        viewModel.contactList.observe(this, Observer {
            contactListAdapter.setContactList(it)
            emptyScreen.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
            contactList.visibility = if(it.isEmpty()) View.GONE else View.VISIBLE
        })

        if (context?.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                MainActivity.REQUEST_CODE_PERMISSION_CONTACT
            )
        } else {
            updateContacts()
        }
    }

    private fun updateContacts() {
        if (context?.checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            launch {
                viewModel.getAllContact()
            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MainActivity.REQUEST_CODE_PERMISSION_CONTACT) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateContacts()
            } else {
                Toast.makeText(context, "We need the permission to display the contacts", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
