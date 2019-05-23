package com.example.contacts.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.data.entity.Contact
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactListAdapter : RecyclerView.Adapter<ContactListAdapter.ViewHolder>(){
    private var contactList : List<Contact> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false))
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(contact: Contact){
            itemView.name.text = contact.name
            itemView.phoneNumber.text = contact.phoneNumber
        }
    }

    fun updateAllContact(contactList: List<Contact>){
        this.contactList = contactList
        notifyDataSetChanged()
    }
}