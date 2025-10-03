package br.unifor.travobasic.retrofit

import br.unifor.travobasic.model.LoginRequest
import br.unifor.travobasic.model.LoginResponse
import br.unifor.travobasic.model.RegistroRequest
import br.unifor.travobasic.model.UsuariosResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TravoServiceAPI {

    @POST("usuariosOrg")
    suspend fun registrar(@Body registroRequest: RegistroRequest): Response<Unit>

    @POST("usuariosOrg/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("usuarios")
    suspend fun getAllUsers():Response<List<UsuariosResponse>>

}