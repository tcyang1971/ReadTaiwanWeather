package tw.edu.pu.csim.tcyang.readtaiwanweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var BaseUrl = "https://opendata.cwb.gov.tw/api/"
    var Authorization = "rdec-key-123-45678-011121314"
    var Format = "JSON"
    var LocationName = "臺中市"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener{ ReadData() }
    }

    fun ReadData(){
        val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(JsonService::class.java)
        val call = service.getJsonData(Authorization, Format, LocationName)
        call.enqueue(object : Callback<JsonResponse> {
            override fun onResponse(call: Call<JsonResponse>, response: Response<JsonResponse>) {
                if (response.code() == 200) {
                    val Result = response.body()!!
                    var msg = "天氣預報 (城市：" + Result.records!!.location[0].locationName + ")"

                    for(i in 0..2){
                        msg += "\n\n" + Result.records!!.location[0].weatherElement[0].time[i].startTime.toString()
                        msg += " ~ " + Result.records!!.location[0].weatherElement[0].time[i].endTime.toString()
                        msg += "\n天氣：" + Result.records!!.location[0].weatherElement[0].time[i].parameter!!.parameterName.toString()
                        msg += "，氣溫：" + Result.records!!.location[0].weatherElement[2].time[i].parameter!!.parameterName.toString()
                        msg += "~" + Result.records!!.location[0].weatherElement[4].time[i].parameter!!.parameterName.toString()
                        msg += "度\n降雨機率：" + Result.records!!.location[0].weatherElement[1].time[i].parameter!!.parameterName.toString()
                        msg += "%，舒適度：" + Result.records!!.location[0].weatherElement[3].time[i].parameter!!.parameterName.toString()
                    }
                    txv.text = msg
                }
                else{
                    txv.text = "找不到檔案"
                }
            }

            override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                txv.text = t.message
            }
        })
    }

}
