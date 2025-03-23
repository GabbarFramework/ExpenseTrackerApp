package com.adyan.expenseflow.presentation.screens.settings

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.adyan.expenseflow.BuildConfig
import com.adyan.expenseflow.core.util.Constants
import com.adyan.expenseflow.core.util.getAppVersionName
import com.adyan.expenseflow.presentation.alertDialogs.ThemeDialog
import com.adyan.expenseflow.presentation.components.SettingsRow

@Composable
fun SettingsScreen(
    viewModel: SettingsScreenViewModel = hiltViewModel(),
    navigateToAboutScreen: () -> Unit,

    ) {
    val theme by viewModel.theme.collectAsStateWithLifecycle()
    val uiState by viewModel.settingsScreenUiState.collectAsStateWithLifecycle()
    SettingsScreenContent(
        theme = theme,
        uiState = uiState,
        updateTheme = { viewModel.updateTheme(it) },
        toggleThemeDialogVisibility = viewModel::toggleThemeDialogVisibility,
        navigateToAboutScreen = navigateToAboutScreen
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenContent(
    theme: String,
    uiState: SettingsScreenUiState,
    navigateToAboutScreen: () -> Unit,
    toggleThemeDialogVisibility: () -> Unit,
    updateTheme: (String) -> Unit,
) {

    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        style = TextStyle(color = MaterialTheme.colorScheme.primary),
                        text = "Settings",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    scrolledContainerColor = MaterialTheme.colorScheme.onBackground,
                    navigationIconContentColor = MaterialTheme.colorScheme.background,
                    actionIconContentColor = MaterialTheme.colorScheme.background,
                )
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            if (uiState.isThemeDialogVisible) {
                ThemeDialog(
                    changeTheme = updateTheme,
                    toggleThemeDialog = toggleThemeDialogVisibility,
                    currentTheme = theme
                )
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    SettingsRow(
                        title = "About",
                        onClick = navigateToAboutScreen
                    )
                }
                item {
                    SettingsRow(
                        title = "Dark Theme",
                        onClick = toggleThemeDialogVisibility
                    )
                }

            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "App Version: ${getAppVersionName(context)}",
                    modifier = Modifier,
                    style = TextStyle(color = MaterialTheme.colorScheme.primary),
                    fontSize = 11.sp
                )
                Text(
                    text = "Build Type: ${BuildConfig.BUILD_TYPE}",
                    modifier = Modifier,
                    style = TextStyle(color = MaterialTheme.colorScheme.primary),
                    fontSize = 11.sp
                )
                Text(
                    text = "Made with ❤️ by Adyan Shaikh",
                    modifier = Modifier,
                    style = TextStyle(color = MaterialTheme.colorScheme.primary),
                    fontSize = 12.sp
                )
            }
        }
    }

}

@Preview
@Composable
fun SettingsScreenContentPreview() {
    SettingsScreenContent(
        theme = Constants.DARK_MODE,
        uiState = SettingsScreenUiState(),
        navigateToAboutScreen = {},
        toggleThemeDialogVisibility = {},
        updateTheme = {}
    )
}