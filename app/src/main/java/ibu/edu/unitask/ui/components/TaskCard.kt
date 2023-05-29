package ibu.edu.unitask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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
) {
    Surface(
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(20.dp))


    ) {

        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE3F4F4)
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
                        onCheckedChange = {
                            onCheckedChange(task, it)
                        }
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        val textDecoration =
                            if (isChecked) {
                                TextDecoration.LineThrough
                            } else {
                                TextDecoration.None
                            }

                        val textStyleTitle = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textDecoration = textDecoration
                        )

                        val textStyleDescription = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp
                        )

                        Text(
                            text = task.title,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
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
                            modifier = modifier.padding(end = 10.dp)

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

                            modifier = Modifier.clickable { onEdit.invoke(task.id) }
                                .padding(end = 10.dp)
                        )

                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete task button",
                            modifier = Modifier.clickable { onDelete.invoke(task) }
                                .padding(end = 10.dp),
                            tint = Color.Red
                        )

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
        onEdit = {}
    )
}