package br.unifor.travobasic.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.unifor.travobasic.R
import br.unifor.travobasic.activity.RegistroActivity
import br.unifor.travobasic.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var edToken: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        edToken = findViewById(R.id.main_tv_token)

        val sharedPref = getSharedPreferences("TravoBasic", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", "no token")
        edToken.text = token

        Log.i("Travo Basic", "Token: $token")

        val travoServiceAPI = RetrofitService.getTravoServiceAPIWithToken(token!!)

        CoroutineScope(Dispatchers.IO).launch {
            val response = travoServiceAPI.getAllUsers()
            if(response.isSuccessful) {
                val usuarios = response.body()

                usuarios?.forEach {
                    Log.i("Travo Basic", "[Usuario](id: ${it.id}, nome: ${it.nomeCompleto})")
                }
            }
        }

    }
}