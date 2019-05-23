package com.example.contacts.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.data.repository.ContactRepository


class ContactListViewModelFactory(
    private val contactRepository: ContactRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactListViewModel(contactRepository) as T
    }
}
