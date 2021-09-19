package jp.cordea.komunika.repository

import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import java.io.File
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImagePersistenceRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dir get() = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

    fun insert(bytes: ByteArray) = flow {
        val file = File(dir, UUID.randomUUID().toString())
        file.writeBytes(bytes)
        emit(file.toUri())
    }
}
