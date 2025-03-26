
package com.adyan.expenseflow.domain.use_case

import com.adyan.expenseflow.core.room.entities.TransactionEntity
import com.adyan.expenseflow.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionsForACertainDayUseCase @Inject constructor(
    private val repository:TransactionRepository
) {

    operator fun invoke(date:String): Flow<List<TransactionEntity>> {
        return repository.getTransactionsForACertainDay(date = date)

    }
}