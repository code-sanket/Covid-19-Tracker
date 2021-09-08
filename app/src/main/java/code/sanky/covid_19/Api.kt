package code.sanky.covid_19

import retrofit2.Response
import retrofit2.http.GET

interface Api {

    /**
     * It is moreover like DAOs in Room Library
     * We just have to provide the interface and rest of the work is done my Retrofit.
     */
    @GET("stats")
    suspend fun getData() : Response<UpdatedDataApi>
}