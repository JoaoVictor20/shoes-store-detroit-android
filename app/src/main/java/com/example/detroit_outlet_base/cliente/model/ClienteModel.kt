package com.example.detroit_outlet.cliente.model

import com.google.gson.annotations.SerializedName

class ClienteModel {

    @SerializedName("idcliente")
    var idCliente: Int = 0

    @SerializedName("nomecliente")
    var nomeCliente: String = ""

    @SerializedName("telefonecliente")
    var telefoneCliente: String = ""
}