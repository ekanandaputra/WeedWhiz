package com.ntech.weedwhiz.di

import com.ntech.theyardhub.feature.auth.ConfigViewModel
import com.ntech.theyardhub.feature.auth.HistoryViewModel
import com.ntech.theyardhub.feature.auth.MonitoringViewModel
import com.ntech.weedwhiz.feature.auth.LoginViewModel
import com.ntech.weedwhiz.datalayer.di.AuthenticationModule
import com.ntech.weedwhiz.datalayer.di.ConfigModule
import com.ntech.weedwhiz.datalayer.di.HistoryModule
import com.ntech.weedwhiz.datalayer.di.MonitoringModule
import com.ntech.weedwhiz.datalayer.implementation.repository.AuthenticationRepositoryImpl
import com.ntech.weedwhiz.datalayer.implementation.repository.ConfigRepositoryImpl
import com.ntech.weedwhiz.datalayer.implementation.repository.HistoryRepositoryImpl
import com.ntech.weedwhiz.datalayer.implementation.repository.MonitoringRepositoryImpl
import com.ntech.weedwhiz.feature.main.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    // Shared Preference
    single { SharedPreference().provideSharedPreference(androidContext()) }
    single { SharedPreference().providePreferenceManager(get()) }

    // Auth Collection
    single(named("AUTH")) { AuthenticationModule.provideAuthRef() }
    single { AuthenticationModule.provideAuthRepository(get(named("AUTH")), get()) }

    // Config Collection
    single(named("CONFIG")) { ConfigModule.provideConfigRef() }
    single { ConfigModule.provideConfigRepository(get(named("CONFIG")), get()) }

    // Config Collection
    single(named("HISTORY")) { HistoryModule.provideHistoryRef() }
    single { HistoryModule.provideHistoryRepository(get(named("HISTORY")), get()) }

    // Config Collection
    single(named("MONITORING")) { MonitoringModule.provideMonitoringRef() }
    single { MonitoringModule.provideMonitoringRepository(get(named("MONITORING")), get()) }

    // Repository
    single { AuthenticationRepositoryImpl(get(), get()) }
    single { ConfigRepositoryImpl(get(), get()) }
    single { HistoryRepositoryImpl(get(), get()) }
    single { MonitoringRepositoryImpl(get(), get()) }

    // View Model
    factory { LoginViewModel(get()) }
    factory { ConfigViewModel(get()) }
    factory { HistoryViewModel(get()) }
    factory { MonitoringViewModel(get()) }
    factory { MainActivityViewModel() }

}