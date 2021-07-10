package com.arvita.tokolistrik.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arvita.tokolistrik.R
import com.arvita.tokolistrik.adapter.AdapterBank
import com.arvita.tokolistrik.app.ApiConfig
import com.arvita.tokolistrik.helper.Helper
import com.arvita.tokolistrik.model.Bank
import com.arvita.tokolistrik.model.Chekout
import com.arvita.tokolistrik.model.ResponseModel
import com.arvita.tokolistrik.model.Transaksi
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_pembayaran.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PembayaranActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)
        Helper().setToolbar(this, toolbar, "Pembayaran")
        displayBank()
    }

    fun displayBank(){

            val arrBank = ArrayList<Bank>()
            arrBank.add(Bank("BRI", "86721349128", "Budi Atmaja", R.drawable.logo_bri))

            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL

            rv_data.layoutManager = layoutManager
            rv_data.adapter = AdapterBank(arrBank, object : AdapterBank.Listeners {
                override fun onClicked(data: Bank, index: Int) {
                    bayar(data)
                }
            })

    }
    fun bayar(bank :Bank){

        val json = intent.getStringExtra("extra")!!.toString()
        val chekout =Gson().fromJson(json,Chekout::class.java)
        chekout.bank = bank.nama

        ApiConfig.instanceRetrofit.chekout(chekout).enqueue(object:
            Callback<ResponseModel> {
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
      //          Toast.makeText( this@PengirimanActivity,"Error"+t.message, Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val respon = response.body()!!
                if(respon.success == 1){
                    val jsBank = Gson().toJson(bank, Bank::class.java)
                    val jsTransaksi = Gson().toJson(respon.transaksi, Transaksi::class.java)
                    val jsChekout = Gson().toJson(chekout, Chekout::class.java)

                    val intent = Intent(this@PembayaranActivity, SuccessActivity::class.java)
                    intent.putExtra("bank", jsBank)
                    intent.putExtra("transaksi", jsTransaksi)
                    intent.putExtra("chekout", jsChekout)
                    startActivity(intent)
                }else{
                    //gagal
                    Toast.makeText( this@PembayaranActivity,"Error"+respon.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
