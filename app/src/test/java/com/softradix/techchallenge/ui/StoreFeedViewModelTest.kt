package com.softradix.techchallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softradix.techchallenge.StoreFeedFakeRepo
import com.softradix.techchallenge.network.model.StoreResponse
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*

import org.junit.Before

class StoreFeedViewModelTest(private val repo:StoreFeedFakeRepo):ViewModel() {

    val categorySelected = MutableLiveData<List<StoreResponse>>()
    val feedLiveData: LiveData<List<StoreResponse>> = categorySelected
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

    fun validateFields(lat:Double,long:Double):Boolean{
        if (lat.equals(null) || long.equals(null)){
            return false
        }
        if (lat == 0.0 || long == 0.0){
            return false
        }

        return true
    }
}