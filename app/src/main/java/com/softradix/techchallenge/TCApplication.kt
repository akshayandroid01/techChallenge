package com.softradix.techchallenge

import android.app.Application
import com.softradix.techchallenge.dagger.AppComponent
import com.softradix.techchallenge.dagger.AppModule
import com.softradix.techchallenge.dagger.DaggerAppComponent

/**
 * The application class - an entry point into our app where we initialize Dagger.
 */
class TCApplication : Application() {
    companion object {
        private lateinit var appComponent: AppComponent

        fun getAppComponent(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerAppComponent()
    }

    private fun initDaggerAppComponent() {
         appComponent =
               DaggerAppComponent.builder()
                         .appModule(AppModule(this))
                         .build()
    }
}
