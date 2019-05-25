package com.example.contacts.data.repository

import android.graphics.Bitmap
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.entity.DetailedContact

interface ContactRepository {
    suspend fun getAllContact(): List<Contact>
    suspend fun getDetailedContact(contactId: Long): DetailedContact?
    suspend fun getThumbnail(contact: Contact): Bitmap
    suspend fun getFullSizedAvatar(detailedContact: DetailedContact?): Bitmap
}