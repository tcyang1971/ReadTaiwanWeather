package tw.edu.pu.csim.tcyang.readtaiwanweather

import com.google.gson.annotations.SerializedName

class JsonResponse {
    @SerializedName("records")
    var records: Records? = null
}

class Records {
    @SerializedName("location")
    var location = ArrayList<Location>()
}

class Location {
    @SerializedName("locationName")
    var locationName: String? = null

    @SerializedName("weatherElement")
    var weatherElement = ArrayList<WeatherElement>()
}

class WeatherElement {
    @SerializedName("elementName")
    var elementName: String? = null

    @SerializedName("time")
    var time = ArrayList<Time>()
}

class Time {
    @SerializedName("startTime")
    var startTime: String? = null
    @SerializedName("endTime")
    var endTime: String? = null

    @SerializedName("parameter")
    var parameter: Parameter? = null
}

class Parameter {
    @SerializedName("parameterName")
    var parameterName: String? = null
}