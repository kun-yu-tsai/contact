package com.example.contacts.data.repository

import androidx.lifecycle.LiveData
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.entity.DetailedContact
import com.example.contacts.data.provider.ContactDao
import kotlinx.coroutines.*

class ContactRepositoryImpl(
    private val contactDao: ContactDao
) : ContactRepository {
    override suspend fun getAllContact(): List<Contact> {
        return withContext(Dispatchers.IO){
            contactDao.getAllContact()
        }
    }

    override fun readDetailContact(): LiveData<DetailedContact> {
        return contactDao.readDetailContact()
    }

    override fun updateDetailContact(detailedContact: DetailedContact) {
        contactDao.updateDetailContact(detailedContact)
    }
}