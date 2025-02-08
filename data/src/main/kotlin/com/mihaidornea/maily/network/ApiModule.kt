package com.mihaidornea.maily.network

import com.mihaidornea.maily.api.RandomUserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideRandomUserApi(
        retrofit: Retrofit
    ): RandomUserApi = retrofit.create(RandomUserApi::class.java)
}
