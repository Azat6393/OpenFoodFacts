package com.azatberdimyradov.openfoodfacts.di

import android.content.Context
import androidx.room.Room
import com.azatberdimyradov.openfoodfacts.R
import com.azatberdimyradov.openfoodfacts.data.local.ProductDatabase
import com.azatberdimyradov.openfoodfacts.data.remote.ProductAPI
import com.azatberdimyradov.openfoodfacts.utils.Constants.BASE_URL
import com.azatberdimyradov.openfoodfacts.utils.Constants.DATABASE_NAME
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideProductDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ProductDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideProductDao(
        database: ProductDatabase
    ) = database.productDao()

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_24)
    )

    @Singleton
    @Provides
    fun provideProductApi(): ProductAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ProductAPI::class.java)
    }
}