package com.dylan0221.dogliker.di.modules

import android.content.Context
import com.dylan0221.dogliker.data.remote.firebase.FirebaseAuthentication
import com.dylan0221.dogliker.data.remote.firebase.FirebaseDatabase
import com.dylan0221.dogliker.data.remote.retrofit.DogApiService
import com.dylan0221.dogliker.data.repository.DogLikerRepositoryImpl
import com.dylan0221.dogliker.domain.repository.DogLikerRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDogApi():DogApiService =
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiService::class.java)

    @Provides
    @Singleton
    fun providesDogRepository(
        api: DogApiService,
        fbAuth: FirebaseAuthentication,
        fbData: FirebaseDatabase
    ): DogLikerRepository = DogLikerRepositoryImpl(api, fbAuth, fbData)


    @Singleton
    @Provides
    fun providesFirebaseAuthentication(auth: FirebaseAuth): FirebaseAuthentication =
        FirebaseAuthentication(auth)

    @Provides
    @Singleton
    fun providesAuth():FirebaseAuth = FirebaseAuth.getInstance()


    @Provides
    @Singleton
    fun providesFireStore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesFirebaseDatabase(db: FirebaseFirestore): FirebaseDatabase =
        FirebaseDatabase(db)

    @Provides
    @Singleton
    fun providesGoogleSignInClient(context: Context): GoogleSignInClient {
        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(com.firebase.ui.auth.R.string.default_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(context,signInOptions)
    }


}

