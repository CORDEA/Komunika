package jp.cordea.komunika.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    @ColumnInfo(name = "email_address")
    val emailAddress: String,
    @ColumnInfo(name = "company")
    val company: String
) {
    constructor(
        thumbnail: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        emailAddress: String,
        company: String
    ) : this(0, thumbnail, firstName, lastName, phoneNumber, emailAddress, company)
}
