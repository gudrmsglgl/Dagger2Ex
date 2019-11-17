package com.fastival.dagger2hardex.di.auth

import com.fastival.dagger2hardex.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {


    @Module
    companion object {

        @JvmStatic
        @AuthScope
        @Provides
        fun provideSessionApi(retrofit: Retrofit): AuthApi {
            return retrofit.create(AuthApi::class.java)
        }

    }
    /*@Provides
    fun provideSessionApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }*/
}