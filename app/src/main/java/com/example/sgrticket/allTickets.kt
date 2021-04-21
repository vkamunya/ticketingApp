package com.example.sgrticket

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.AsyncTask.execute
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Adapter
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sgrticket.adapters.AllTicketsAdapter
import com.example.sgrticket.databinding.ActivityAllTicketsBinding
import com.example.sgrticket.models.Ticket
import org.json.JSONArray
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList

private lateinit var binding: ActivityAllTicketsBinding
private var tickets = ArrayList<Ticket>()
private lateinit var ticketAdapter: AllTicketsAdapter



class AllTickets : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllTicketsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_all_tickets)

        val recyclerView: RecyclerView = findViewById(R.id.RecyclerView)
        ticketAdapter = AllTicketsAdapter(tickets)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ticketAdapter
        recyclerView.setHasFixedSize(true)
        MyAsyncTask(applicationContext).execute()
    }
    class MyAsyncTask internal constructor(context: Context) : AsyncTask<String, String, String>() {
            lateinit var con: HttpURLConnection
            lateinit var resulta: String
            val builder = Uri.Builder()

            private val cont: Context = context

            val recyclerView= binding.RecyclerView

            override fun onPreExecute() {
                super.onPreExecute()

                val progressBar = ProgressBar(cont)
                progressBar.isIndeterminate = true
                progressBar.visibility = View.VISIBLE


            }


            override fun doInBackground(vararg params: String?): String? {
                try {

                    var query = builder.build().encodedQuery
                    val url: String =
                        "https://wizard-transiting.000webhostapp.com/sgr/alltickets.php"
                    val obj = URL(url)
                    con = obj.openConnection() as HttpURLConnection
                    con.setRequestMethod("GET")
                    con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)")
                    con.setRequestProperty("Accept-Language", "UTF-8")
                    con.setDoOutput(true)
                    val outputStreamWriter = OutputStreamWriter(con.getOutputStream())
                    outputStreamWriter.write(query)
                    outputStreamWriter.flush()
                    Log.e("pass 1", "connection success ")
                } catch (e: Exception) {
                    Log.e("Fail 1", e.toString())
                }
                try {
                    resulta = con.inputStream.bufferedReader().readText()
                    Log.e("data", resulta)
                } catch (e: java.lang.Exception) {
                    Log.e("Fail 2", e.toString())
                }
                return "";
            }
            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                val progressBar = ProgressBar(cont)
                progressBar.visibility = View.GONE
                var json_data = JSONArray(resulta)


                for (i in 0 until json_data.length()) {
                    val jsonObject = json_data.getJSONObject(i)
                    val name = jsonObject.optString("name")
                    val tktNumber = jsonObject.optString("ticketnumber")
                    val source = jsonObject.optString("source")
                    val destination = jsonObject.optString("destination")
                    tickets.add(Ticket(name,tktNumber,source,destination))


                }
                recyclerView.adapter = AllTicketsAdapter(tickets)
                ticketAdapter.notifyDataSetChanged()

                Log.e("data", json_data.toString())

            }
        }
    }
