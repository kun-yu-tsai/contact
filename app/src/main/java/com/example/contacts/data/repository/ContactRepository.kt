package com.example.contacts.data.repository

import android.graphics.Bitmap
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.entity.ContactDetail

interface ContactRepository {
    suspend fun getAllContact(): List<Contact>
    suspend fun getContactDetail(contactId: Long): ContactDetail?
    suspend fun getThumbnail(contact: Contact): Bitmap
    suspend fun getFullSizedAvatar(contactDetail: ContactDetail?): Bitmap
}