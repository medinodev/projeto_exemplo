package br.unifor.travobasic.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.unifor.travobasic.R
import br.unifor.travobasic.activity.RegistroActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.main_btn_registro).setOnClickListener { v ->
            val it = Intent(this, RegistroActivity::class.java)
            startActivity(it)
        }
    }
}