package com.adyan.expenseflow.domain.use_case

import com.adyan.expenseflow.domain.repository.ExpenseRepository
import javax.inject.Inject

class DeleteExpenseByIdUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository,
){
    suspend operator fun invoke(expenseId:String){
        expenseRepository.deleteExpenseById(expenseId)
    }
}