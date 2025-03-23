package com.adyan.expenseflow.domain.use_case

import com.adyan.expenseflow.domain.models.Expense
import com.adyan.expenseflow.domain.models.ExpenseCategory
import com.adyan.expenseflow.domain.models.TransactionInfo
import com.adyan.expenseflow.domain.repository.ExpenseCategoryRepository
import com.adyan.expenseflow.domain.repository.ExpenseRepository
import com.adyan.expenseflow.domain.toExternalModel
import javax.inject.Inject


data class ExpenseInfo(
    val expense: Expense?,
    val expenseCategory: ExpenseCategory,
)

class GetSingleExpenseUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    private val  expenseCategoryRepository: ExpenseCategoryRepository,
) {


    suspend operator fun invoke(expenseId:String): ExpenseInfo?{
        val expense = expenseRepository.getExpenseById(expenseId = expenseId)
        val expenseCategory = expense?.let {
            expenseCategoryRepository.getExpenseCategoryById(
                expenseCategoryId = it.expenseCategoryId
            )
        }
        if (expenseCategory != null) {
            return ExpenseInfo(
                expense = expense.toExternalModel(),
                expenseCategory = expenseCategory.toExternalModel(),
            )
        }else{
            return null
        }

    }
}