package br.unifor.travobasic.retrofit

import br.unifor.travobasic.model.Registro
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TravoService {

    @POST("usuariosOrg")
    suspend fun registrar(@Body registro: Registro): Response<Unit>

}