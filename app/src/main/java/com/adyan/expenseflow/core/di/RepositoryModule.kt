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
package com.adyan.expenseflow.core.di

import com.adyan.expenseflow.core.analytics.analytics.AnalyticsHelper
import com.adyan.expenseflow.core.room.database.ExpenseTrackerAppDatabase
import com.adyan.expenseflow.data.ExpenseCategoryRepositoryImpl
import com.adyan.expenseflow.data.ExpenseRepositoryImpl
import com.adyan.expenseflow.data.IncomeRepositoryImpl
import com.adyan.expenseflow.data.TransactionCategoryRepositoryImpl
import com.adyan.expenseflow.data.TransactionRepositoryImpl
import com.adyan.expenseflow.domain.repository.ExpenseCategoryRepository
import com.adyan.expenseflow.domain.repository.ExpenseRepository
import com.adyan.expenseflow.domain.repository.IncomeRepository
import com.adyan.expenseflow.domain.repository.TransactionCategoryRepository
import com.adyan.expenseflow.domain.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideExpenseCategoryRepository(
        database: ExpenseTrackerAppDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        analyticsHelper: AnalyticsHelper,
    ): ExpenseCategoryRepository {
        return ExpenseCategoryRepositoryImpl(
            db = database,
            ioDispatcher = ioDispatcher,
            analyticsHelper = analyticsHelper
        )
    }

    @Provides
    @Singleton
    fun provideExpenseRepository(
        database: ExpenseTrackerAppDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        analyticsHelper: AnalyticsHelper,
    ): ExpenseRepository {
        return ExpenseRepositoryImpl(
            db = database,
            ioDispatcher = ioDispatcher,
            analyticsHelper = analyticsHelper
        )
    }

    @Provides
    @Singleton
    fun provideTransactionCategoryRepository(
        database: ExpenseTrackerAppDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        analyticsHelper: AnalyticsHelper,
    ): TransactionCategoryRepository {
        return TransactionCategoryRepositoryImpl(
            db = database,
            ioDispatcher = ioDispatcher,
            analyticsHelper = analyticsHelper
        )
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(
        database: ExpenseTrackerAppDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        analyticsHelper: AnalyticsHelper,
    ): TransactionRepository {
        return TransactionRepositoryImpl(
            db = database,
            ioDispatcher = ioDispatcher,
            analyticsHelper = analyticsHelper
        )
    }

    @Provides
    @Singleton
    fun provideIncomeRepository(
        database: ExpenseTrackerAppDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        analyticsHelper: AnalyticsHelper,
    ): IncomeRepository {
        return IncomeRepositoryImpl(
            db = database,
            ioDispatcher = ioDispatcher,
            analyticsHelper = analyticsHelper
        )
    }
}