package com.example.di.provide.repository

import com.example.di.provide.api.ApiModule
import com.example.di.provide.departmentApi.DepartmentApi
import com.example.di.repository.DepartmentRepository
import com.example.di.repository.DepartmentRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideDepartmentRepository(
        departmentApi: DepartmentApi
    ): DepartmentRepository = DepartmentRepositoryImpl(departmentApi)

}
