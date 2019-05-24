package com.example.contacts.data.provider

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.entity.DetailedContact

interface ContactDao {
    fun getAllContact() : List<Contact>
    fun getThumbnail(thumbnailUri: Uri) : Bitmap
    fun getFullSizePhoto(fullSizedUri: Uri) : Bitmap
    fun readDetailContact(): LiveData<DetailedContact>
    fun updateDetailContact(detailedContact: DetailedContact)
}