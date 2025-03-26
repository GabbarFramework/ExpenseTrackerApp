
package com.adyan.expenseflow.core.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.adyan.expenseflow.core.room.dao.ExpenseCategoryEntityDao
import com.adyan.expenseflow.core.room.dao.ExpenseEntityDao
import com.adyan.expenseflow.core.room.dao.IncomeEntityDao
import com.adyan.expenseflow.core.room.dao.TransactionCategoryEntityDao
import com.adyan.expenseflow.core.room.dao.TransactionEntityDao
import com.adyan.expenseflow.core.room.entities.ExpenseCategoryEntity
import com.adyan.expenseflow.core.room.entities.ExpenseEntity
import com.adyan.expenseflow.core.room.entities.IncomeEntity
import com.adyan.expenseflow.core.room.entities.TransactionCategoryEntity
import com.adyan.expenseflow.core.room.entities.TransactionEntity
import com.adyan.expenseflow.core.room.converters.DateConverter

@TypeConverters(DateConverter::class)
@Database(
    entities = [
        ExpenseCategoryEntity::class,
        TransactionCategoryEntity::class,
        ExpenseEntity::class,
        TransactionEntity::class,
        IncomeEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class ExpenseTrackerAppDatabase : RoomDatabase() {


    abstract val expenseCategoryEntityDao: ExpenseCategoryEntityDao

    abstract val transactionCategoryEntityDao: TransactionCategoryEntityDao

    abstract val expenseEntityDao:ExpenseEntityDao

    abstract val transactionEntityDao:TransactionEntityDao

    abstract val incomeEntityDao:IncomeEntityDao

}