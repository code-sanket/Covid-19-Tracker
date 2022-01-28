package code.sanky.covid_19

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val BASE_URL = "https://api.rootnet.in/covid19-in/stats/latest/"

    /**
     * Here we are creating instance for Retrofit.
     */
    fun getInstance(): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Here Retrofit is providing body for api class.
     * Then only we can use this api to get the data.
     */
    val api = getInstance().create(Api::class.java)
}