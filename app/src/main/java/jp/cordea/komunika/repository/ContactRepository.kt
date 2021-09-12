package jp.cordea.komunika.repository

import jp.cordea.komunika.data.AppDatabase
import jp.cordea.komunika.data.Contact
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor(
    private val database: AppDatabase
) {
    fun findAll(): List<Contact> = database.contactDao().findAll()

    fun insert(contact: Contact) = database.contactDao().insert(contact)

    fun delete(contact: Contact) = database.contactDao().delete(contact)
}
