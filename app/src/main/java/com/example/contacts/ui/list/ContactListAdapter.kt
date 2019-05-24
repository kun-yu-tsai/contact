package com.example.contacts.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.repository.ContactRepository
import kotlinx.android.synthetic.main.contact_item.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import kotlin.system.measureTimeMillis

class ContactListAdapter : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    private var contactList: List<Contact> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false))
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), KodeinAware {
        override val kodein: Kodein by org.kodein.di.android.kodein(itemView.context)
        private val contactRepository: ContactRepository by instance()

        fun bind(contact: Contact) {
            itemView.name.text = contact.name
            itemView.phoneNumber.text = contact.phoneNumber
            val time = measureTimeMillis {
//                itemView.thumbnail.setImageBitmap(contactRepository.getFullSizePhoto(contact))
                itemView.thumbnail.setImageBitmap(contactRepository.getThumbnail(contact))
            }
            Log.i("myapp", "$time")
            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.showDetailedContact)
            }
        }
    }

    fun updateAllContact(contactList: List<Contact>) {
        this.contactList = contactList
        notifyDataSetChanged()
    }
}