/*
 * Copyright 2023 Expense Tracker App By Peter Chege
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.adyan.expenseflow.domain.repository

import com.adyan.expenseflow.core.room.entities.ExpenseCategoryEntity
import com.adyan.expenseflow.domain.models.ExpenseCategory
import kotlinx.coroutines.flow.Flow

interface ExpenseCategoryRepository {
    suspend fun saveExpenseCategory(expenseCategory: ExpenseCategory)

    suspend fun getExpenseCategoryById(expenseCategoryId: String):ExpenseCategoryEntity?

    fun getAllExpenseCategories(): Flow<List<ExpenseCategoryEntity>>

    suspend fun getExpenseCategoryByName(name:String):ExpenseCategoryEntity?

    suspend fun updateExpenseCategory(expenseCategoryId:String,expenseCategoryName:String)

    suspend fun deleteExpenseCategory(expenseCategoryId:String)

}