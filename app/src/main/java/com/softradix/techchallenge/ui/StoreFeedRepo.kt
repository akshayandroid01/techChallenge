package com.softradix.techchallenge.ui

import com.softradix.techchallenge.network.TPSService
import com.softradix.techchallenge.network.model.StoreDetailsResponse
import com.softradix.techchallenge.network.model.StoreResponse
import io.reactivex.Single
import javax.inject.Inject

class StoreFeedRepo @Inject constructor(private val apiService: TPSService) {

    fun getStoreFeed(lat:Double, long:Double): Single<List<StoreResponse>> {
        return apiService.getStoreFeed(lat,long).doOnSuccess {
        }
    }
    fun getStoreFeedDetails(id:String): Single<StoreDetailsResponse> {
        return apiService.getStoreDetails(id).doOnSuccess {
        }
    }
}