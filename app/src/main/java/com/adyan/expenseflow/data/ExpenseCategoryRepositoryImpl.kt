
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