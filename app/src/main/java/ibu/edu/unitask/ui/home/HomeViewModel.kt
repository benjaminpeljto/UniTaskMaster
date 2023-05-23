package ibu.edu.unitask.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ibu.edu.unitask.data.graph.Graph
import ibu.edu.unitask.data.models.Task
import ibu.edu.unitask.data.repository.OfflineRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: OfflineRepository = Graph.repository
) : ViewModel() {

    var state = mutableStateOf(HomeUiState())
        private set

    //Whenever viewmodel initialized, get tasks
    init {
        getTasks()
    }

    private fun getTasks(){
        viewModelScope.launch {
            repository.getAllTasks().collectLatest {
                state.value = state.value.copy(
                    tasks = it
                )
            }
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun onTaskCheckedChange(task: Task, isFinished: Boolean){
        viewModelScope.launch {
            repository.updateTask(
                task = task.copy(isFinished = isFinished)
            )
        }
    }


}

data class HomeUiState(
    val tasks: List<Task> = emptyList(),

)