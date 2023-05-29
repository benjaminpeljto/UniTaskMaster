package ibu.edu.unitask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ibu.edu.unitask.data.models.Task

@Composable
fun CurrentTasks(
    taskList: List<Task>,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    onCheckedChange:(Task, Boolean) -> Unit,
    deleteTask:(Task) -> Unit,
    onEdit: (id: Int) -> Unit,
    onRequestDetails: (Int) -> Unit
) {
    Column(
    modifier = modifier
    .padding(padding)
    ) {

//*****************  Header  *****************//
        Header(day = "Today")

        Divider(thickness = 2.dp)

//*****************  TaskList  *****************//
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.fillMaxSize()
                .background(Color(0xFF121212))
        ) {
            items(taskList) { task ->
                TaskCard(
                    task = task,
                    isChecked = task.isFinished,
                    onCheckedChange = onCheckedChange,
                    onDelete = deleteTask,
                    modifier = modifier,
                    onEdit = onEdit,
                    onRequestDetails = onRequestDetails
                )
            }

        }

    }
}