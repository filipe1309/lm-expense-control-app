package com.filipe1309.expensecontrol

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filipe1309.expensecontrol.ui.theme.categories
import com.filipe1309.expensecontrol.ui.theme.toCurrency
import java.math.BigDecimal
import java.util.UUID

@Composable
fun TransactionCard(
    uuid: String,
    value: BigDecimal,
    category: String,
    date: String
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        TransactionInfoRow(category, date, value)
    }
}

@Composable
private fun TransactionInfoRow(category: String, date: String, value: BigDecimal) {
    Column(Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = categories.firstOrNull {
                    it.first == category
                }?.second ?: Icons.Filled.Lightbulb,
                contentDescription = "Category Icon"
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = category, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) // ContentAlpha.medium
                )
            }

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = value.toCurrency(),
                style = MaterialTheme.typography.bodyLarge,
                )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
        }
    }
}

@Preview
@Composable
fun Preview() {
    TransactionCard(
        uuid = UUID.randomUUID().toString(),
        value = BigDecimal(13.14),
        category = "Food",
        date = "2021-09-01"
    )
}