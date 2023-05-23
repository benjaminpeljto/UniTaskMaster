package ibu.edu.unitask.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ibu.edu.unitask.R
import ibu.edu.unitask.UniTaskApp
import ibu.edu.unitask.data.models.Task
import ibu.edu.unitask.ui.components.AllTasksCompleted
import ibu.edu.unitask.ui.components.Header
import ibu.edu.unitask.ui.components.TaskCard
import ibu.edu.unitask.ui.navigation.NavigationDestination
import ibu.edu.unitask.ui.navigation.UniTaskTopAppBar
import java.text.SimpleDateFormat



object HomeDestination : NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.unitask_home
}





@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToAddTask: () -> Unit
) {

    val viewModel = viewModel(modelClass = HomeViewModel::class.java)
    val homeUiState = viewModel.state
Scaffold (
    topBar = {
             UniTaskTopAppBar(
                 title = stringResource(R.string.unitaskmanager_home_top_bar),
                 canNavigateBack = false
             )
    },
    floatingActionButton = {
        FloatingActionButton(
            onClick = navigateToAddTask,
            modifier = Modifier.navigationBarsPadding()
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    },
        ){innerPadding ->
    if(homeUiState.value.tasks.isEmpty()){
        AllTasksCompleted()
    }
    else {

        Column(
            modifier = modifier
                .padding(innerPadding)
        ) {

//*****************  Header  *****************//
            Header(day = "Today")

//*****************  TaskList  *****************//
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
            ) {
                items(homeUiState.value.tasks) { task ->
                    TaskCard(
                        task = task,
                        isChecked = task.isFinished,
                        onCheckedChange = { task, finished ->
                            viewModel.onTaskCheckedChange(task,!finished)
                        },
                        onDelete = {
                            viewModel.deleteTask(it)
                        },
                    )
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


