package ibu.edu.unitask.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ibu.edu.unitask.R
import ibu.edu.unitask.data.models.Task
import ibu.edu.unitask.ui.components.AllTasksCompleted
import ibu.edu.unitask.ui.components.CurrentTasks
import ibu.edu.unitask.ui.delete_task_alert.DeleteTaskAlertDialog
import ibu.edu.unitask.ui.edit_task.EditTaskAlertDialog
import ibu.edu.unitask.ui.navigation.NavigationDestination
import ibu.edu.unitask.ui.navigation.UniTaskTopAppBar
import java.text.SimpleDateFormat



object HomeDestination : NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.unitaskmanager_home_top_bar
    override val icon = Icons.Default.Home
}





@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToAddTask: () -> Unit,
    navigateToDetails: (Int) -> Unit,
) {

    val viewModel = viewModel(modelClass = HomeViewModel::class.java)
    val homeUiState = viewModel.state

    if (homeUiState.confirmDelete) {
    if(homeUiState.confirmDelete){
        val idDeleted = homeUiState.taskForDeletion.id
        viewModel.deleteTask(homeUiState.taskForDeletion)
        Toast.makeText(LocalContext.current, "Task no. $idDeleted deleted successfully!", Toast.LENGTH_SHORT).show()
    }

    /*<<<<<<< HEAD ======= */
    Box(
        modifier = modifier.fillMaxSize()
            .background(Color.Black)
    ) {
        Scaffold(
            topBar = {
                UniTaskTopAppBar(
                    title = stringResource(HomeDestination.titleRes),
                    canNavigateBack = false
                )
Scaffold (
    topBar = {
             UniTaskTopAppBar(
                 title = stringResource(HomeDestination.titleRes),
                 canNavigateBack = false
             )
    },
    floatingActionButton = {
        FloatingActionButton(
            onClick = navigateToAddTask,
            modifier = modifier
                .navigationBarsPadding()
                .offset(y = (-54).dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    },
        ){innerPadding ->
    if(homeUiState.tasks.isEmpty()){
        AllTasksCompleted()
    }
    else {
        CurrentTasks(
            taskList =homeUiState.tasks,
            padding = innerPadding,
            onCheckedChange = { task, finished ->
                viewModel.onTaskCheckedChange(task, finished)
            },
            deleteTask ={
                viewModel.openDeleteDialog()
                viewModel.assignTaskForDeletion(it)
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = navigateToAddTask,
                    modifier = modifier.navigationBarsPadding(),
                    containerColor = Color(0xFF320064)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
        ) { innerPadding ->
            if (homeUiState.tasks.isEmpty()) {
                AllTasksCompleted()
            } else {
                CurrentTasks(
                    taskList = homeUiState.tasks,
                    padding = innerPadding,
                    onCheckedChange = { task, finished ->
                        viewModel.onTaskCheckedChange(task, finished)
                    },
                    deleteTask = {
                        viewModel.openDeleteDialog()
                        viewModel.assignTaskForDeletion(it)
                    },
                    onEdit = {
                        viewModel.assignTaskForEdit(it)
                        viewModel.openEditDialog()
                    },
                    onRequestDetails = navigateToDetails
                )
                /* >>>>>>> origin/master */

                Scaffold(
                    topBar = {
                        UniTaskTopAppBar(
                            title = stringResource(R.string.unitaskmanager_home_top_bar),
                            canNavigateBack = false
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = navigateToAddTask,
                            modifier = modifier.navigationBarsPadding()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    },
                ) { innerPadding ->
                    if (homeUiState.tasks.isEmpty()) {
                        AllTasksCompleted()
                    } else {
                        CurrentTasks(
                            taskList = homeUiState.tasks,
                            padding = innerPadding,
                            onCheckedChange = { task, finished ->
                                viewModel.onTaskCheckedChange(task, finished)
                            },
                            deleteTask = {
                                viewModel.openDeleteDialog()
                                viewModel.assignTaskForDeletion(it)
                            },
                            onEdit = {
                                viewModel.assignTaskForEdit(it)
                                viewModel.openEditDialog()
                            },
                            onRequestDetails = navigateToDetails

                        )

                        if (homeUiState.openEditDialog) {
                            EditTaskAlertDialog(
                                onDismiss = {
                                    viewModel.closeEditDialog()
                                },
                                onConfirm = {
                                    viewModel.updateTask(it)
                                    viewModel.closeEditDialog()
                                },
                                taskForEditId = homeUiState.taskForEditId
                            )
                        }
                        if (homeUiState.openDeleteDialog) {
                            DeleteTaskAlertDialog(
                                onDelete = {
                                    viewModel.confirmDeletion()
                                    viewModel.closeDeleteDialog()
                                },
                                onDismissRequest = { viewModel.closeDeleteDialog() }
                            )
                        }
                    }
                }
            }
        }
    }
}





@Preview
@Composable
fun HomeScreenPreview() {
    val emptlyList = emptyList<Task>()
    val tasksList = listOf(
        Task(
            id = 1,
            title = "Task 1",
            description = "Description 1",
            course = "Course 1",
            dueDate = SimpleDateFormat("dd.MM.yyyy").parse("22.02.2023")!!,
            isFinished = false
        ),
        Task(
            id = 2,
            title = "Task 2",
            description = "Description 2",
            course = "Course 2",
            dueDate = SimpleDateFormat("dd.MM.yyyy").parse("22.02.2023")!!,
            isFinished = false
        ),
        Task(
            id = 3,
            title = "Task 3",
            description = "Description 3",
            course = "Course 3",
            dueDate = SimpleDateFormat("dd.MM.yyyy").parse("22.02.2023")!!,
            isFinished = false
        )
    )


}


