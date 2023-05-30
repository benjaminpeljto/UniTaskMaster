package ibu.edu.unitask.ui.finished

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ibu.edu.unitask.R
import ibu.edu.unitask.ui.components.TaskCard
import ibu.edu.unitask.ui.navigation.NavigationDestination
import ibu.edu.unitask.ui.navigation.UniTaskTopAppBar


object FinishedTasksDestination : NavigationDestination{
    override val route = "finished"
    override val titleRes = R.string.finished_tasks
    override val icon = Icons.Default.Star

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinishedTasksScreen(
    modifier: Modifier = Modifier,
    onRequestDetails:(Int) -> Unit
) {
    val viewModel = viewModel(modelClass = FinishedTasksViewModel::class.java)
    val finishedTasksUiState = viewModel.state.tasks
    Scaffold(
        topBar = {
            UniTaskTopAppBar(
                title = stringResource(id = FinishedTasksDestination.titleRes),
                canNavigateBack = false,
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
        ) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
            ) {
                items(finishedTasksUiState) { task ->
                    if(task.isFinished) {
                        TaskCard(
                            task = task,
                            isChecked = task.isFinished,
                            onCheckedChange = {_,_ -> },
                            onDelete = {},
                            modifier = modifier,
                            onEdit = {},
                            onRequestDetails = onRequestDetails
                        )
                    }
                }

            }

        }
    }
}