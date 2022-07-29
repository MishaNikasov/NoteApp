package com.app.noteapp.di

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object FirebaseModule {
//
//    @Singleton
//    @Provides
//    fun provideSignInRequest() = BeginSignInRequest.builder()
//        .setGoogleIdTokenRequestOptions(
//            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                .setSupported(true)
//                .setServerClientId("154565551251-lkt8msgcd0uu6erp97usabq3knfslr5u.apps.googleusercontent.com")
//                .setFilterByAuthorizedAccounts(true)
//                .build())
//
//}