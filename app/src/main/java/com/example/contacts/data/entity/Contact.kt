package com.example.contacts.data.entity

data class Contact(
    val id: String,
    val name : String,
    val phoneNumber: String,
    val thumbnailUri: String,
    val fullSizePhotoUri: String
)
