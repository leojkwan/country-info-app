package com.kodeco.android.countryinfo

import com.kodeco.android.countryinfo.models.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface APIServiceType {
    @GET("all")
    suspend fun getAllCountries(): Response<List<Country>>
}

object APIService {
    private const val BASE_URL = "https://restcountries.com/v3.1/"

    val api: APIServiceType by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(APIServiceType::class.java)
    }
}
