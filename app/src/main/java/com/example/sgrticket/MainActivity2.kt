package com.example.sgrticket

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.sgrticket.databinding.ActivityMain2Binding
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

private lateinit var binding: ActivityMain2Binding
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val button = findViewById<Button>(R.id.LoginBtn)
        button.setOnClickListener {
            val login=MyAsyncTask(applicationContext)
            login.execute()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val signup = findViewById<TextView>(R.id.SignUpText)
        signup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }

    companion object {
        class MyAsyncTask internal constructor(context: Context) : AsyncTask<String, String, String>() {
            lateinit var con:HttpURLConnection
            lateinit var resulta:String
            val builder = Uri.Builder()
            private val cont:Context=context
            override fun onPreExecute() {
                super.onPreExecute()

                val progressBar=ProgressBar(cont)
                progressBar.isIndeterminate=true
                progressBar.visibility= View.VISIBLE
                builder.appendQueryParameter("id", "0776646981")
                builder.appendQueryParameter("name", "kimani")
            }

            override fun doInBackground(vararg params: String?):  String? {
                try {

                    var query = builder.build().encodedQuery
                    val url: String = "https://wizard-transiting.000webhostapp.com/sgr/login.php?id=0776646981"
                    val obj = URL(url)
                    con = obj.openConnection() as HttpURLConnection
                    con.setRequestMethod("POST")
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
                fun onPostExecute(result: String?) {
                    super.onPostExecute(result)

                    var json_data = JSONObject(resulta)
                    val code: Int = json_data.getInt("code")
                    Log.e("data",code.toString())
                    if (code == 1) {
//                        val com: JSONArray = json_data.getJSONArray("userdetails")
//                        val comObject = com[0] as JSONObject
//                        Log.e("data",""+comObject.optString("fname"))
                        val toMain = Intent(cont, MainActivity::class.java)
                        cont.startActivity(toMain)
                    }
                }

                return null;
            }
        }

    }
}