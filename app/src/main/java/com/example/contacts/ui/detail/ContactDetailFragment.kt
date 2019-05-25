package com.example.contacts.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.contacts.R
import kotlinx.android.synthetic.main.fragment_contact_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import kotlin.coroutines.CoroutineContext

class ContactDetailFragment : Fragment(), CoroutineScope, KodeinAware {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override val kodein: Kodein by kodein()
    private lateinit var viewModel: ContactDetailViewModel
    private val viewModelFactory: ContactDetailedViewModelFactory by instance()

    private val args: ContactDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = Job()

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(ContactDetailViewModel::class.java)

        viewModel.detailedContact.observe(this,
            Observer { contactDetail ->
                name.text = contactDetail.name
                launch {
                    viewModel.getFullSizedAvatar()
                }
            })

        viewModel.avatarBitmap.observe(this,
            Observer { fullSizedAvatar ->
                avatar.setImageBitmap(fullSizedAvatar)
            }
        )
    }


    override fun onStart() {
        super.onStart()
        launch {
            viewModel.getDetailedContact(args.contactId)
        }
    }

    // show the view only. Show it by ViewModel? shall be viewModel, because I need more data. And I might need to change the data.
    // provide editing feature?
    // provide calling and email feature?


}
