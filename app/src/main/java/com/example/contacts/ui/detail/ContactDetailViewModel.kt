package com.example.contacts.ui.detail

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacts.data.entity.DetailedContact
import com.example.contacts.data.repository.ContactRepository

class ContactDetailViewModel(
    private val contactRepository: ContactRepository
) : ViewModel() {
    private val _detailedContact = MutableLiveData<DetailedContact>()
    val detailedContact: LiveData<DetailedContact>
        get() = _detailedContact

    private val _avatarBitmap = MutableLiveData<Bitmap>()
    val avatarBitmap : LiveData<Bitmap>
        get() = _avatarBitmap

    suspend fun getDetailedContact(contactId : Long) {
        _detailedContact.postValue(contactRepository.getDetailedContact(contactId))
    }

    suspend fun getFullSizedAvatar(){
        _avatarBitmap.postValue(contactRepository.getFullSizePhoto(detailedContact.value))
    }
}