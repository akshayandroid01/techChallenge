package com.softradix.techchallenge.dagger

import com.google.gson.GsonBuilder
import com.softradix.techchallenge.Constants
import com.softradix.techchallenge.network.TPSService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Provides Network communication related instances.
 */
@Module
class NetworkModule {

    @Provides
    fun provideTPSService(): TPSService {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .build()
        return retrofit.create(TPSService::class.java)
    }

}
