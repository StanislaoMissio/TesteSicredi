package com.example.testesicredi

import com.example.testesicredi.api.API
import com.example.testesicredi.repository.EventsRepository
import com.example.testesicredi.viewmodel.EventDetailViewModel
import com.example.testesicredi.viewmodel.ListEventsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val viewModel = module {
    viewModel { ListEventsViewModel(get()) }
    viewModel { EventDetailViewModel(get()) }
}

val repository = module {
    single { EventsRepository(get()) }
}

val network = module {
    single { provideRetrofit(get()) }
    single { provideNetworkApi(get()) }
    single { provideOkHTTPService() }
}

fun provideOkHTTPService(): OkHttpClient.Builder {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(logging)
}

fun provideRetrofit(okHttpClient: OkHttpClient.Builder): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://5f5a8f24d44d640016169133.mockapi.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())
        .build()

}

fun provideNetworkApi(retrofit: Retrofit): API =
    retrofit.create(API::class.java)