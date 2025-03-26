
package com.adyan.expenseflow.domain.use_case

import com.adyan.expenseflow.core.room.entities.ExpenseEntity
import com.adyan.expenseflow.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllExpensesUseCase @Inject constructor(
    private val repository:ExpenseRepository,
) {
    operator fun invoke(): Flow<List<ExpenseEntity>> {
        return repository.getAllExpenses()
    }
}