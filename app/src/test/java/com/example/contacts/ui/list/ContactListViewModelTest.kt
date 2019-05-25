package com.example.contacts.ui.list

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.contacts.data.entity.Contact
import com.example.contacts.data.repository.ContactRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ContactListViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun test_emptyList_views_visibility() = runBlocking {
        val contactRepository: ContactRepository = mock()
        val list = emptyList<Contact>()
        whenever(contactRepository.getAllContact()).thenReturn(list)
        val viewModel = ContactListViewModel(contactRepository)
        viewModel.getAllContact()

        assertEquals(list, viewModel.contactList.value)
        assertEquals(View.GONE, viewModel.contactListVisibility.value)
        assertEquals(View.VISIBLE, viewModel.emptyScreenVisibility.value)
    }

}