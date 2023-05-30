package ibu.edu.unitask.ui.home

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ibu.edu.unitask.data.graph.Graph
import ibu.edu.unitask.data.models.Task
import ibu.edu.unitask.data.repository.OfflineRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date

class HomeViewModel(
    private val repository: OfflineRepository = Graph.repository
) : ViewModel() {

    var state by mutableStateOf(HomeUiState())
        private set


    //Whenever viewmodel initialized, get tasks
    init {
        getTasks()
    }

    private fun getTasks(){
        viewModelScope.launch {
            repository.getAllTasks().collectLatest {
                state = state.copy(tasks = it)
            }
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            repository.deleteTask(task)
        }
        closeDeleteDialog()
        denyDeletion()
    }

    fun onTaskCheckedChange(task: Task, isFinished: Boolean){
        viewModelScope.launch {
            repository.updateTask(
                task = task.copy(isFinished = isFinished)
            )
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch {
            repository.updateTask(task = task)
        }
    }

    fun assignTaskForEdit(id: Int){
        state = state.copy(taskForEditId = id)
    }

    fun openEditDialog(){
        state = state.copy(openEditDialog = true)
    }

    fun closeEditDialog(){
        state = state.copy(openEditDialog = false)
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

    fun assignTaskForDeletion(task: Task){
        state = state.copy(taskForDeletion = task)
    }


}

data class HomeUiState(
    val tasks: List<Task> = emptyList(),
    val openEditDialog: Boolean = false,
    val openDeleteDialog: Boolean = false,
    val taskForEditId: Int = -1,
    val confirmDelete: Boolean = false,
    val taskForDeletion: Task = Task(
        id = -1,
        title = "",
        course = "",
        description = "",
        dueDate = Date()
    ),
)