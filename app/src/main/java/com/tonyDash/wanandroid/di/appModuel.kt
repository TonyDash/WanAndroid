package com.tonyDash.wanandroid.di
import com.cjy.networklibrary.api.HomeApi
import com.cjy.networklibrary.api.HomeApiService
import com.cjy.networklibrary.api.UserApi
import com.cjy.networklibrary.api.UserApiService
import com.cjy.networklibrary.room.historyDao
import com.cjy.networklibrary.room.userDao
import com.tonyDash.wanandroid.ui.main.common.repository.HistoryRepository
import com.tonyDash.wanandroid.ui.main.common.repository.UserInfoRepository
import com.tonyDash.wanandroid.ui.main.common.viewModel.UserInfoViewModel
import com.tonyDash.wanandroid.ui.main.home.repository.*
import com.tonyDash.wanandroid.ui.main.home.viewmodel.*
import com.tonyDash.wanandroid.ui.main.mine.repository.LoginRepository
import com.tonyDash.wanandroid.ui.main.mine.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PopularViewModel(get()) }
    viewModel { LatestViewModel(get()) }
    viewModel { SquareViewModel(get()) }
    viewModel { ProjectViewModel(get()) }
    viewModel { WeChatViewModel(get()) }
    viewModel { LoginViewModel(get(),get()) }
    viewModel { UserInfoViewModel(get()) }
}

val reposModule = module {
    //factory 每次注入时都重新创建一个新的对象
    factory { PopularRepository(get()) }
    factory { LatestRepository(get()) }
    factory { SquareRepository(get()) }
    factory { ProjectRepository(get()) }
    factory { WeChatRepository(get()) }
    factory { LoginRepository(get()) }
    factory { UserInfoRepository(get()) }
    factory { HistoryRepository(get()) }
}

val remoteModule = module {
    //single 单列注入
    single<HomeApi> { HomeApiService }
    single<UserApi> { UserApiService }
}


val localModule = module {
    single { userDao }
    single { historyDao }
}

val appModule = listOf(viewModelModule, reposModule,remoteModule, localModule)

