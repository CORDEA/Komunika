package jp.cordea.komunika.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.cordea.komunika.repository.ContactRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {
}

class HomeItemViewModel(
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val body: String
)
