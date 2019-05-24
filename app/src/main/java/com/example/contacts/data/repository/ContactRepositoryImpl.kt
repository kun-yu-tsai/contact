package com.example.contacts.data.repository

import android.graphics.Bitmap
import android.net.Uri
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

    override suspend fun getThumbnail(contact: Contact): Bitmap {
        return withContext(Dispatchers.IO){
            contactDao.getThumbnail(Uri.parse(contact.thumbnailUri))
        }
    }

    override fun getFullSizePhoto(contact: Contact): Bitmap {
        return contactDao.getFullSizePhoto(Uri.parse(contact.fullSizePhotoUri))
    }
}
