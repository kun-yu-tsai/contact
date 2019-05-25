package com.example.contacts.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.repository.ContactRepository
import kotlinx.android.synthetic.main.contact_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import kotlin.coroutines.CoroutineContext

class ContactListAdapter : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    private var contactList: List<Contact> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false))
        viewHolder.itemView.setOnClickListener {
            val contact = contactList[viewHolder.adapterPosition]
            val action = ContactListFragmentDirections.startDetailedContact(contact.id.toLong())
            it.findNavController().navigate(action)
        }
        return viewHolder
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), KodeinAware, CoroutineScope {
        private var job = Job()
        override val coroutineContext: CoroutineContext
            get() = job + Dispatchers.Main

        override val kodein: Kodein by org.kodein.di.android.kodein(itemView.context)
        private val contactRepository: ContactRepository by instance()

        fun bind(contact: Contact) {
            itemView.name.text = contact.name
            itemView.phoneNumber.text = contact.phoneNumber

            launch {
                itemView.thumbnail.setImageBitmap(contactRepository.getThumbnail(contact))
            }
        }
    }

    fun setContactList(contactList: List<Contact>) {
        this.contactList = contactList
        notifyDataSetChanged()
    }
}