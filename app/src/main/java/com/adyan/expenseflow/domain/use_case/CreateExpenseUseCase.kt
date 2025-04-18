
package com.adyan.expenseflow.domain.use_case

import com.adyan.expenseflow.core.util.Resource
import com.adyan.expenseflow.core.util.Response
import com.adyan.expenseflow.domain.models.Expense
import com.adyan.expenseflow.domain.models.ExpenseCategory
import com.adyan.expenseflow.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class CreateExpenseUseCase @Inject constructor(
  private val repository: ExpenseRepository
) {
    suspend operator fun invoke(expense: Expense): Flow<Resource<Response>> = flow {
        try {
            emit(Resource.Loading())
            repository.createExpense(expense = expense)
            emit(Resource.Success(data = Response(msg = "Expense saved successfully")))
        }catch (e:IOException){
            emit(Resource.Error(message = e.localizedMessage?: "An unexpected error occurred"))
        }
    }
}