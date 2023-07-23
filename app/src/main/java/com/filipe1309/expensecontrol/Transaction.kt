package com.filipe1309.expensecontrol

import java.math.BigDecimal

data class Transaction(
    val uuid: String,
    val description: String,
    val value: BigDecimal,
    val category: String,
)
