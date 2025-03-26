
package com.adyan.expenseflow.domain.use_case

import com.adyan.expenseflow.core.util.Resource
import com.adyan.expenseflow.core.util.Response
import com.adyan.expenseflow.domain.models.Income
import com.adyan.expenseflow.domain.repository.IncomeRepository
import com.adyan.expenseflow.domain.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CreateIncomeUseCase @Inject constructor(
    private val incomeRepository: IncomeRepository,
) {
    suspend operator fun invoke(income: Income): Flow<Resource<Response>> = flow {
        try {
            emit(Resource.Loading())
            incomeRepository.insertIncome(incomeEntity = income.toEntity())
            emit(Resource.Success(data = Response(msg = "Income saved successfully")))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}