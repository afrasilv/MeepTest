package com.afrasilv.meeptest.di

import com.afrasilv.meeptest.BuildConfig
import com.afrasilv.meeptest.MainViewModel
import com.afrasilv.meeptest.data.repository.CityRersourcesRepository
import com.afrasilv.meeptest.data.repository.remote.CityResourceRemote
import com.afrasilv.meeptest.data.repository.remote.RemoteInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    //provide web components
    single { createOkHttpClient() }

    //provide Retrofit
    single { createRetrofitClient<RemoteInterface>(get()) }

    //provide remote repositories
    single { CityResourceRemote(get())}

    //provide repositories
    single { CityRersourcesRepository(get())}

    //viewmodels
    viewModel { MainViewModel(get()) }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }
        .addInterceptor(httpLoggingInterceptor)
        .hostnameVerifier { _, _ -> true } //TODO remove in production code. USE CERTIFICATEPINNING
        .build()
}

inline fun <reified T> createRetrofitClient(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_API)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}