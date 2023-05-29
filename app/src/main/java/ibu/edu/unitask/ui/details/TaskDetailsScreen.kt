package ibu.edu.unitask.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ibu.edu.unitask.R
import ibu.edu.unitask.data.models.Task
import ibu.edu.unitask.ui.navigation.NavigationDestination
import ibu.edu.unitask.ui.navigation.UniTaskTopAppBar
import ibu.edu.unitask.ui.utils.DateFormatter
import java.util.Calendar

object TaskDetailsDestination : NavigationDestination{
    override val route = "task_details/{id}"
    override val titleRes = R.string.task_no
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(
    taskId: Int,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    onNavigateBack: () -> Unit
) {
    val viewModel = viewModel(modelClass = TaskDetailsViewModel::class.java)
    val taskDetailsUiState = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.getTask(taskId)
    }

    Scaffold(
        topBar = {
            UniTaskTopAppBar(
                title = stringResource(TaskDetailsDestination.titleRes) + taskDetailsUiState.task.id.toString(),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateBack
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(bottom = 50.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Task Details", fontSize = 40.sp)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val status: String = if(taskDetailsUiState.task.isFinished) "Done" else "Not done"
                Checkbox(checked = taskDetailsUiState.task.isFinished, onCheckedChange = {})
                Text(text = "Status: ")
                Text(text = status, fontWeight = FontWeight.Bold)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = null)
                Text(text = "Title: ")
                TextField(value = taskDetailsUiState.task.title, onValueChange = {}, readOnly = true)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = null)
                Text(text = "Description: ")
                TextField(value = taskDetailsUiState.task.description, onValueChange = {}, readOnly = true)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = null)
                Text(text = "Course: ")
                TextField(value = taskDetailsUiState.task.course, onValueChange = {}, readOnly = true)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = null)
                Text(text = "Due date: ")
                TextField(value = DateFormatter(taskDetailsUiState.task.dueDate), onValueChange = {}, readOnly = true)
            }



        }
    }
}

@Preview
@Composable
fun TaskDetailsPreview() {
    val currentDate = Calendar.getInstance().time
    val task = Task(
        id = 5,
        title = "Sample Task",
        description = "This is a sample task description",
        dueDate = currentDate,
        course = "Maths",
        isFinished = true
    )
}

