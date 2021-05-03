package com.tonyDash.wanandroid.di
import com.tonyDash.wanandroid.network.HomeApiService
import com.tonyDash.wanandroid.ui.main.home.api.HomeApi
import com.tonyDash.wanandroid.ui.main.home.repository.PopularRepository
import com.tonyDash.wanandroid.ui.main.home.viewmodel.PopularViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PopularViewModel(get()) }
}

val reposModule = module {
    //factory 每次注入时都重新创建一个新的对象
    factory { PopularRepository(get()) }
}

val remoteModule = module {
    //single 单列注入
    single<HomeApi> { HomeApiService }
}

val appModule = listOf(viewModelModule, reposModule,remoteModule)

