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
import com.adyan.expenseflow.core.analytics.analytics.logNewTransaction
import com.adyan.expenseflow.core.di.IoDispatcher
import com.adyan.expenseflow.core.room.database.ExpenseTrackerAppDatabase
import com.adyan.expenseflow.core.room.entities.TransactionEntity
import com.adyan.expenseflow.domain.models.Transaction
import com.adyan.expenseflow.domain.repository.TransactionRepository
import com.adyan.expenseflow.domain.toEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val db: ExpenseTrackerAppDatabase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val analyticsHelper: AnalyticsHelper,
) : TransactionRepository {
    override suspend fun createTransaction(transaction: Transaction) {
        analyticsHelper.logNewTransaction()
        return withContext(ioDispatcher) {
            db.transactionEntityDao.insertTransaction(
                transactionEntity = transaction.toEntity()
            )
        }
    }

    override suspend fun getTransactionById(transactionId: String): TransactionEntity? {
        return withContext(ioDispatcher) {
            db.transactionEntityDao.getTransactionById(id = transactionId)
        }
    }

    override fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return db.transactionEntityDao.getTransactions().flowOn(ioDispatcher)

    }

    override fun getTransactionsForACertainDay(date: String): Flow<List<TransactionEntity>> {
        return db.transactionEntityDao.getTransactionsForACertainDay(date = date)
            .flowOn(ioDispatcher)
    }

    override fun getTransactionsBetweenTwoDates(dates: List<String>): Flow<List<TransactionEntity>> {
        return db.transactionEntityDao.getTransactionsBetweenTwoDates(dates = dates)
            .flowOn(ioDispatcher)
    }

    override fun searchTransactions(
        dates: List<String>,
        categoryId: String
    ): Flow<List<TransactionEntity>> {
        return db.transactionEntityDao.searchTransactions(dates = dates, categoryId = categoryId)
            .flowOn(ioDispatcher)
    }

    override suspend fun deleteTransactionById(transactionId: String) {
        withContext(ioDispatcher){
            db.transactionEntityDao.deleteTransactionById(id = transactionId)
        }
    }

    override fun getTransactionByCategory(categoryId: String): Flow<List<TransactionEntity>> {
        return db.transactionEntityDao.getTransactionsByCategory(categoryId = categoryId)
            .flowOn(ioDispatcher)
    }

}