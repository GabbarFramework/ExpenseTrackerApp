
package com.adyan.expenseflow.domain.use_case

import com.adyan.expenseflow.core.room.entities.ExpenseEntity
import com.adyan.expenseflow.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExpensesByCategoryUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {

    operator fun invoke(expenseCategoryId:String): Flow<List<ExpenseEntity>> {
        return repository.getExpensesByCategory(categoryId = expenseCategoryId)
    }



}