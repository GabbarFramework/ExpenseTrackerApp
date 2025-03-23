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

import com.adyan.expenseflow.core.util.Resource
import com.adyan.expenseflow.core.util.Response
import com.adyan.expenseflow.domain.models.Expense
import com.adyan.expenseflow.domain.models.Transaction
import com.adyan.expenseflow.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(transaction: Transaction): Flow<Resource<Response>> = flow {
        try {
            emit(Resource.Loading())
            repository.createTransaction(transaction = transaction)
            emit(Resource.Success(data = Response(msg = "Transaction saved successfully")))
        }catch (e:IOException){
            emit(Resource.Error(message = e.localizedMessage?: "An unexpected error occurred"))
        }
    }
}