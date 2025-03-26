
package com.adyan.expenseflow.domain.use_case

import com.adyan.expenseflow.core.room.entities.IncomeEntity
import com.adyan.expenseflow.domain.repository.IncomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIncomeByIdUseCase @Inject constructor(
    private val repository: IncomeRepository
) {

    operator fun invoke(incomeId:String): Flow<IncomeEntity?> {
        return repository.getIncomeById(id = incomeId)
    }
}