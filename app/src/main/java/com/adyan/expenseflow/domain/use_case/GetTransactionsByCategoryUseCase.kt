
package com.adyan.expenseflow.domain.use_case


import com.adyan.expenseflow.core.room.entities.TransactionEntity
import com.adyan.expenseflow.core.util.FilterConstants
import com.adyan.expenseflow.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionsByCategoryUseCase @Inject constructor(
    private val getFilteredTransactionsUseCase: GetFilteredTransactionsUseCase,
    private val repository: TransactionRepository
) {
    operator fun invoke(categoryId: String): Flow<List<TransactionEntity>> {
        if (categoryId == FilterConstants.ALL){
            return getFilteredTransactionsUseCase(filter = FilterConstants.ALL)
        }else{
            return repository.getTransactionByCategory(categoryId = categoryId)
        }
    }
}