package com.thernat.tempconverter.di

import com.thernat.tempconverter.data.TemperatureRepository
import com.thernat.tempconverter.MainPresenter
import com.thernat.tempconverter.api.ApiService
import com.thernat.tempconverter.data.TemperatureDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * Created by m.rafalski on 2019-06-29.
 */
val applicationModule = module {
    single<TemperatureDataSource> { TemperatureRepository(get()) }
    factory { MainPresenter(get()) }
    single {  }
}

val netModule = module {
    single {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    single {AnnotationStrategy()}

    single {
        Persister(get<AnnotationStrategy>())
    }

    single {
        SimpleXmlConverterFactory.create(get<Persister>())
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://www.w3schools.com/xml/")
            .addConverterFactory(get<SimpleXmlConverterFactory>())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }
}
