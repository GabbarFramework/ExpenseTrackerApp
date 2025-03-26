
package com.adyan.expenseflow.domain.models

import com.adyan.expenseflow.core.room.entities.ExpenseEntity
import kotlinx.coroutines.flow.Flow

data class ExpenseCategoryInfoEntity(
    val expenseCategory:ExpenseCategory,
    val expenses: Flow<List<ExpenseEntity>>
)

data class ExpenseCategoryInfo(
    val expenseCategory:ExpenseCategory,
    val expenses: Flow<List<Expense>>
)