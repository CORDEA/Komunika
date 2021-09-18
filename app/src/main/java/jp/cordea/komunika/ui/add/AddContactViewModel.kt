package jp.cordea.komunika.ui.add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor() : ViewModel() {
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

    }
}
