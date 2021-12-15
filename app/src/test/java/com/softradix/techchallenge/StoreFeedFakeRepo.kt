package com.softradix.techchallenge

import com.google.gson.GsonBuilder
import com.softradix.techchallenge.Constants
import com.softradix.techchallenge.network.TPSService
import com.softradix.techchallenge.network.model.StoreResponse
import dagger.Provides
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class StoreFeedFakeRepo  {


    fun getStoreFeed(lat:Double, long:Double): Single<List<StoreResponse>> {
        return provideTPSService().getStoreFeed(lat,long).doOnSuccess {
        }
    }

    private fun provideTPSService(): TPSService {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .build()
        return retrofit.create(TPSService::class.java)
    }

}