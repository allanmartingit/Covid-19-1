package com.lopes.covidworld

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lopes.covidworld.activitys.CountryInformations
import kotlinx.android.synthetic.main.item.view.*

class CountrysAdapter (private val countrys: List<Countrys>): RecyclerView.Adapter<CountrysAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        val vh = VH(v)
        vh.itemView.setOnClickListener {
            val intent = Intent(v.context, CountryInformations::class.java)
            var arrayCountry = countrys[vh.adapterPosition]
            intent.putExtra("Country", arrayCountry)
            v.context.startActivity(intent)
        }
        return vh
    }

    override fun getItemCount(): Int {
        return countrys.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        var country = countrys[position]
        holder.countryName.text = country.country
        holder.countryCases.text = country.cases.toString()
        holder.countryDay.text = country.data.toString().substring(0,2)
        holder.countryMonth.text = alterMonth(country.data.toString())
        holder.countryYear.text = country.data.toString().substring(6,10)
        //holder.countryCaution.setBackgroundColor(setColor(country.cases))
    }


    class VH(itemView: View):RecyclerView.ViewHolder(itemView){
        var countryName:TextView = itemView.name
        var countryCases:TextView = itemView.nCases
        var countryDay:TextView = itemView.dia
        var countryMonth:TextView = itemView.mes
        var countryYear:TextView = itemView.ano
        //var countryCaution:View = itemView.caution
    }

    fun alterMonth(month: String): String? {
        var newString: String? = ""
        when {
            month.substring(3,5) == "01" -> {
                newString = "JAN"
            }
            month.substring(3,5) == "02" -> {
                newString = "FEV"
            }
            month.substring(3,5) == "03" -> {
                newString = "MAR"
            }
            month.substring(3,5) == "04" -> {
                newString = "ABR"
            }
            month.substring(3,5) == "05" -> {
                newString = "MAI"
            }
            month.substring(3,5) == "06" -> {
                newString = "JUN"
            }
            month.substring(3,5) == "07" -> {
                newString = "JUL"
            }
            month.substring(3,5) == "08" -> {
                newString = "AGO"
            }
            month.substring(3,5) == "09" -> {
                newString = "SET"
            }
            month.substring(3,5) == "10" -> {
                newString = "OUT"
            }
            month.substring(3,5) == "11" -> {
                newString = "NOV"
            }
            month.substring(3,5) == "12" -> {
                newString = "DEZ"
            }
        }

        return newString
    }

    fun setColor(n: Int): Int {

        if(n < 2000){
            return Color.GREEN
        }else if (n < 6000){
            return Color.YELLOW
        }else{
            return Color.RED
        }
    }
}