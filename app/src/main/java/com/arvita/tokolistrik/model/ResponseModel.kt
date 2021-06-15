package com.arvita.tokolistrik.model

class ResponseModel {
    var success = 0
    lateinit var message:String
    var user = User()
    var produks:ArrayList<Produk> = ArrayList()
}
