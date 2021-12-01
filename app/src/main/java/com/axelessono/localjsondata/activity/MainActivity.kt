package com.axelessono.localjsondata.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axelessono.localjsondata.R
import com.axelessono.localjsondata.adapter.TVShowAdapter
import com.axelessono.localjsondata.model.TVShow
import com.axelessono.localjsondata.model.TVShowResponse
import com.google.gson.Gson
import org.json.JSONException
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    lateinit var tvShows: ArrayList<TVShow>

    private lateinit var recyclerTVShowHome : RecyclerView

    // LayoutManager
    lateinit var layoutManager: LinearLayoutManager

    // Adapter
    lateinit var tvShowAdapter: TVShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Bind View
        bindView()

        getJsonObjectRoot()

        setRecyclerTVShowHome()

    }

    private fun bindView() {
        recyclerTVShowHome = findViewById(R.id.recyclerTVShowHome)
    }

    private fun getJsonObjectRoot(){

        var json : String? = null
        try {

            // Access to tv_show_data.json
            val inputStream : InputStream = assets.open("tv_show_data.json")

            // Load tv_show_data.json text value into string variable json
            json = inputStream.bufferedReader().use { it.readText() }

            // Convert json String into TVShowResponse object
            val gson = Gson()
            val tvShowResponse = gson.fromJson(json, TVShowResponse::class.java)

            // Load tvShows: ArrayList<TVShow>
            initTVShowList(tvShowResponse)

        }catch (e : IOException){
            e.printStackTrace()
        }catch (jsonException : JSONException){
            jsonException.printStackTrace()
        }
    }

    private fun initTVShowList(tvShowResponse: TVShowResponse?) {
        tvShows = tvShowResponse!!.tvShows
    }

    private fun setRecyclerTVShowHome() {

        // 1 - Init layoutManager
        layoutManager = LinearLayoutManager(applicationContext).apply {
            LinearLayoutManager.VERTICAL
        }

        // 2 - Init adapter
        tvShowAdapter = TVShowAdapter(this@MainActivity, tvShows){

            // Pass data from MainActivity to DetailActivity
            val intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra("TVSHOW", it)
            startActivity(intent)
        }

        // Attach adapter to RecyclerView
        recyclerTVShowHome.adapter = tvShowAdapter

        /**
         * On pouvait aussi écrire :
         * recyclerTVShowHome.adapter = TVShowAdapter(this@MainActivity, tvShows){
                      Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT).show()
                   }
         */
    }
}