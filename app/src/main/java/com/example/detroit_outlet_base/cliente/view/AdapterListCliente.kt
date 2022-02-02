package com.example.detroit_outlet_base.cliente.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.detroit_outlet.cliente.model.ClienteModel
import com.example.detroit_outlet_base.R

class AdapterListCliente : BaseAdapter {

    private var context: Context
    private var clientes: List<ClienteModel> = arrayListOf()

    constructor(context: Context, clientes: List<ClienteModel>) : super() {
        this.context = context
        this.clientes = clientes
    }

    override fun getCount(): Int {
        return clientes.size
    }

    override fun getItem(p0: Int): Any {
        return clientes[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var cliente: ClienteModel = clientes[p0]

        var inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var layout = inflater.inflate(R.layout.cliente_layout_list, null)

        var codigo: TextView = layout.findViewById(R.id.codigo_item_list)
        var nome: TextView = layout.findViewById(R.id.nome_item_list)
        var telefone: TextView = layout.findViewById(R.id.telefone_item_list)

        var codigoString: Int = cliente.idCliente
        var nomeString: String = cliente.nomeCliente
        var telefoneString: String = cliente.telefoneCliente

        codigo.setText(codigoString.toString())
        nome.setText(nomeString)
        telefone.setText(telefoneString)

        return layout
    }
}