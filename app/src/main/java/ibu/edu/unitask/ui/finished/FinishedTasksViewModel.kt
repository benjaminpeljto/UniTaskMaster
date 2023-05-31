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
import java.util.Date

class FinishedTasksViewModel (
    private val repository: OfflineRepository = Graph.repository
) : ViewModel() {

    var state by mutableStateOf(FinishedTasksUiState())
        private set

    init{
        getTasks()
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            repository.deleteTask(task)
        }
        closeDeleteDialog()
        denyDeletion()
    }

    private fun getTasks(){
        viewModelScope.launch {
            repository.getAllTasks().collectLatest {
                state = state.copy(tasks = it)
            }
        }
    }

    fun assignTaskForDeletion(task: Task){
        state = state.copy(taskForDeletion = task)
    }

    fun openDeleteDialog(){
        state = state.copy(openDeleteDialog = true)
    }

    fun closeDeleteDialog(){
        state = state.copy(openDeleteDialog = false)
    }

    fun confirmDeletion(){
        state = state.copy(confirmDelete = true)
    }

    fun denyDeletion(){
        state = state.copy(confirmDelete = false)
    }
}

data class FinishedTasksUiState(
    val tasks: List<Task> = emptyList(),
    val openDeleteDialog: Boolean = false,
    val confirmDelete: Boolean = false,
    val taskForDeletion: Task = Task(
        id = -1,
        title = "",
        course = "",
        description = "",
        dueDate = Date()
    ),
)