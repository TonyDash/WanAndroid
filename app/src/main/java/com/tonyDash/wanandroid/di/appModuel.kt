package com.tonyDash.wanandroid.di
import com.tonyDash.wanandroid.network.HomeApiService
import com.tonyDash.wanandroid.network.UserApiService
import com.tonyDash.wanandroid.ui.main.home.api.HomeApi
import com.tonyDash.wanandroid.ui.main.home.repository.*
import com.tonyDash.wanandroid.ui.main.home.viewmodel.*
import com.tonyDash.wanandroid.ui.main.mine.api.UserApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PopularViewModel(get()) }
    viewModel { LatestViewModel(get()) }
    viewModel { SquareViewModel(get()) }
    viewModel { ProjectViewModel(get()) }
    viewModel { WeChatViewModel(get()) }
}

val reposModule = module {
    //factory 每次注入时都重新创建一个新的对象
    factory { PopularRepository(get()) }
    factory { LatestRepository(get()) }
    factory { SquareRepository(get()) }
    factory { ProjectRepository(get()) }
    factory { WeChatRepository(get()) }
}

val remoteModule = module {
    //single 单列注入
    single<HomeApi> { HomeApiService }
    single<UserApi> { UserApiService }
}

val appModule = listOf(viewModelModule, reposModule,remoteModule)

