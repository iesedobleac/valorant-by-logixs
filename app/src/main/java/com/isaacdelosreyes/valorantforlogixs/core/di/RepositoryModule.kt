package com.isaacdelosreyes.valorantforlogixs.core.di

import com.isaacdelosreyes.valorantforlogixs.core.data.remote.retrofit.ValorantWs
import com.isaacdelosreyes.valorantforlogixs.core.data.repository.ValorantRepository
import com.isaacdelosreyes.valorantforlogixs.core.data.repository.ValorantRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesMonumentsRepository(
        valorantWs: ValorantWs
    ): ValorantRepository =
        ValorantRepositoryImpl(valorantWs)
}