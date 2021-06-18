package tw.edu.pu.csim.tcyang.readtaiwanweather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonService {
    @GET("v1/rest/datastore/F-C0032-001")
    fun getJsonData(@Query("authorization") Authorization: String,
                    @Query("format") Format: String,
                    @Query("locationName") LocationName: String): Call<JsonResponse>
}