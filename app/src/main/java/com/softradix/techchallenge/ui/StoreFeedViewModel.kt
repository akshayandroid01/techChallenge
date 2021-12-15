package com.softradix.techchallenge.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softradix.techchallenge.network.model.StoreDetailsResponse
import com.softradix.techchallenge.network.model.StoreResponse
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StoreFeedViewModel @Inject constructor(private val repo: StoreFeedRepo):ViewModel() {

    val categorySelected = MutableLiveData<List<StoreResponse>>()
    val detailResponse = MutableLiveData<StoreDetailsResponse>()
    val feedLiveData:LiveData<List<StoreResponse>> = categorySelected
    val detailLiveData:LiveData<StoreDetailsResponse> = detailResponse
    val isLoading = MutableLiveData<Boolean>()

    fun getFeeds(lat:Double,long:Double){
        repo.getStoreFeed(lat,long)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<StoreResponse>> {
                override fun onSuccess(t: List<StoreResponse>) {
                    isLoading.value = false
                    categorySelected.value = t
                }

                override fun onSubscribe(d: Disposable) {
                    isLoading.value = true
                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                }

            })
    }
    fun getFeedsDetails(id:String){
        repo.getStoreFeedDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<StoreDetailsResponse> {
                override fun onSuccess(t: StoreDetailsResponse) {
                    isLoading.value = false
                    Log.e("Data", "onSuccess: $t", )
                    detailResponse.value = t
                }

                override fun onSubscribe(d: Disposable) {
                    isLoading.value = true
                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                }

            })
    }
}