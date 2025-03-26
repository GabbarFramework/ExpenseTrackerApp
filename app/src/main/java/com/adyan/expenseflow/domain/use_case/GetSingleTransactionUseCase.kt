
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