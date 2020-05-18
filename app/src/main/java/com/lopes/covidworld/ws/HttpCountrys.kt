package com.lopes.covidworld

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

object HttpCountrys {

    val urlCountrys = "https://covid19-brazil-api.now.sh/api/report/v1/countries"

    fun hasConnection(ctx: Context): Boolean{
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val info =  cm.activeNetworkInfo
        return info != null && info.isConnected
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadCountrys(): List<Countrys>?{

        val country = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url(HttpCountrys.urlCountrys)
            .build()

        val response = country.newCall(request).execute()
        val jsonString = response.body?.string()

        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("data")

        return readCountrys(jsonArray)


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun readCountrys(json: JSONArray) : List<Countrys>?{
        val paises = arrayListOf<Countrys>()
        try {
            for (i in 0 .. json.length()-1) {
                var js = json.getJSONObject(i)

                val dia = formatarData(js.getString("updated_at").substring(0,10))
                val hora = js.getString("updated_at").substring(11,16)

                var country = Countrys(
                    js.getString("country"),
                    js.getInt("cases"),
                    js.getInt("confirmed"),
                    js.getInt("deaths"),
                    js.getInt("recovered"),
                    dia,
                    hora
                )
                paises.add(country)
            }
        }catch (e: IOException) {
            Log.e("Erro", "Impossivel ler JSON")
        }

        return paises
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatarData(data: String): String {
        val diaString = data
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        var date = LocalDate.parse(diaString)
        var formattedDate = date.format(formatter)
        return formattedDate
    }
}