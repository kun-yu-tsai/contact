package com.example.contacts.ui.detail

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacts.data.entity.ContactDetail
import com.example.contacts.data.repository.ContactRepository

class ContactDetailViewModel(
    private val contactRepository: ContactRepository
) : ViewModel() {
    private val _detailedContact = MutableLiveData<ContactDetail>()
    val contactDetail: LiveData<ContactDetail>
        get() = _detailedContact

    private val _avatarBitmap = MutableLiveData<Bitmap>()
    val avatarBitmap : LiveData<Bitmap>
        get() = _avatarBitmap

    suspend fun getContactDetail(contactId : Long) {
        _detailedContact.postValue(contactRepository.getContactDetail(contactId))
    }

    suspend fun getFullSizedAvatar(contactDetail: ContactDetail){
        _avatarBitmap.postValue(contactRepository.getFullSizedAvatar(contactDetail))
    }
}