package ibu.edu.unitask.ui.info

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ibu.edu.unitask.R
import ibu.edu.unitask.ui.navigation.NavigationDestination
import ibu.edu.unitask.ui.navigation.UniTaskTopAppBar

object InfoDestination : NavigationDestination{
    override val route = "info"
    override val titleRes = R.string.about_the_app
    override val icon = Icons.Default.Info
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InfoScreen(
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            UniTaskTopAppBar(title = stringResource(InfoDestination.titleRes), canNavigateBack = false)
        }
    ) {
        Column(
            modifier = modifier.background(androidx.compose.ui.graphics.Color(0xFFD9D0DE))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Made by Dino Kršo and Benajmin Peljto with mentorship by Naida Fatić " +
                    "and professor Zerina Mašetić" , style= MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center)
        }
    }
}