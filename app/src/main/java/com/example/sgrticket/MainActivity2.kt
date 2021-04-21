package com.example.sgrticket

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.sgrticket.databinding.ActivityMain2Binding
import com.example.sgrticket.models.Ticket
import org.json.JSONArray
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

private lateinit var binding: ActivityMain2Binding
lateinit var progressBar: ProgressBar

class MainActivity2 : AppCompatActivity() {
    val query: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        var view = binding.root

        setContentView(view)
        progressBar= binding.progressBar

        binding.LoginBtn.setOnClickListener {
            val login = MyAsyncTask(applicationContext)
            val password: String = binding.passtxt.text.toString()
            if(password.trim().length>0) {
                Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Password cannot be empty ", Toast.LENGTH_SHORT).show()
            }


            login.execute()


        }
        binding.SignUpText.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }
    companion object {
        class MyAsyncTask internal constructor(context: Context) : AsyncTask<String, String, String>() {
            lateinit var con: HttpURLConnection
            lateinit var resulta: String

            val builder = Uri.Builder()
            private val cont: Context = context
            override fun onPreExecute() {
                super.onPreExecute()

                progressBar.isIndeterminate()
                progressBar.visibility = View.VISIBLE


                val phone: String = binding.phonetxt.text.toString()
                val password: String = binding.passtxt.text.toString()




                builder.appendQueryParameter("phone", phone)
                builder.appendQueryParameter("password", password)


            }

            override fun doInBackground(vararg params: String?): String? {
                try {

                    var query = builder.build().encodedQuery
                    val url: String = "http://wizard-transiting.000webhostapp.com/sgr/login.php"
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
                return null;
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                val progressBar = ProgressBar(cont)
                progressBar.visibility = View.GONE
                var json_data = JSONObject(resulta)
                fun isResultaInitialized() = ::resulta.isInitialized
                val code: Int = json_data.getInt("code")
                Log.e("data", code.toString())

                if (code == 1) {
                    //val com: JSONArray = json_data.getJSONArray("userdetails")
                    //  val comObject = com[0] as JSONObject
                    // Log.e("data",""+comObject.optString("fname"))


                    val toMain = Intent(cont, AllTickets::class.java)
                    toMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    cont.startActivity(toMain)}

                Log.e("data", json_data.toString())

            }

        }
    }



}




