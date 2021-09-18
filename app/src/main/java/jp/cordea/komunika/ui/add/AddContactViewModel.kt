package jp.cordea.komunika.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.cordea.komunika.data.Contact
import jp.cordea.komunika.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {
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
        repository.insert(
            Contact(
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
}
