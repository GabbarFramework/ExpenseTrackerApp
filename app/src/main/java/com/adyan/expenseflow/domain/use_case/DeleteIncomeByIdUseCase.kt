
package com.adyan.expenseflow.domain.use_case

import com.adyan.expenseflow.domain.repository.IncomeRepository
import javax.inject.Inject

class DeleteIncomeByIdUseCase @Inject constructor(
    private val repository: IncomeRepository
) {
    suspend operator fun invoke(incomeId:String){
        return repository.deleteIncomeById(id = incomeId)
    }
}