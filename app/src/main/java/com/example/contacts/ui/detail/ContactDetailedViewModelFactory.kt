package com.example.contacts.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.data.repository.ContactRepository


class ContactDetailedViewModelFactory(
    private val contactRepository: ContactRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactDetailViewModel(contactRepository) as T
    }
}
