package com.example.di.provide.api

import com.example.di.provide.departmentApi.DepartmentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module(includes = [HttpModule::class])
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideAPIService(retrofit: Retrofit): DepartmentApi {
        return retrofit.create(DepartmentApi::class.java)
    }
}