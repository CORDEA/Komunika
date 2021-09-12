package jp.cordea.komunika.repository

import jp.cordea.komunika.data.AppDatabase
import jp.cordea.komunika.data.Contact
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor(
    private val database: AppDatabase
) {
    fun findAll() = flow { emit(database.contactDao().findAll()) }

    fun insert(contact: Contact) = flow { emit(database.contactDao().insert(contact)) }

    fun delete(contact: Contact) = flow { emit(database.contactDao().delete(contact)) }
}
