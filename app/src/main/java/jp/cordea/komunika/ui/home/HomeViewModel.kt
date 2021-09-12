package jp.cordea.komunika.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.cordea.komunika.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: ContactRepository
) : ViewModel() {
    init {
        repository.findAll()
            .map { list ->
                list.map {
                    HomeItemViewModel(
                        it.id,
                        "",
                        "${it.firstName} ${it.lastName}",
                        ""
                    )
                }
            }
            .flowOn(Dispatchers.IO)
            .onEach {
                _items.value = it
            }
            .launchIn(viewModelScope)
    }

    private val _items = MutableLiveData<List<HomeItemViewModel>>()
    private val items: LiveData<List<HomeItemViewModel>> get() = _items
}

class HomeItemViewModel(
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val body: String
)
