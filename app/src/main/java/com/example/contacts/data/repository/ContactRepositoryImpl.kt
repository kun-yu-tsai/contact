package com.example.contacts.data.repository

import android.graphics.Bitmap
import android.net.Uri
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.entity.ContactDetail
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

    override suspend fun getContactDetail(contactId : Long): ContactDetail? {
        return withContext(Dispatchers.IO) { contactDao.getContactDetail(contactId) }
    }

    override suspend fun getThumbnail(contact: Contact): Bitmap {
        return withContext(Dispatchers.IO) {
            contactDao.getThumbnail(Uri.parse(contact.thumbnailUri))
        }
    }

    override suspend fun getFullSizedAvatar(contactDetail: ContactDetail?): Bitmap {
        return withContext(Dispatchers.IO) {
            contactDao.getFullSizePhoto(Uri.parse(contactDetail?.fullSizedAvatarUri))
        }
    }
}
