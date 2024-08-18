package com.benidict.persistence.module

import android.content.Context
import com.benidict.persistence.database.TamingTemperDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideTamingTemperDataStore(@ApplicationContext context: Context) = TamingTemperDataStore(
        context
    )
}