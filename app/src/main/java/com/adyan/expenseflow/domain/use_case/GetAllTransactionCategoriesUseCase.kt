
package com.adyan.expenseflow.domain.use_case

import com.adyan.expenseflow.core.room.entities.TransactionCategoryEntity
import com.adyan.expenseflow.domain.repository.TransactionCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTransactionCategoriesUseCase @Inject constructor(
    private val repository: TransactionCategoryRepository
){

    operator fun invoke(): Flow<List<TransactionCategoryEntity>> {
        return repository.getAllTransactionCategories()
    }
}