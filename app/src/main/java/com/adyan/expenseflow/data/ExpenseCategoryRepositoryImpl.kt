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
package com.adyan.expenseflow.data

import com.adyan.expenseflow.core.analytics.analytics.AnalyticsHelper
import com.adyan.expenseflow.core.analytics.analytics.logNewExpenseCategoryCreated
import com.adyan.expenseflow.core.di.IoDispatcher
import com.adyan.expenseflow.core.room.database.ExpenseTrackerAppDatabase
import com.adyan.expenseflow.core.room.entities.ExpenseCategoryEntity
import com.adyan.expenseflow.domain.models.ExpenseCategory
import com.adyan.expenseflow.domain.repository.ExpenseCategoryRepository
import com.adyan.expenseflow.domain.toEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExpenseCategoryRepositoryImpl @Inject constructor(
    private val db:ExpenseTrackerAppDatabase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val analyticsHelper: AnalyticsHelper,
): ExpenseCategoryRepository {
    override suspend fun saveExpenseCategory(expenseCategory: ExpenseCategory) {
        return withContext(ioDispatcher){
            analyticsHelper.logNewExpenseCategoryCreated()
            db.expenseCategoryEntityDao.insertExpenseCategory(
                expenseCategoryEntity = expenseCategory.toEntity())
        }
    }

    override suspend fun getExpenseCategoryById(expenseCategoryId: String): ExpenseCategoryEntity? {
        return withContext(ioDispatcher){
            db.expenseCategoryEntityDao.getExpenseCategoryById(expenseCategoryId = expenseCategoryId)
        }
    }

    override fun getAllExpenseCategories(): Flow<List<ExpenseCategoryEntity>> {
        return db.expenseCategoryEntityDao.getExpenseCategories().flowOn(ioDispatcher)
    }

    override suspend fun getExpenseCategoryByName(name: String): ExpenseCategoryEntity? {
        return withContext(ioDispatcher){
            db.expenseCategoryEntityDao.getExpenseCategoryByName(name = name)
        }

    }

    override suspend fun updateExpenseCategory(expenseCategoryId: String, expenseCategoryName: String) {
        withContext(ioDispatcher){
            db.expenseCategoryEntityDao.updateExpenseCategoryName(
                id = expenseCategoryId, name = expenseCategoryName)
        }
    }

    override suspend fun deleteExpenseCategory(expenseCategoryId: String) {
        withContext(ioDispatcher){
            db.expenseCategoryEntityDao.deleteExpenseCategoryById(id = expenseCategoryId)
        }
    }

}