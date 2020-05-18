package com.lopes.covidworld.activitys

import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lopes.covidworld.*
import kotlinx.android.synthetic.main.activity_all_states.*

class all_countrys : AppCompatActivity() {

    private var listContrys = arrayListOf<Countrys>()
    private var adapter = CountrysAdapter(listContrys)
    private var asyncTask : CountryTask? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_countrys)
        CarregaDados()
        rvDados.layoutManager = GridLayoutManager(this,2) as RecyclerView.LayoutManager?
        rvDados.itemAnimator = DefaultItemAnimator()
        rvDados.adapter = adapter

        appBar.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@all_countrys, HomeActivity::class.java)
                startActivity(intent)
            }
        })

    }

    fun showProgress(show: Boolean){
        if(show){
            txtMsg.text = "Carregando..."
        }else{
            txtMsg.visibility = if(show) View.VISIBLE else View.GONE
            progressBar.visibility = if(show) View.VISIBLE else View.GONE
        }
    }

    fun CarregaDados(){
        listContrys.clear()
        if(listContrys.isNotEmpty()){
            showProgress(false)
        }else{
            if(asyncTask==null){
                if(HttpCountrys.hasConnection(this)){
                    if(asyncTask?.status != AsyncTask.Status.RUNNING){
                        asyncTask = CountryTask()
                        asyncTask?.execute()
                    }
                }else{
                    progressBar.visibility = View.GONE
                }
            }else if(asyncTask?.status== AsyncTask.Status.RUNNING){
                showProgress(true)
            }
        }
    }

    inner class CountryTask: AsyncTask<Void, Void, List<Countrys?>>(){

        override fun onPreExecute() {
            super.onPreExecute()
            showProgress(true)
        }


        @RequiresApi(Build.VERSION_CODES.O)
        override fun doInBackground(vararg params: Void?): List<Countrys>? {
            return HttpCountrys.loadCountrys()
        }

        private fun update(result: List<Countrys>?){
            if(result != null){
                listContrys.clear()
                listContrys.addAll(result.reversed())
            }else{
                txtMsg.text = "Erro ao Carregar"
            }
            adapter.notifyDataSetChanged()
            asyncTask = null
        }

        override fun onPostExecute(result: List<Countrys?>?) {
            super.onPostExecute(result)
            showProgress(false)
            update(result as List<Countrys>?)
        }

    }
}
