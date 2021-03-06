package com.example.contacts

import android.app.Application
import com.example.contacts.data.db.ContactDao
import com.example.contacts.data.db.ContactDaoProviderImpl
import com.example.contacts.data.repository.ContactRepository
import com.example.contacts.data.repository.ContactRepositoryImpl
import com.example.contacts.ui.detail.ContactDetailedViewModelFactory
import com.example.contacts.ui.list.ContactListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ContactApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ContactApplication))

        bind<ContactDao>() with singleton { ContactDaoProviderImpl(instance()) }
        bind<ContactRepository>() with singleton { ContactRepositoryImpl(instance()) }
        bind() from provider { ContactListViewModelFactory(instance())}
        bind() from provider { ContactDetailedViewModelFactory(instance())}
    }

}