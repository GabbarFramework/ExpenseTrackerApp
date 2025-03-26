
package com.adyan.expenseflow.domain.use_case

import com.adyan.expenseflow.core.util.Resource
import com.adyan.expenseflow.core.util.Response
import com.adyan.expenseflow.domain.models.Expense
import com.adyan.expenseflow.domain.models.Transaction
import com.adyan.expenseflow.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(transaction: Transaction): Flow<Resource<Response>> = flow {
        try {
            emit(Resource.Loading())
            repository.createTransaction(transaction = transaction)
            emit(Resource.Success(data = Response(msg = "Transaction saved successfully")))
        }catch (e:IOException){
            emit(Resource.Error(message = e.localizedMessage?: "An unexpected error occurred"))
        }
    }
}