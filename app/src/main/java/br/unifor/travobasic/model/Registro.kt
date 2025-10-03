package br.unifor.travobasic.model

import com.google.gson.annotations.SerializedName

data class Registro(
    @SerializedName("nome_fantasia")
    val nomeFantasia:String,
    val telefone: String,
    val email: String,
    val senha:String
)
