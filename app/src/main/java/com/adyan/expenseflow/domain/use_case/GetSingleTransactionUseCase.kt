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
package com.adyan.expenseflow.domain.use_case


import com.adyan.expenseflow.domain.models.Transaction
import com.adyan.expenseflow.domain.models.TransactionCategory
import com.adyan.expenseflow.domain.models.TransactionInfo
import com.adyan.expenseflow.domain.repository.TransactionCategoryRepository
import com.adyan.expenseflow.domain.repository.TransactionRepository
import com.adyan.expenseflow.domain.toExternalModel
import javax.inject.Inject


class GetSingleTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val transactionCategoryRepository: TransactionCategoryRepository
){

    suspend operator fun invoke(transactionId:String): TransactionInfo?{
        val transaction = transactionRepository.getTransactionById(transactionId = transactionId)
        val transactionCategory = transaction?.let {
            transactionCategoryRepository.getTransactionCategoryById(
                transactionId = it.transactionCategoryId)
        }
        if (transactionCategory != null) {
            return TransactionInfo(
                transaction = transaction.toExternalModel(),
                category = transactionCategory.toExternalModel(),
            )
        }else{
            return null
        }

    }
}