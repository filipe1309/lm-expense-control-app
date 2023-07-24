package com.filipe1309.expensecontrol

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.filipe1309.expensecontrol.ui.theme.formatDate
import com.filipe1309.expensecontrol.ui.theme.randomTransaction
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(viewModel: OverviewViewModel = viewModel()) {

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(top = 32.dp, bottom = 32.dp),
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
                title = {
                    Text(
                        text = "Welcome back, \nFilipe",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.ClearAll,
                            contentDescription = "Clear",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.addTransaction(randomTransaction())
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val listState = rememberLazyListState()
        val scope = rememberCoroutineScope()
        Column(Modifier.padding(it)) {
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                text = "Transactions"
            )
            LazyColumn(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
                state = listState
            ) {
                items(uiState.transactions) { transaction ->
                    TransactionCard(
                        uuid = transaction.uuid,
                        value = transaction.value,
                        category = transaction.category,
                        date = transaction.date.formatDate()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    IconButton(onClick = {
                        scope.launch {
                            listState.scrollToItem(0)
                        }
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowCircleUp, contentDescription = "Scroll to top")
                    }
                }
            }
        }
    }
}
