package com.filipe1309.expensecontrol

class DummyRepository {
    private var _transactions= mutableListOf<Transaction>()
    val transactions get() = _transactions.toList()

    fun add(transaction: Transaction) {
        _transactions.add(transaction)
    }

    fun clearTransactions() {
        _transactions.clear()
    }

    fun updateTransaction(transaction: Transaction) {
        deleteTransaction(transaction.uuid)
        _transactions.add(transaction)
    }

    fun deleteTransaction(uuid: String) {
        _transactions.removeIf { it.uuid == uuid }
    }

    fun findTransactions(uuid: String): Transaction {
       return _transactions.firstOrNull { it.uuid == uuid } ?: Transaction()
    }
}