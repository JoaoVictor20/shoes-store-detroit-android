package com.example.detroit_outlet.cliente.controller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.detroit_outlet.cliente.model.ClienteModel
import com.example.detroit_outlet.core.RestCallListener
import com.example.retrofit.model.dao.ClienteDao

class ClienteController {

    private var clienteDao: ClienteDao = ClienteDao()

    private var mutableListClienteModel = MutableLiveData<List<ClienteModel>>()
    var liveMutableListClienteModel: LiveData<List<ClienteModel>> = mutableListClienteModel

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
        clienteDao.getAllClientes(object : RestCallListener<List<ClienteModel>> {
            override fun onSuccess(data: List<ClienteModel>?, httpStatusCode: Int) {
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

    fun insertNewCliente(newClienteModel: ClienteModel) {
        clienteDao.insertNewCliente(object : RestCallListener<ClienteModel> {
            override fun onSuccess(data: ClienteModel?, httpStatusCode: Int) {
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
        clienteDao.deleteCliente(object : RestCallListener<ClienteModel> {
            override fun onSuccess(data: ClienteModel?, httpStatusCode: Int) {
                if (httpStatusCode == 204) {
                    mutableConfirmationRequestApi.value = true
                }
            }

            override fun onFailure(throwable: Throwable) {
                mutableErrorRequestApi.value = throwable.message
            }
        }, id)
    }

    fun updateCliente(id: Int, newClienteModel: ClienteModel) {
        clienteDao.updateCliente(object : RestCallListener<ClienteModel> {
            override fun onSuccess(data: ClienteModel?, httpStatusCode: Int) {
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