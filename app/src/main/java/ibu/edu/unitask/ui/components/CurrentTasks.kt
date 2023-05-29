package ibu.edu.unitask.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        .padding(bottom = 67.dp)
    ) {

//*****************  Header  *****************//
        Header(day = "Today")

        Divider(thickness = 2.dp)

//*****************  TaskList  *****************//
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            modifier = modifier
        ) {
            items(taskList) { task ->
                if(!task.isFinished) {
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
}