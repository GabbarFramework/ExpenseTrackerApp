
package com.adyan.expenseflow.domain.use_case

import com.adyan.expenseflow.core.room.entities.ExpenseCategoryEntity
import com.adyan.expenseflow.core.util.Resource
import com.adyan.expenseflow.domain.repository.ExpenseCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetAllExpenseCategoriesUseCase @Inject constructor(
    private val repository: ExpenseCategoryRepository
) {

    operator fun invoke():Flow<List<ExpenseCategoryEntity>>{
        return repository.getAllExpenseCategories()
    }
}