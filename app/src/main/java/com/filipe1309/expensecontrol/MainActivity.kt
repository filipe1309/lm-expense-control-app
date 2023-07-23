package com.filipe1309.expensecontrol

import android.os.Bundle
import android.view.SurfaceControl
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.filipe1309.expensecontrol.ui.theme.ExpenseControlTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseControlTheme {
                Column {
                    Welcome()
                    Transactions()
                }
            }
        }
    }
}

@Composable
fun Welcome(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Welcome back, \nFilipe!",
            style = MaterialTheme.typography.headlineLarge,
            modifier = modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "Clear Transactions",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun Transactions(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(transactionsDummy.size) { index ->
            Transaction(modifier, index)
        }
    }
}

@Composable
private fun Transaction(modifier: Modifier, index: Int) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Settings"
            )
            Spacer(modifier = modifier.padding(16.dp))
            Text(
                text = transactionsDummy[index],
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private val transactionsDummy = listOf<String>(
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
)