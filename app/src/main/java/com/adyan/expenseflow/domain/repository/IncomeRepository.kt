
package com.adyan.expenseflow.domain.repository

import com.adyan.expenseflow.core.room.entities.IncomeEntity
import kotlinx.coroutines.flow.Flow

interface IncomeRepository {

    suspend fun insertIncome(incomeEntity: IncomeEntity)

    fun getAllIncome(): Flow<List<IncomeEntity>>

    fun getIncomeById(id:String): Flow<IncomeEntity?>

    suspend fun deleteIncomeById(id:String)

    suspend fun deleteAllIncome()

}