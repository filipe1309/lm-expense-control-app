package com.filipe1309.expensecontrol

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
    val listState = rememberLazyListState()
    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }
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
                Row(
                    Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                    AnimatedVisibility(visible = !showButton) {
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "Add"
                        )
                    }
                }
            }
        }
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(),
            exit = slideOutVertically { fullHeight -> fullHeight } + fadeOut(),
        ) {
            Box(Modifier.fillMaxSize()) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.extraLarge
                        ),
                    onClick = {
                        scope.launch {
                            listState.animateScrollToItem(0)
                        }
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowCircleUp,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "Scroll to top",
                    )
                }
            }
        }
    }
}
