package jp.cordea.komunika.ui.add

import android.os.Parcelable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import kotlinx.parcelize.Parcelize

sealed class AddContactResult : Parcelable {
    companion object {
        private const val KEY = "add_contact"

        fun storeResult(savedStateHandle: SavedStateHandle) {
            savedStateHandle[KEY] = Saved
        }

        fun observeResult(savedStateHandle: SavedStateHandle): LiveData<AddContactResult> =
            savedStateHandle.getLiveData(KEY)
    }

    @Parcelize
    object Saved : AddContactResult()
}

@Composable
fun AddContact(viewModel: AddContactViewModel, navController: NavController) {
    val event by viewModel.event.collectAsState(initial = null)
    when (event) {
        AddContactEvent.Back -> {
            navController.previousBackStackEntry?.run {
                AddContactResult.storeResult(savedStateHandle)
            }
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Add contact") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = viewModel::onFabClicked) {
                Icon(Icons.Default.Add, contentDescription = "Add contact")
            }
        }
    ) {
        Content(viewModel)
    }
}

@Composable
private fun Content(viewModel: AddContactViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            val firstName by viewModel.firstName.collectAsState()
            OutlinedTextField(
                value = firstName,
                onValueChange = viewModel::onFirstNameChanged,
                label = { Text("First name") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            val lastName by viewModel.lastName.collectAsState()
            OutlinedTextField(
                value = lastName,
                onValueChange = viewModel::onLastNameChanged,
                label = { Text("Last name") },
                modifier = Modifier.weight(1f)
            )
        }
        val phoneNumber by viewModel.phoneNumber.collectAsState()
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = viewModel::onPhoneNumberChanged,
            label = { Text("Phone number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        val emailAddress by viewModel.emailAddress.collectAsState()
        OutlinedTextField(
            value = emailAddress,
            onValueChange = viewModel::onEmailAddressChanged,
            label = { Text("Email address") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        val company by viewModel.company.collectAsState()
        OutlinedTextField(
            value = company,
            onValueChange = viewModel::onCompanyChanged,
            label = { Text("Company") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
    }
}
