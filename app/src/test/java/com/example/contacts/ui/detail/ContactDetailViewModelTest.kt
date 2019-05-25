package com.example.contacts.ui.detail

import android.graphics.Bitmap
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.contacts.data.entity.ContactDetail
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
    fun whenGetContactDetail_thenPostItToLiveData() = runBlocking {
        // given
        val contactRepository: ContactRepository = mock()
        val contactDetail = ContactDetail("name", "123", "uri")
        val viewModel = ContactDetailViewModel(contactRepository)
        whenever(contactRepository.getContactDetail(0)).thenReturn(contactDetail)

        // when
        viewModel.getContactDetail(0)

        // then
        assertSame(contactDetail, viewModel.contactDetail.value)
    }

    @Test
    fun whenGetFullSizedAvatar_thenPostItToLiveData() = runBlocking {
        // given
        val contactRepository: ContactRepository = mock()
        val detailedContact = ContactDetail("name", "123", "uri")
        val viewModel = ContactDetailViewModel(contactRepository)
        val bitmap: Bitmap = mock()

        whenever(contactRepository.getFullSizedAvatar(detailedContact)).thenReturn(bitmap)

        // when
        viewModel.getFullSizedAvatar(detailedContact)

        // then
        assertSame(bitmap, viewModel.avatarBitmap.value)
    }
}
