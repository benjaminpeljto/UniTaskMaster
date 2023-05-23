package ibu.edu.unitask.ui.taskadd


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ibu.edu.unitask.R
import ibu.edu.unitask.data.models.Task
import ibu.edu.unitask.ui.components.datePickerDialog
import ibu.edu.unitask.ui.navigation.NavigationDestination
import ibu.edu.unitask.ui.navigation.UniTaskTopAppBar
import ibu.edu.unitask.ui.utils.DateFormatter
import java.util.Date

object AddNewTaskDestination : NavigationDestination {
    override val route = "add_task"
    override val titleRes = R.string.add_a_new_task
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewTaskScreen(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    onNavigateUp:() -> Unit
    ) {

    val viewModel = viewModel(modelClass = AddTaskViewModel::class.java)
    val addTaskUiState = viewModel.state
    Scaffold (
            topBar = {
                UniTaskTopAppBar(
                    title = stringResource(R.string.add_a_new_task_top_bar_text),
                    canNavigateBack = canNavigateBack,
                    navigateUp = onNavigateUp
                )
            }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = stringResource(R.string.add_a_new_task_here),
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(bottom = 16.dp, top = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                OutlinedTextField(
                    label = {
                        Text(text = stringResource(R.string.task_title))
                    },
                    value = addTaskUiState.taskTitle,
                    onValueChange = { text ->
                        viewModel.onTitleChange(text)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    label = {
                        Text(text = stringResource(R.string.task_description))
                    },
                    value = addTaskUiState.taskDescription,
                    onValueChange = {text ->
                        viewModel.onDescriptionChange(text)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    label = {
                        Text(text = stringResource(R.string.task_course))
                    },
                    value = addTaskUiState.taskCourse,
                    onValueChange = {text ->
                        viewModel.onCourseChange(text)
                    }
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null
                        )
                        Spacer(modifier = modifier.size(4.dp))
                        Text(text = DateFormatter(addTaskUiState.dueDate))
                        Spacer(modifier = modifier.size(4.dp))
                        val mDatePicker = datePickerDialog(
                            context = LocalContext.current,
                            onDateSelected = {
                                viewModel.onDateChange(it)
                            }
                        )
                        IconButton(onClick = {
                            mDatePicker.show()
                        }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }
                    }
                }



            }

            Column(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ){

                Button(onClick = {

                    viewModel.addTask(
                        Task(
                            title = addTaskUiState.taskTitle,
                            description = addTaskUiState.taskDescription,
                            dueDate = addTaskUiState.dueDate,
                            course = addTaskUiState.taskCourse
                        )
                    )

                    onNavigateUp()

                },
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .fillMaxWidth(),
                ) {
                    Text(text = stringResource(R.string.add_task))
                }
            }
        }
    }


}

@Preview
@Composable
fun AddNewTaskScreenPreview() {
    //AddNewTaskScreen(onTaskAdded = {})
}
