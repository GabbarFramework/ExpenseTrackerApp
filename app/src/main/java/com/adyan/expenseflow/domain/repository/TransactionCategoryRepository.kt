
package com.adyan.expenseflow.domain.repository

import com.adyan.expenseflow.core.room.entities.ExpenseCategoryEntity
import com.adyan.expenseflow.core.room.entities.TransactionCategoryEntity
import com.adyan.expenseflow.domain.models.ExpenseCategory
import com.adyan.expenseflow.domain.models.TransactionCategory
import kotlinx.coroutines.flow.Flow

interface TransactionCategoryRepository {

    suspend fun saveTransactionCategory(transactionCategory: TransactionCategory)

    fun getAllTransactionCategories(): Flow<List<TransactionCategoryEntity>>

    suspend fun getTransactionCategoryById(transactionId:String):TransactionCategoryEntity?

    suspend fun getTransactionCategoryByName(name:String): TransactionCategoryEntity?

    suspend fun updateTransactionCategory(transactionCategoryId:String,transactionCategoryName:String)

    suspend fun deleteTransactionCategory(transactionCategoryId:String)


}