package ibu.edu.unitask.ui.finished

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ibu.edu.unitask.data.graph.Graph
import ibu.edu.unitask.data.models.Task
import ibu.edu.unitask.data.repository.OfflineRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FinishedTasksViewModel (
    private val repository: OfflineRepository = Graph.repository
) : ViewModel() {

    var state by mutableStateOf(FinishedTasksUiState())
        private set

    init{
        getTasks()
    }

    private fun getTasks(){
        viewModelScope.launch {
            repository.getAllTasks().collectLatest {
                state = state.copy(tasks = it)
            }
        }
    }
}

data class FinishedTasksUiState(
    val tasks: List<Task> = emptyList()
)