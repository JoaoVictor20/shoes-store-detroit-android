package com.example.detroit_outlet_base.cliente.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.detroit_outlet.cliente.controller.ClienteController
import com.example.detroit_outlet.cliente.model.ClienteModel
import com.example.detroit_outlet_base.databinding.FragmentClienteBinding
import java.util.*

class ClienteFragment : Fragment() {

    var clienteController: ClienteController = ClienteController()

    lateinit var editTextCodigoCliente: EditText
    lateinit var editTextNomeCliente: EditText
    lateinit var editTextTelefoneCliente: EditText
    lateinit var buttonLimparCliente: Button
    lateinit var buttonSaveCliente: Button
    lateinit var buttonDeleteCliente: Button
    lateinit var SearchViewPesquisaCliente: SearchView
    lateinit var listViewClientes: ListView

    var listClienteModel: List<ClienteModel> = arrayListOf()
    private var _binding: FragmentClienteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClienteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initializeComponents()
        loadAllClientes()

        buttonLimparCliente.setOnClickListener {
            limparCampos()
        }

        buttonSaveCliente.setOnClickListener {
            salvarCliente()
        }

        buttonDeleteCliente.setOnClickListener {
            excluirCliente()
        }

        SearchViewPesquisaCliente.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
//                    loadListViewFilterNome(p0)
                }
                return false
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun loadAllClientes() {
        clienteController.getAllClientes()
    }

    fun limparCampos() {
        editTextCodigoCliente.setText("")
        editTextNomeCliente.setText("")
        editTextTelefoneCliente.setText("")
    }

    fun salvarCliente() {

        var clienteModel = ClienteModel()
        clienteModel.nomeCliente =
            editTextNomeCliente.text.toString().uppercase(Locale.getDefault())
        clienteModel.telefoneCliente = editTextTelefoneCliente.text.toString()

        if (clienteModel.nomeCliente == "" || clienteModel.telefoneCliente == "") {
            Toast.makeText(activity, "Digite o nome e telefone!", Toast.LENGTH_SHORT)
                .show()
        } else {
            if (editTextCodigoCliente.text.toString().isEmpty()) {
                clienteController.insertNewCliente(clienteModel)
            } else {
                clienteModel.idCliente = Integer.parseInt(editTextCodigoCliente.text.toString())
                clienteController.updateCliente(clienteModel.idCliente, clienteModel)
            }
        }
    }

    fun excluirCliente() {
        var codigo: String = editTextCodigoCliente.text.toString()
        if (codigo != null && codigo != "") {
            clienteController.deleteCliente(Integer.parseInt(editTextCodigoCliente.text.toString()))
        } else {
            Toast.makeText(activity, "Escolha um item da lista para excluir!", Toast.LENGTH_SHORT)
                .show()
        }
        SearchViewPesquisaCliente.cancelLongPress()
    }

//    private fun loadListViewFilterNome(p0: String) {
//
//        var a: Int = 0
//        var listAux: ArrayList<ClienteModel> = arrayListOf()
//
//        while (a < listClienteModel.size) {
//
//            if (listClienteModel[a].nome.contains(p0.toUpperCase())) {
//                listAux.add(listClienteModel[a])
//            }
//            a = a + 1
//        }
//
//        listViewClientes.adapter = listAux?.let {
//            activity?.let { it1 ->
//                AdapterListCliente(
//                    it1, it
//                )
//            }
//        }
//    }

    private fun loadListViewComClientes() {
        listViewClientes.adapter = listClienteModel?.let {
            activity?.let { it1 ->
                AdapterListCliente(
                    it1, it
                )
            }
        }
    }

    fun clickItemList() {
        listViewClientes.setOnItemClickListener { parent, view, position, id ->

            editTextCodigoCliente.setText(listClienteModel[position].idCliente.toString())
            editTextNomeCliente.setText(listClienteModel[position].nomeCliente)
            editTextTelefoneCliente.setText(listClienteModel[position].telefoneCliente)
        }
    }

    init {
        clienteController.liveMutableListClienteModel.observe(this, Observer { listClienteModel ->
            if (!listClienteModel.isNullOrEmpty()) {
                loadListClienteModel(listClienteModel)
                loadListViewComClientes()
                clickItemList()
                loadMutableListClienteModelEmpty()
            }
        })

        clienteController.liveMutableConfirmationRequestApi.observe(
            this,
            Observer {
                if (it) {
                    loadAllClientes()
                    loadmutableConfirmationRequestApi()
                }
            })
    }

    fun initializeComponents() {
        editTextCodigoCliente = _binding!!.editTextCodigoCliente
        editTextNomeCliente = _binding!!.editTextNomeCliente
        editTextTelefoneCliente = _binding!!.editTextTelefoneCliente
        buttonLimparCliente = _binding!!.buttonsCliente.buttonClienteLimpar
        buttonSaveCliente = _binding!!.buttonsCliente.buttonClienteSalvar
        buttonDeleteCliente = _binding!!.buttonsCliente.buttonClienteExcluir
        SearchViewPesquisaCliente = _binding!!.editTextPesquisaCliente
        listViewClientes = _binding!!.listViewCliente
    }

    fun loadListClienteModel(listClienteModel: List<ClienteModel>) {
        this.listClienteModel = listClienteModel
    }

    fun loadMutableListClienteModelEmpty() {
        clienteController.loadMutableListClienteModelEmpty()
    }

    fun loadmutableConfirmationRequestApi() {
        clienteController.loadMutableConfirmationRequestApiFalse()
    }
}