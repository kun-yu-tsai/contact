package com.example.contacts.ui.detail

import android.graphics.Bitmap
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.contacts.data.entity.DetailedContact
import com.example.contacts.data.repository.ContactRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertSame
import org.junit.Rule
import org.junit.Test

class ContactDetailViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun detailedContact_and_avatarBitmap_getPosted_to_liveData() = runBlocking {
        val contactRepository: ContactRepository = mock()
        val detailedContact = DetailedContact("name", "123", "uri")
        val viewModel = ContactDetailViewModel(contactRepository)
        val bitmap: Bitmap = mock()

        whenever(contactRepository.getDetailedContact(0)).thenReturn(detailedContact)
        viewModel.getDetailedContact(0)
        assertSame(detailedContact, viewModel.detailedContact.value)

        whenever(contactRepository.getFullSizedAvatar(detailedContact)).thenReturn(bitmap)
        viewModel.getFullSizedAvatar()
        assertSame(bitmap, viewModel.avatarBitmap.value)
    }
}
