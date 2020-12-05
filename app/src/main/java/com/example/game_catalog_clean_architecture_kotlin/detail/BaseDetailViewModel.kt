package com.example.game_catalog_clean_architecture_kotlin.detail

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import com.example.game_catalog_clean_architecture_kotlin.detail.DetailFragment.Companion.BUNDLE_ARGS


open class BaseDetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val arguments get() = savedStateHandle.get<Bundle>(BUNDLE_ARGS)

    @MainThread
    inline fun <reified Args : NavArgs> navArgs() = NavArgsLazy(Args::class) {
        arguments ?: throw IllegalStateException("ViewModel $this has null arguments")
    }
}