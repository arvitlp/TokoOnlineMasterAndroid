package com.arvita.tokolistrik.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.arvita.tokolistrik.R
import com.arvita.tokolistrik.helper.Helper
import com.arvita.tokolistrik.model.Produk
import com.arvita.tokolistrik.room.MyDatabase
import com.arvita.tokolistrik.util.Config
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail_produk.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.toolbar
import kotlinx.android.synthetic.main.toolbar_custom.*

class DetailProdukActivity : AppCompatActivity() {
    lateinit var produk :Produk
    lateinit var myDb: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)
        myDb = MyDatabase.getInstance(this)!! // call database

        getInfo()
        mainButton()
        cekkeranjang()
    }
    private fun mainButton(){
        btn_keranjang.setOnClickListener{
            val data = myDb.daoKeranjang().getProduk(produk.id)
            if (data == null){
                insert()
            }else{
                data.jumlah = data.jumlah +1
                update(data)
            }

            insert()
        }
        btn_favorit.setOnClickListener{
            val listData = myDb.daoKeranjang().getAll() // get All data
            for(note :Produk in listData){
                println("-----------------------")
                println(note.nama)
                println(note.harga)
            }
        }

        btn_tokeranjang.setOnClickListener {
            val intent = Intent("event:keranjang")
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            onBackPressed()
        }

    }

    private fun insert(){
        CompositeDisposable().add(Observable.fromCallable { myDb.daoKeranjang().insert(produk) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                cekkeranjang()
                Log.d("respons", "data inserted")
                Toast.makeText(this,"Barang masuk keranjang",Toast.LENGTH_SHORT).show()
            })
    }

    private fun update(data:Produk){
        CompositeDisposable().add(Observable.fromCallable { myDb.daoKeranjang().update(data) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                cekkeranjang()
                Log.d("respons", "data inserted")
                Toast.makeText(this,"Barang masuk keranjang",Toast.LENGTH_SHORT).show()
            })
    }

    private fun cekkeranjang(){
        val datakeranjang =myDb.daoKeranjang().getAll()
        if(datakeranjang.isNotEmpty()){
            div_angka.visibility = View.VISIBLE
            tv_angka.text = datakeranjang.size.toString()
        }else{
            div_angka.visibility = View.GONE
        }
    }

   private fun getInfo(){
        val data = intent.getStringExtra("extra")
         produk = Gson().fromJson<Produk>(data,Produk::class.java)
        //set value
        tv_nama.text = produk.nama
        tv_harga.text = Helper().gantiRupiah( produk                                                                                                                                    .harga)
        tv_deskripsi.text = produk.deskripsi

        val img = Config.productUrl  + produk.image
        Picasso.get()
            .load(img)
            .placeholder(R.drawable.l1)
            .error(R.drawable.l1)
            .resize(400,400)
            .into(image)

        //set Toolbar
        Helper().setToolbar(this,toolbar,produk.nama)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
