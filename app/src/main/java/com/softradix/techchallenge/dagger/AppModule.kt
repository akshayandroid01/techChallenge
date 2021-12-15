package com.softradix.techchallenge.dagger

import android.app.Application
import com.softradix.techchallenge.TCApplication
import com.softradix.techchallenge.network.TPSService
import com.softradix.techchallenge.ui.StoreFeedRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val application: TCApplication) {

    @Provides
    @Singleton
    fun getApplication(): Application {
        return application
    }

    @Provides
    fun provideViewModel(service: TPSService) = StoreFeedRepo(service)

}
