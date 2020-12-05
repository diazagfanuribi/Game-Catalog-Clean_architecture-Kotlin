package com.example.core.di

import com.example.core.data.HomeRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface CoreDependency {

    fun repository(): HomeRepository
}