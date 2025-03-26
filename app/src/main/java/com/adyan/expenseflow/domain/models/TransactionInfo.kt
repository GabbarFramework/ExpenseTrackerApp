
package com.adyan.expenseflow.domain.models

data class TransactionInfo(
    val transaction: Transaction?,
    val category:TransactionCategory?,
)