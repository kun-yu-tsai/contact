package com.example.contacts.ui.list

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.repository.ContactRepository

class ContactListViewModel(
    private val contactRepository: ContactRepository
) : ViewModel() {
    private val _emptyScreenVisibility = MutableLiveData<Int>()
    val emptyScreenVisibility : LiveData<Int>
        get() = _emptyScreenVisibility

    private val _contactListVisibility = MutableLiveData<Int>()
    val contactListVisibility : LiveData<Int>
        get() = _contactListVisibility

    private val _contactList = MutableLiveData<List<Contact>>()
    val contactList: LiveData<List<Contact>>
        get() = _contactList

    suspend fun getAllContact() {
        val list = contactRepository.getAllContact()

        _contactList.postValue(list)
        _emptyScreenVisibility.postValue(if(list.isEmpty()) View.VISIBLE else View.GONE)
        _contactListVisibility.postValue(if(list.isEmpty()) View.GONE else View.VISIBLE)
    }
}