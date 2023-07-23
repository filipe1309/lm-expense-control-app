package com.filipe1309.expensecontrol

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun OverviewScreen(viewModel: OverviewViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
}
