package br.unifor.travobasic.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.unifor.travobasic.R
import br.unifor.travobasic.model.RegistroRequest
import br.unifor.travobasic.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistroActivity : AppCompatActivity() {

    lateinit var edNomeFantasia: EditText
    lateinit var edEmail: EditText
    lateinit var edTelefone: EditText
    lateinit var edSenha: EditText
    lateinit var btnCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_regitro)

        edNomeFantasia = findViewById(R.id.registro_ed_nome_fantasia)
        edEmail = findViewById(R.id.registro_ed_email)
        edTelefone = findViewById(R.id.registro_ed_telefone)
        edSenha = findViewById(R.id.registro_ed_senha)
        btnCadastrar = findViewById(R.id.registro_btn_cadastrar)

        btnCadastrar.setOnClickListener { view ->

            val nomeFantasia = edNomeFantasia.text.toString()
            val email = edEmail.text.toString()
            val telefone = edTelefone.text.toString()
            val senha = edSenha.text.toString()

            val registroRequest = RegistroRequest(
                nomeFantasia,
                telefone,
                email,
                senha
            )

            val travoServiceAPI = RetrofitService.getTravoServiceAPI()

            CoroutineScope(Dispatchers.IO).launch {
                val response = travoServiceAPI.registrar(registroRequest)
                if(response.isSuccessful){
                    withContext(Dispatchers.Main){
                        Toast.makeText(
                            this@RegistroActivity,
                            "Registro realizado com sucesso!",
                            Toast.LENGTH_LONG)
                            .show()
                        finish()
                    }
                }
            }

            Log.i("Travo Basic", nomeFantasia.toString())
        }
    }
}