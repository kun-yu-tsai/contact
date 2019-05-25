package com.example.contacts.data.db

import android.graphics.Bitmap
import android.net.Uri
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.entity.DetailedContact

interface ContactDao {
    fun getAllContact() : List<Contact>
    fun getThumbnail(thumbnailUri: Uri) : Bitmap
    fun getFullSizePhoto(fullSizedUri: Uri) : Bitmap
    fun getDetailedContact(contactId: Long): DetailedContact?
}