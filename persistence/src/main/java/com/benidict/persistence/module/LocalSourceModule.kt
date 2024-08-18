package com.benidict.persistence.module

import com.benidict.persistence.database.TamingTemperDataStore
import com.benidict.persistence.source.ActivitiesLocalSource
import com.benidict.persistence.source.UserLocalSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalSourceModule {

    @Singleton
    @Provides
    fun providesActivitiesLocalSource() =
        ActivitiesLocalSource()

    @Singleton
    @Provides
    fun providesUserLocalSource(
        tamingTemperDataStore: TamingTemperDataStore
    ) = UserLocalSource(tamingTemperDataStore)
}