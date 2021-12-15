package com.softradix.techchallenge.dagger

import com.softradix.techchallenge.TCApplication
import com.softradix.techchallenge.ui.StoreFeedDetailFragment
import com.softradix.techchallenge.ui.StoreFeedFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun appModule(module: AppModule): Builder
        fun networkModule(module: NetworkModule): Builder
        fun build(): AppComponent
    }

    fun inject(app: TCApplication)
    fun inject(storeFeedFragment: StoreFeedFragment)
    fun inject(storeFeedFragment: StoreFeedDetailFragment)
}
