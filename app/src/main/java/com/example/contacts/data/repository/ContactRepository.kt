package com.example.contacts.data.repository

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.entity.DetailedContact
import kotlinx.coroutines.Deferred

interface ContactRepository {
    suspend fun getAllContact() : List<Contact>
    fun readDetailContact(): LiveData<DetailedContact>
    fun updateDetailContact(detailedContact: DetailedContact)
    suspend fun getThumbnail(contact: Contact): Bitmap
    fun getFullSizePhoto(contact: Contact): Bitmap
}