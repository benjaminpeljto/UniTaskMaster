package ibu.edu.unitask.ui.details

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
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
import androidx.compose.ui.platform.LocalClipboardManager


object TaskDetailsDestination : NavigationDestination{
    override val route = "task_details/{id}"
    override val titleRes = R.string.task_no
    override val icon = Icons.Default.ArrowForward
}

@SuppressLint("ServiceCast")
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
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    LaunchedEffect(key1 = true) {
        viewModel.getTask(taskId)
    }
    Box(
        modifier = modifier
            .fillMaxSize()

    ) {
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
                    .background(Color(0xFFD9D0DE))
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
                    Text(text = "Task Details",
                        fontSize = 40.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val status: String =
                        if (taskDetailsUiState.task.isFinished) "Done" else "Not done"
                    Checkbox(checked = taskDetailsUiState.task.isFinished, onCheckedChange = {})
                    Text(text = "Status: ",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(text = status,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.background(Color.Transparent)
                ) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = null)
                    Text(
                        text = "Title: ",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = taskDetailsUiState.task.title,
                    )
                    Spacer(modifier = Modifier.weight(1f)) // Add a spacer to push the icon to the end
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_content_copy_24),
                        contentDescription = "copy",
                        modifier = Modifier
                            .clickable {
                                val annotatedString = buildAnnotatedString {
                                    append(taskDetailsUiState.task.title)
                                }

                                clipboardManager.setText(annotatedString)
                                Toast
                                    .makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .padding(end = 10.dp)
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically),
                        tint = Color(0xFFFFFFF7)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_description_24), contentDescription = null)
                    Text(text = "Description: ",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                            text = taskDetailsUiState.task.description,
                    )
                    Spacer(modifier = Modifier.weight(1f)) // Add a spacer to push the icon to the end
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_content_copy_24),
                        contentDescription = "copy",
                        modifier = Modifier
                            .clickable {
                                val annotatedString = buildAnnotatedString {
                                    append(taskDetailsUiState.task.description)
                                }

                                clipboardManager.setText(annotatedString)
                                Toast
                                    .makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .padding(end = 10.dp)
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically),
                        tint = Color(0xFFFFFFF7)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_subject_24),
                        contentDescription = null
                    )
                    Text(text = "Course: ",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = taskDetailsUiState.task.course,
                    )
                    Spacer(modifier = Modifier.weight(1f)) // Add a spacer to push the icon to the end
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_content_copy_24),
                        contentDescription = "copy",
                        modifier = Modifier
                            .clickable {
                                val annotatedString = buildAnnotatedString {
                                    append(taskDetailsUiState.task.course)
                                }

                                clipboardManager.setText(annotatedString)
                                Toast
                                    .makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .padding(end = 10.dp)
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically),
                        tint = Color(0xFFFFFFF7)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.DateRange, contentDescription = null)
                    Text(text = "Due date: ",
                        style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = DateFormatter(taskDetailsUiState.task.dueDate),
                    )
                    Spacer(modifier = Modifier.weight(1f)) // Add a spacer to push the icon to the end
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_content_copy_24),
                        contentDescription = "copy",
                        modifier = Modifier
                            .clickable {
                                val annotatedString = buildAnnotatedString {
                                    append(DateFormatter(taskDetailsUiState.task.dueDate))
                                }

                                clipboardManager.setText(annotatedString)
                                Toast
                                    .makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .padding(end = 10.dp)
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically),
                        tint = Color(0xFFFFFFF7)
                    )
                }


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

