package jp.cordea.komunika.ui.add

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.cordea.komunika.data.Contact
import jp.cordea.komunika.repository.ContactRepository
import jp.cordea.komunika.repository.ImagePersistenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val imagePersistenceRepository: ImagePersistenceRepository,
    private val contactRepository: ContactRepository
) : ViewModel() {
    private val _thumbnail = MutableStateFlow(Uri.EMPTY)
    val thumbnail get() = _thumbnail.asStateFlow()

    private val _firstName = MutableStateFlow("")
    val firstName get() = _firstName.asStateFlow()

    private val _lastName = MutableStateFlow("")
    val lastName get() = _lastName.asStateFlow()

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber get() = _phoneNumber.asStateFlow()

    private val _emailAddress = MutableStateFlow("")
    val emailAddress get() = _emailAddress.asStateFlow()

    private val _company = MutableStateFlow("")
    val company get() = _company.asStateFlow()

    private val _event = MutableSharedFlow<AddContactEvent>()
    val event get() = _event.asSharedFlow()

    fun onAddThumbnailClicked() {
        viewModelScope.launch { _event.emit(AddContactEvent.PickImage()) }
    }

    fun onThumbnailAdded(bytes: ByteArray) {
        imagePersistenceRepository.insert(bytes)
            .flowOn(Dispatchers.IO)
            .onEach { _thumbnail.tryEmit(it) }
            .catch { it.printStackTrace() }
            .launchIn(viewModelScope)
    }

    fun onFirstNameChanged(firstName: String) {
        _firstName.tryEmit(firstName)
    }

    fun onLastNameChanged(lastName: String) {
        _lastName.tryEmit(lastName)
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        _phoneNumber.tryEmit(phoneNumber)
    }

    fun onEmailAddressChanged(emailAddress: String) {
        _emailAddress.tryEmit(emailAddress)
    }

    fun onCompanyChanged(company: String) {
        _company.tryEmit(company)
    }

    fun onFabClicked() {
        contactRepository.insert(
            Contact(
                thumbnail = thumbnail.value?.toString().orEmpty(),
                firstName = firstName.value,
                lastName = lastName.value,
                phoneNumber = phoneNumber.value,
                emailAddress = emailAddress.value,
                company = company.value,
            )
        )
            .flowOn(Dispatchers.IO)
            .transform { emit(_event.emit(AddContactEvent.Back)) }
            .launchIn(viewModelScope)
    }
}

sealed class AddContactEvent {
    object Back : AddContactEvent()
    class PickImage : AddContactEvent()
}
