package ibu.edu.unitask.ui.delete_task_alert

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ibu.edu.unitask.R

@Composable
fun DeleteTaskAlertDialog(
    onDelete:() -> Unit,
    onDismissRequest:() -> Unit
){
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = stringResource(R.string.delete_task_dialog_title))
        },
        text = {
            Text(stringResource(R.string.are_you_sure))
        },
        confirmButton = {
            Button(

                onClick = onDelete
            ) {
                Text(stringResource(R.string.delete_button))
            }
        },
        dismissButton = {
            Button(

                onClick = onDismissRequest
            ) {
                Text(stringResource(R.string.close_button))
            }
        }
    )
}