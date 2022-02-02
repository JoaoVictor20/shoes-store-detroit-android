package com.example.retrofit.model.dao

import com.example.detroit_outlet.cliente.model.ClienteModel
import com.example.detroit_outlet.core.RestCallListener
import com.example.detroit_outlet_base.core.RetrofitConfiguration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClienteDao {

//    fun getClienteById(restCallListener: RestCallListener<ClienteModel>, id: Int) {
//        val remote = RetrofitCliente.createService(RequestClienteDao::class.java)
//        val call: Call<ClienteModel> = remote.listById(id)
//        call.enqueue(object : Callback<ClienteModel> {
//            override fun onResponse(call: Call<ClienteModel>, res: Response<ClienteModel>) {
//                restCallListener.onSuccess(res.body(), res.code())
//            }
//
//            override fun onFailure(call: Call<ClienteModel>, t: Throwable) {
//                restCallListener.onFailure(t)
//            }
//        })
//    }

    fun getAllClientes(restCallListener: RestCallListener<List<ClienteModel>>) {
        val remote = RetrofitConfiguration.createService(RequestProdutoDao::class.java)
        val call: Call<List<ClienteModel>> = remote.list()
        call.enqueue(object : Callback<List<ClienteModel>> {
            override fun onResponse(
                call: Call<List<ClienteModel>>,
                res: Response<List<ClienteModel>>
            ) {
                restCallListener.onSuccess(res.body(), res.code())
            }

            override fun onFailure(call: Call<List<ClienteModel>>, t: Throwable) {
                restCallListener.onFailure(t)
            }
        })
    }

    fun insertNewCliente(
        restCallListener: RestCallListener<ClienteModel>,
        postModel: ClienteModel
    ) {
        val remote = RetrofitConfiguration.createService(RequestProdutoDao::class.java)
        val call: Call<ClienteModel> = remote.inserir(postModel)
        call.enqueue(object : Callback<ClienteModel> {
            override fun onResponse(call: Call<ClienteModel>, res: Response<ClienteModel>) {
                restCallListener.onSuccess(res.body(), res.code())
            }

            override fun onFailure(call: Call<ClienteModel>, t: Throwable) {
                restCallListener.onFailure(t)
            }
        })
    }

    fun deleteCliente(restCallListener: RestCallListener<ClienteModel>, id: Int) {
        val remote = RetrofitConfiguration.createService(RequestProdutoDao::class.java)
        val call: Call<ClienteModel> = remote.delete(id)
        call.enqueue(object : Callback<ClienteModel> {
            override fun onResponse(call: Call<ClienteModel>, res: Response<ClienteModel>) {
                restCallListener.onSuccess(res.body(), res.code())
            }

            override fun onFailure(call: Call<ClienteModel>, t: Throwable) {
                restCallListener.onFailure(t)
            }
        })
    }

    fun updateCliente(
        restCallListener: RestCallListener<ClienteModel>,
        id: Int,
        postModel: ClienteModel
    ) {
        val remote = RetrofitConfiguration.createService(RequestProdutoDao::class.java)
        val call: Call<ClienteModel> = remote.updatePut(id, postModel)
        call.enqueue(object : Callback<ClienteModel> {
            override fun onResponse(call: Call<ClienteModel>, res: Response<ClienteModel>) {
                restCallListener.onSuccess(res.body(), res.code())
            }

            override fun onFailure(call: Call<ClienteModel>, t: Throwable) {
                restCallListener.onFailure(t)
            }
        })
    }
}