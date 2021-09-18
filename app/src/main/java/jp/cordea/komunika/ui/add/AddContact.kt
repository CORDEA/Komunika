package jp.cordea.komunika.ui.add

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun AddContact() {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Add contact") }) }
    ) {
        Content()
    }
}

@Composable
private fun Content() {
}
