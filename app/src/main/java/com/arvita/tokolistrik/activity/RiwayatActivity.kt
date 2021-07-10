package com.arvita.tokolistrik.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arvita.tokolistrik.R
import com.arvita.tokolistrik.app.ApiConfig
import com.arvita.tokolistrik.helper.Helper
import com.arvita.tokolistrik.helper.SharedPref
import com.arvita.tokolistrik.model.ResponseModel
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)
        Helper().setToolbar(this, toolbar, "Riwayat Transaksi")
        getRiwayat()
    }

    fun getRiwayat(){

        val id = SharedPref(this).getUser()!!.id
        ApiConfig.instanceRetrofit.getRiwayat(id).enqueue(object:
            Callback<ResponseModel> {
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val res = response.body()!!
                if(res.success == 1){

                }
            }
        })



    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
