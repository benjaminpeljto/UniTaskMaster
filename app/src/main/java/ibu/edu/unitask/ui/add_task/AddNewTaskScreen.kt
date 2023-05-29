package ibu.edu.unitask.ui.add_task


import androidx.compose.foundation.background
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    title = stringResource(AddNewTaskDestination.titleRes),
                    canNavigateBack = canNavigateBack,
                    navigateUp = onNavigateUp
                )
            }
    ){innerPadding ->
        Column(
            modifier = Modifier.background(Color(0xFFF8F6F4))
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
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )

                OutlinedTextField(
                    label = {
                        Text(text = stringResource(R.string.task_title),
                                style = MaterialTheme.typography.headlineLarge)
                    },
                    value = addTaskUiState.taskTitle,
                    onValueChange = { text ->
                        viewModel.onTitleChange(text)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    label = {
                        Text(text = stringResource(R.string.task_description),
                            style = MaterialTheme.typography.headlineLarge)
                    },
                    value = addTaskUiState.taskDescription,
                    onValueChange = {text ->
                        viewModel.onDescriptionChange(text)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    label = {
                        Text(text = stringResource(R.string.task_course),
                            style = MaterialTheme.typography.headlineLarge)
                    },
                    value = addTaskUiState.taskCourse,
                    onValueChange = {text ->
                        viewModel.onCourseChange(text)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))


                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            modifier = modifier
                                .size(40.dp)
                        )
                        Spacer(modifier = modifier.size(10.dp))
                        Text(
                            text = DateFormatter(addTaskUiState.dueDate),
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Spacer(modifier = modifier.size(10.dp))
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
                    .padding(bottom = 16.dp, start = 20.dp, end = 20.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ){

                Button(onClick = {
                    viewModel.addTask(
                        Task(
                            title = addTaskUiState.taskTitle,
                            description = addTaskUiState.taskDescription,
                            dueDate = addTaskUiState.dueDate,
                            course = addTaskUiState.taskCourse.uppercase(),

                        )
                    )

                    onNavigateUp()

                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF899C9C)),
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
