package com.lopes.covidworld.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lopes.covidworld.Countrys
import com.lopes.covidworld.R
import kotlinx.android.synthetic.main.activity_all_states.appBar
import kotlinx.android.synthetic.main.activity_country_informations.*

class CountryInformations : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_informations)

        appBar.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@CountryInformations, all_countrys::class.java)
                startActivity(intent)
            }
        })

        val arrayCountry = this.intent.getParcelableExtra<Countrys>("Country")

        dia.text = arrayCountry.data.toString().substring(0,2)
        mes.text = alterMonth(arrayCountry.data.toString())
        pais.text = arrayCountry.country
        casos.text = arrayCountry.cases.toString()
        mortes.text = arrayCountry.deaths.toString()
        confirmados.text = arrayCountry.confirmed.toString()
        curados.text = arrayCountry.recovered.toString()

    }

    fun alterMonth(month: String): String? {
        var newString: String? = ""
        when {
            month.substring(3,5) == "01" -> {
                newString = "JANEIRO"
            }
            month.substring(3,5) == "02" -> {
                newString = "FEVEREIRO"
            }
            month.substring(3,5) == "03" -> {
                newString = "MARÇO"
            }
            month.substring(3,5) == "04" -> {
                newString = "ABRIL"
            }
            month.substring(3,5) == "05" -> {
                newString = "MAIO"
            }
            month.substring(3,5) == "06" -> {
                newString = "JUNHO"
            }
            month.substring(3,5) == "07" -> {
                newString = "JULHO"
            }
            month.substring(3,5) == "08" -> {
                newString = "AGOSTO"
            }
            month.substring(3,5) == "09" -> {
                newString = "SETEMBRO"
            }
            month.substring(3,5) == "10" -> {
                newString = "OUTUBRO"
            }
            month.substring(3,5) == "11" -> {
                newString = "NOVEMBRO"
            }
            month.substring(3,5) == "12" -> {
                newString = "DEZEMBRO"
            }
        }

        return newString
    }
}
