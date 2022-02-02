package com.example.detroit_outlet.produto.model

import com.google.gson.annotations.SerializedName

class ProdutoModel {

    @SerializedName("idcliente")
    var idCliente: Int = 0

    @SerializedName("nomecliente")
    var nomeCliente: String = ""

    @SerializedName("telefonecliente")
    var telefoneCliente: String = ""
}