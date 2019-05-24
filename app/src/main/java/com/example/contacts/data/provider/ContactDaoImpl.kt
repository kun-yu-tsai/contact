package com.example.contacts.data.provider

import android.content.ContentResolver
import android.content.res.AssetFileDescriptor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.entity.DetailedContact

class ContactDaoImpl(private val contentResolver: ContentResolver) : ContactDao {
    override fun getAllContact(): List<Contact> {

        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        val contactList = mutableListOf<Contact>()
        while (cursor?.moveToNext() == true) {
            contactList.add(
                Contact(
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
                )
            )
        }

        Log.i("myapp", "${contactList}")

        cursor?.close()

        return contactList
    }

    override fun readDetailContact(): LiveData<DetailedContact> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateDetailContact(detailedContact: DetailedContact) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getThumbnail(thumbnailUri: Uri): Bitmap {
        val inputStream = contentResolver.openInputStream(thumbnailUri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()
        return bitmap
    }

    override fun getFullSizePhoto(fullSizedUri: Uri): Bitmap {
        val fd = contentResolver.openAssetFileDescriptor(fullSizedUri, "r")
        val inputStream = fd?.createInputStream()
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()
        fd?.close()
        return bitmap
    }
}
