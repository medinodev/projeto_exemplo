package br.unifor.travobasic.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.unifor.travobasic.R
import br.unifor.travobasic.model.LoginRequest
import br.unifor.travobasic.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {

    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText
    private lateinit var tvRegister: TextView
    private lateinit var btnSignIn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        this.edEmail = findViewById(R.id.login_ed_email)
        this.edPassword = findViewById(R.id.login_ed_password)
        this.tvRegister = findViewById(R.id.login_tv_register)
        this.btnSignIn = findViewById(R.id.login_btn_singup)

        this.tvRegister.setOnClickListener { view ->
            val it = Intent(this, RegistroActivity::class.java)
            startActivity(it)
        }

        this.btnSignIn.setOnClickListener { view ->

            if (isValidForm()) {
                val email = edEmail.text.toString()
                val password = edPassword.text.toString()

                val travoServiceAPI = RetrofitService.getTravoServiceAPI()

                val login = LoginRequest(email, password)

                CoroutineScope(Dispatchers.IO).launch {
                    val response = travoServiceAPI.login(login)
                    if (response.isSuccessful) {
                        val loginResponse = response.body()

                        val sharedPref = getSharedPreferences("TravoBasic", Context.MODE_PRIVATE)
                        with(sharedPref.edit()) {
                            putString("token", loginResponse!!.token)
                            apply()
                        }


                        // Executar na thread principal
                        withContext(Dispatchers.Main){
                            Toast.makeText(
                                this@LoginActivity,
                                loginResponse?.mensagem,
                                Toast.LENGTH_SHORT)
                                .show()

                            val it = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(it)
                        }

                    }
                }


            }

        }

    }

    private fun isValidForm(): Boolean {
        var isValid = true

        if (edEmail.text.isEmpty()) {
            edEmail.error = "Este campo não pode ser vazio."
            isValid = false
        }

        if (edPassword.text.isEmpty()) {
            edPassword.error = "Este campo não pode ser vazio."
            isValid = false
        }

        return isValid
    }
}