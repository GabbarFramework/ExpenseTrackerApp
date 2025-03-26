
package com.adyan.expenseflow.domain.repository

import com.adyan.expenseflow.core.room.entities.ExpenseEntity
import com.adyan.expenseflow.domain.models.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {

    suspend fun createExpense(expense: Expense)

    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    suspend fun getExpenseById(expenseId:String):ExpenseEntity?

    suspend fun deleteExpenseById(expenseId:String)

    suspend fun updateExpense(
        expenseName:String,
        expenseAmount:Int,
        expenseUpdatedAt:String,
        expenseUpdatedOn:String,
    )

    fun getExpensesByCategory(categoryId:String): Flow<List<ExpenseEntity>>

}