package com.example.contacts.data.provider

import androidx.lifecycle.LiveData
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.entity.DetailedContact

interface ContactDao {
    fun getAllContact() : List<Contact>
    fun readDetailContact(): LiveData<DetailedContact>
    fun updateDetailContact(detailedContact: DetailedContact)
}