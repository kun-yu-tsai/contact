package com.example.contacts.data.repository

import android.graphics.Bitmap
import android.net.Uri
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.entity.DetailedContact
import com.example.contacts.data.db.ContactDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactRepositoryImpl(
    private val contactDao: ContactDao
) : ContactRepository {
    override suspend fun getAllContact(): List<Contact> {
        return withContext(Dispatchers.IO) {
            contactDao.getAllContact()
        }
    }

    override suspend fun getDetailedContact(contactId : Long): DetailedContact? {
        return withContext(Dispatchers.IO) { contactDao.getDetailedContact(contactId) }
    }

    override suspend fun getThumbnail(contact: Contact): Bitmap {
        return withContext(Dispatchers.IO) {
            contactDao.getThumbnail(Uri.parse(contact.thumbnailUri))
        }
    }

    override suspend fun getFullSizePhoto(detailedContact: DetailedContact?): Bitmap {
        return withContext(Dispatchers.IO) {
            contactDao.getFullSizePhoto(Uri.parse(detailedContact?.fullSizedAvatarUri))
        }
    }
}
