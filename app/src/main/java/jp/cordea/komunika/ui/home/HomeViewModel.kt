package jp.cordea.komunika.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.cordea.komunika.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {
    init {
        fetchItems()
    }

    private val _items = MutableLiveData<List<HomeItemViewModel>>()
    val items: LiveData<List<HomeItemViewModel>> get() = _items

    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

    fun onFabClicked() {
        viewModelScope.launch {
            _event.emit(HomeEvent.NavigateToAddContact)
        }
    }

    fun onContactAdded() {
        fetchItems()
    }

    private fun fetchItems() {
        repository.findAll()
            .map { list ->
                list.map {
                    HomeItemViewModel(
                        it.id,
                        it.thumbnail,
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
}

class HomeItemViewModel(
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val body: String
)

sealed class HomeEvent {
    object NavigateToAddContact : HomeEvent()
}
