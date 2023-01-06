package com.vk.limextask.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appDispatchers = module {
    single(named("IODispatcher")) {
        Dispatchers.IO
    }
    single(named("DefaultDispatcher")) {
        Dispatchers.Default
    }
    single(named("MainDispatcher")) {
        Dispatchers.Main
    }
}