package com.example.contacts.data.provider

import android.content.ContentResolver
import android.provider.ContactsContract
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
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                )
            )
        }

        return contactList
    }

    override fun readDetailContact(): LiveData<DetailedContact> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateDetailContact(detailedContact: DetailedContact) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}