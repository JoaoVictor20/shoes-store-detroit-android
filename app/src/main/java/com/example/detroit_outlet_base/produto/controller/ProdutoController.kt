package com.example.detroit_outlet.produto.controller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.detroit_outlet.produto.model.ProdutoModel
import com.example.detroit_outlet.core.RestCallListener
import com.example.retrofit.model.dao.ClienteDao

class ProdutoController {

    private var clienteDao: ClienteDao = ClienteDao()

    private var mutableListClienteModel = MutableLiveData<List<ProdutoModel>>()
    var liveMutableListClienteModel: LiveData<List<ProdutoModel>> = mutableListClienteModel

    private var mutableConfirmationRequestApi = MutableLiveData<Boolean>()
    var liveMutableConfirmationRequestApi: LiveData<Boolean> = mutableConfirmationRequestApi

    private var mutableErrorRequestApi = MutableLiveData<String>()
    var liveMutableErrorApiRequest: LiveData<String> = mutableErrorRequestApi

//    fun getClienteById(id: Int) {
//        clienteDao.getClienteById(object : RestCallListener<ClienteModel> {
//            override fun onSuccess(data: ClienteModel?, httpStatusCode: Int) {
//                mutableClienteModel.value = data?
//            }
//
//            override fun onFailure(throwable: Throwable) {
//                mutableErrorApiRequest.value = throwable.message
//            }
//        }, id)
//    }

    fun getAllClientes() {
        clienteDao.getAllClientes(object : RestCallListener<List<ProdutoModel>> {
            override fun onSuccess(data: List<ProdutoModel>?, httpStatusCode: Int) {
                if (data != null) {
                    mutableListClienteModel.value = data!!
                } else {
                    mutableListClienteModel.value = arrayListOf()
                }
            }

            override fun onFailure(throwable: Throwable) {
                mutableErrorRequestApi.value = throwable.message
            }
        })
    }

    fun insertNewCliente(newClienteModel: ProdutoModel) {
        clienteDao.insertNewCliente(object : RestCallListener<ProdutoModel> {
            override fun onSuccess(data: ProdutoModel?, httpStatusCode: Int) {
                if (data != null) {
                    mutableConfirmationRequestApi.value = true
                }
            }

            override fun onFailure(throwable: Throwable) {
                mutableErrorRequestApi.value = throwable.message
            }
        }, newClienteModel)
    }

    fun deleteCliente(id: Int) {
        clienteDao.deleteCliente(object : RestCallListener<ProdutoModel> {
            override fun onSuccess(data: ProdutoModel?, httpStatusCode: Int) {
                if (httpStatusCode == 204) {
                    mutableConfirmationRequestApi.value = true
                }
            }

            override fun onFailure(throwable: Throwable) {
                mutableErrorRequestApi.value = throwable.message
            }
        }, id)
    }

    fun updateCliente(id: Int, newClienteModel: ProdutoModel) {
        clienteDao.updateCliente(object : RestCallListener<ProdutoModel> {
            override fun onSuccess(data: ProdutoModel?, httpStatusCode: Int) {
                if (httpStatusCode == 204) {
                    mutableConfirmationRequestApi.value = true
                }
            }

            override fun onFailure(throwable: Throwable) {
                mutableErrorRequestApi.value = throwable.message
            }
        }, id, newClienteModel)
    }

    fun loadMutableListClienteModelEmpty() {
        mutableListClienteModel.value = arrayListOf()
    }

    fun loadMutableConfirmationRequestApiFalse() {
        mutableConfirmationRequestApi.value = false
    }

    fun loadMutableErrorRequestApi() {
        mutableErrorRequestApi.value = ""
    }
}