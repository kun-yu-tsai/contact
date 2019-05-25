package com.example.contacts.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.repository.ContactRepository

class ContactListViewModel(
    private val contactRepository: ContactRepository
) : ViewModel() {
    private val _contactList = MutableLiveData<List<Contact>>()
    val contactList: LiveData<List<Contact>>
        get() = _contactList

    suspend fun getAllContact() {
        val list = contactRepository.getAllContact()
        _contactList.postValue(list)
    }
}