package com.filipe1309.expensecontrol

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal

class OverviewViewModel(
    private val repository: DummyRepository = DummyRepository()
) : ViewModel() {

    data class UiState(
        val advice: String = "Keep your expenses under control",
        val userName: String = "",
        val transactions: List<Transaction> = emptyList(),
        val total: BigDecimal = transactions.sumOf { it.value }
    )

    private val filter = MutableStateFlow<String?>(null)
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun addTransaction(transaction: Transaction) {
        repository.add(transaction)
        updateUiState()
    }

    fun clearTransactions() {
        repository.clearTransactions()
        updateUiState()
    }

    fun updateTransaction(transaction: Transaction) {
        repository.updateTransaction(transaction)
        updateUiState()
    }

    fun deleteTransaction(uuid: String) {
        repository.deleteTransaction(uuid)
        updateUiState()
    }

    fun findTransactions(uuid: String) = repository.findTransactions(uuid)

    fun filterByCategory(category: String) {
        filter.value = category
        updateUiState()
    }

    fun clearFilter() {
        filter.value = null
        updateUiState()
    }

    private fun updateUiState() {
        val transactionListSaved = repository.transactions
        val transactions = if (filter.value != null) {
            transactionListSaved.filter { it.category == filter.value }
        } else {
            transactionListSaved
        }
        _uiState.value = UiState(
            transactions = transactions,
            total = transactionListSaved.sumOf { it.value }
        )
    }
}
