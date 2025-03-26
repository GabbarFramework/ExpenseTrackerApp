
package com.adyan.expenseflow.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.adyan.expenseflow.core.datastore.preferences.UserPreferences
import com.adyan.expenseflow.core.util.Constants
import com.adyan.expenseflow.data.UserPreferenceRepositoryImpl
import com.adyan.expenseflow.domain.repository.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Provides
    @Singleton
    fun provideDatastorePreferences(@ApplicationContext context: Context):
            DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(name = Constants.USER_PREFERENCES)
            }
        )
    }




    @Provides
    @Singleton
    fun provideUserPreferencesRepository(userPreferences: UserPreferences):
            UserPreferencesRepository {
        return UserPreferenceRepositoryImpl(
            preferences = userPreferences
        )
    }
}