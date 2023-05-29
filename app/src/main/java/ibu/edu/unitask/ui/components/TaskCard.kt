package ibu.edu.unitask.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ibu.edu.unitask.data.models.Task
import ibu.edu.unitask.ui.utils.DateFormatter
import ibu.edu.unitask.ui.utils.DateTester
import java.util.Calendar

@Composable
fun TaskCard(
    task: Task,
    onCheckedChange: (Task, Boolean) -> Unit,
    onDelete: (Task) -> Unit,
    onEdit: (Int) -> Unit,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onRequestDetails: (Int) -> Unit
) {
    Surface(
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
            //.shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp), clip = true)


    ) {


        Card(

            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF282828)
             ),
            modifier = modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(20.dp),
                    clip = true
                )
    var isVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(durationMillis = 500)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(durationMillis = 500)
        )
    ) {
        Surface(
            shadowElevation = 4.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(20.dp))


        ) {

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFD2E9E9)
                ),
                modifier = modifier.fillMaxWidth()
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)

                ) {
                    Checkbox(
                        checked = isChecked,
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = Color(0xFFFFFFF7)
                        ),
                        onCheckedChange = {
                            onCheckedChange(task, it)
                        }
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onRequestDetails.invoke(task.id) },
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = task.title,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFF7)
                        )

                    }
                    Column(
                        modifier = modifier
                            .padding(end = 5.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(
                            text = "Due ",
                            fontSize = 15.sp,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Left,
                            modifier = modifier.padding(end = 10.dp),
                            color = Color(0xFFFFFFF7)

                        )
                        Text(
                            text = DateFormatter(task.dueDate),
                            color = DateTester(task.dueDate, Calendar.getInstance().time),
                            fontSize = 15.sp,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = modifier.padding(end = 10.dp)
                        )

                    }
                    Row() {


                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit task button",

                            modifier = Modifier
                                .clickable { onEdit.invoke(task.id) }
                                .padding(end = 10.dp),
                            tint = Color(0xFFFFFFF7)
                        )

                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete task button",
                            modifier = Modifier
                                .clickable { onDelete.invoke(task) }
                                .padding(end = 10.dp),
                            tint = Color(0xFFD11A2A)
                        )

                    }

                }
            }
        }
    }
}


@Preview
@Composable
fun TaskCardPreview() {
    val currentDate = Calendar.getInstance().time
    val task = Task(
        title = "Sample Task",
        description = "This is a sample task description",
        dueDate = currentDate,
        course = "Maths"
    )

    TaskCard(
        task = task,
        onCheckedChange = { _, _ -> /* Handle checked change */ },
        onDelete = { /* Handle delete task */ },
        isChecked = false,
        modifier = Modifier,
        onEdit = {},
        onRequestDetails = {}
    )
}