
package com.adyan.expenseflow.domain.use_case

import com.adyan.expenseflow.domain.models.Income
import com.adyan.expenseflow.domain.repository.IncomeRepository
import com.adyan.expenseflow.domain.toExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllIncomeUseCase @Inject constructor(
    private val repository: IncomeRepository
) {

    operator fun invoke(): Flow<List<Income>> {
        return repository.getAllIncome().map { it.map { it.toExternalModel() } }
    }
}