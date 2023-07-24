package com.filipe1309.expensecontrol

import java.math.BigDecimal
import java.util.Date
import java.util.UUID

data class Transaction(
    val uuid: String = UUID.randomUUID().toString(),
    val category: String = "",
    val value: BigDecimal = BigDecimal.ZERO,
    val date: Date = Date()
)
