package com.example.retrofit.model.dao

import com.example.detroit_outlet.produto.model.ProdutoModel
import com.example.detroit_outlet.core.RestCallListener
import com.example.detroit_outlet_base.core.RetrofitConfiguration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdutoDao {

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

    fun getAllClientes(restCallListener: RestCallListener<List<ProdutoModel>>) {
        val remote = RetrofitConfiguration.createService(RequestProdutoDao::class.java)
        val call: Call<List<ProdutoModel>> = remote.list()
        call.enqueue(object : Callback<List<ProdutoModel>> {
            override fun onResponse(
                call: Call<List<ProdutoModel>>,
                res: Response<List<ProdutoModel>>
            ) {
                restCallListener.onSuccess(res.body(), res.code())
            }

            override fun onFailure(call: Call<List<ProdutoModel>>, t: Throwable) {
                restCallListener.onFailure(t)
            }
        })
    }

    fun insertNewCliente(
        restCallListener: RestCallListener<ProdutoModel>,
        postModel: ProdutoModel
    ) {
        val remote = RetrofitConfiguration.createService(RequestProdutoDao::class.java)
        val call: Call<ProdutoModel> = remote.inserir(postModel)
        call.enqueue(object : Callback<ProdutoModel> {
            override fun onResponse(call: Call<ProdutoModel>, res: Response<ProdutoModel>) {
                restCallListener.onSuccess(res.body(), res.code())
            }

            override fun onFailure(call: Call<ProdutoModel>, t: Throwable) {
                restCallListener.onFailure(t)
            }
        })
    }

    fun deleteCliente(restCallListener: RestCallListener<ProdutoModel>, id: Int) {
        val remote = RetrofitConfiguration.createService(RequestProdutoDao::class.java)
        val call: Call<ProdutoModel> = remote.delete(id)
        call.enqueue(object : Callback<ProdutoModel> {
            override fun onResponse(call: Call<ProdutoModel>, res: Response<ProdutoModel>) {
                restCallListener.onSuccess(res.body(), res.code())
            }

            override fun onFailure(call: Call<ProdutoModel>, t: Throwable) {
                restCallListener.onFailure(t)
            }
        })
    }

    fun updateCliente(
        restCallListener: RestCallListener<ProdutoModel>,
        id: Int,
        postModel: ProdutoModel
    ) {
        val remote = RetrofitConfiguration.createService(RequestProdutoDao::class.java)
        val call: Call<ProdutoModel> = remote.updatePut(id, postModel)
        call.enqueue(object : Callback<ProdutoModel> {
            override fun onResponse(call: Call<ProdutoModel>, res: Response<ProdutoModel>) {
                restCallListener.onSuccess(res.body(), res.code())
            }

            override fun onFailure(call: Call<ProdutoModel>, t: Throwable) {
                restCallListener.onFailure(t)
            }
        })
    }
}