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
package com.adyan.expenseflow.presentation.screens.transactions

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyan.expenseflow.core.util.FilterConstants
import com.adyan.expenseflow.domain.models.Transaction
import com.adyan.expenseflow.domain.models.TransactionCategory
import com.adyan.expenseflow.domain.toExternalModel
import com.adyan.expenseflow.domain.use_case.GetAllTransactionCategoriesUseCase
import com.adyan.expenseflow.domain.use_case.GetTransactionsByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface AllTransactionsScreenUiState {
    object Loading:AllTransactionsScreenUiState

    data class Success(
        val transactions:List<Transaction>,
        val transactionCategories:List<TransactionCategory>,
    ):AllTransactionsScreenUiState

    data class Error(val message:String):AllTransactionsScreenUiState
}

@HiltViewModel
class AllTransactionsScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    getTransactionsByCategoryUseCase: GetTransactionsByCategoryUseCase,
    getAllTransactionCategoriesUseCase: GetAllTransactionCategoriesUseCase,
):ViewModel() {

    val activeTransactionFilter = savedStateHandle.getStateFlow(
        key = "filter", initialValue = FilterConstants.ALL)

    val uiState = combine(
        activeTransactionFilter,
        getAllTransactionCategoriesUseCase(),
        getTransactionsByCategoryUseCase(categoryId = activeTransactionFilter.value),
    ){ transactionFilter, transactionsCategoryEntities,transactionEntities ->

        val transactionCategories = transactionsCategoryEntities.map { it.toExternalModel() }
        val transactions = transactionEntities.map { it.toExternalModel() }
            .filter {
                if (activeTransactionFilter.value != FilterConstants.ALL){
                    it.transactionCategoryId == activeTransactionFilter.value
                }else{
                    true
                }
            }
        AllTransactionsScreenUiState.Success(
            transactions = transactions,
            transactionCategories = transactionCategories
        )
    }.onStart {
        AllTransactionsScreenUiState.Loading
    }.catch { AllTransactionsScreenUiState.Error(message = "Failed to fetch categories") }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = AllTransactionsScreenUiState.Loading
        )

    fun onChangeActiveTransactionFilter(filter:String){
        savedStateHandle["filter"] = filter

    }
}