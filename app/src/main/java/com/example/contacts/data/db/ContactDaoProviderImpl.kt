package com.example.contacts.data.db

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.ContactsContract
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.entity.ContactDetail

class ContactDaoProviderImpl(private val contentResolver: ContentResolver) : ContactDao {
    override fun getAllContact(): List<Contact> {

        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI
            ),
            null,
            null,
            null
        )

        val contactList = mutableListOf<Contact>()
        while (cursor?.moveToNext() == true) {
            contactList.add(
                Contact(
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI))
                )
            )
        }

        cursor?.close()
        return contactList
    }

    override fun getContactDetail(contactId: Long): ContactDetail? {
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI
            ),
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
            null,
            null
        )
        var contactDetail: ContactDetail? = null

        if(cursor?.moveToFirst() == true) {
            contactDetail = ContactDetail(
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY)),
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
            )
        }

        cursor?.close()

        return contactDetail
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
