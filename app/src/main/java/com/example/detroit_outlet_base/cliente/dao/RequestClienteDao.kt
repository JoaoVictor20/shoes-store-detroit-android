package com.example.retrofit.model.dao

import com.example.detroit_outlet.cliente.model.ClienteModel
import retrofit2.Call
import retrofit2.http.*

interface RequestClienteDao {

    @GET("clientes")
    fun list(): Call<List<ClienteModel>>

    @GET("clientes/{id}")
    fun listById(@Path("id") id: Int): Call<ClienteModel>

    @POST("clientes")
    fun inserir(@Body clienteModel: ClienteModel): Call<ClienteModel>

    @DELETE("clientes/{id}")
    fun delete(@Path("id") id: Int): Call<ClienteModel>

    @PUT("clientes/{id}")
    fun updatePut(@Path("id") id: Int, @Body clienteModel: ClienteModel): Call<ClienteModel>
}