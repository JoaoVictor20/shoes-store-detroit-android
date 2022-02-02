package com.example.retrofit.model.dao

import com.example.detroit_outlet.cliente.model.ProdutoModel
import retrofit2.Call
import retrofit2.http.*

interface RequestProdutoDao {

    @GET("clientes")
    fun list(): Call<List<ProdutoModel>>

    @GET("clientes/{id}")
    fun listById(@Path("id") id: Int): Call<ProdutoModel>

    @POST("clientes")
    fun inserir(@Body clienteModel: ProdutoModel): Call<ProdutoModel>

    @DELETE("clientes/{id}")
    fun delete(@Path("id") id: Int): Call<ProdutoModel>

    @PUT("clientes/{id}")
    fun updatePut(@Path("id") id: Int, @Body clienteModel: ProdutoModel): Call<ProdutoModel>
}