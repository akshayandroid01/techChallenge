package com.softradix.techchallenge

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.softradix.techchallenge.network.model.StoreResponse
import com.softradix.techchallenge.ui.StoreFeedViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoTestRule


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mRepo: StoreFeedFakeRepo
    private lateinit var mViewModel:StoreFeedViewModelTest

    @Spy
    private lateinit var mViewModelS:StoreFeedViewModelTest

    @Before
    fun setup(){
        mRepo = StoreFeedFakeRepo()
        mViewModel = StoreFeedViewModelTest(mRepo)
    }

    @Test
    fun isDataEmpty() {
        val result = mViewModel.validateFields(0.0, 0.0)
        assertThat(result).isFalse()
    }

    @Test
    fun isDataCorrect() {
        val result =
            mViewModel.validateFields(Constants.DEFAULT_LATITUDE, Constants.DEFAULT_LONGITUDE)
        assertThat(result).isTrue()
    }

    @Test
    fun isLiveDataEmptyReturnTrue() {
        val item = StoreResponse(
            id = "1", name = "Abs", description = "zdncjdsh",
            coverImgUrl = "", status = "1", deliveryFeeCents = ""
        )
        val list: List<StoreResponse> = listOf(item)
        mViewModel.categorySelected.value = list
        val result = mViewModel.feedLiveData.getOrAwaitValue()
        assertThat(result).contains(item)
    }

    @Test
    fun checkLiveData(){
        doReturn(mViewModelS.feedLiveData).`when`(mViewModel).getFeeds(Constants.DEFAULT_LATITUDE,Constants.DEFAULT_LONGITUDE)
    }


}