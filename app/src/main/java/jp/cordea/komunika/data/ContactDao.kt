package jp.cordea.komunika.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact")
    fun findAll(): List<Contact>

    @Insert
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}
