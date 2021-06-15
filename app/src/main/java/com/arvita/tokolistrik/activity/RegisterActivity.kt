package com.arvita.tokolistrik.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arvita.tokolistrik.MainActivity
import com.arvita.tokolistrik.R
import com.arvita.tokolistrik.app.ApiConfig
import com.arvita.tokolistrik.helper.SharedPref
import com.arvita.tokolistrik.model.ResponseModel
import kotlinx.android.synthetic.main.activity_masuk.btn_register
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var  s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        s=SharedPref(this)

        btn_register.setOnClickListener{
            register()
        }
    }
    fun register(){
        if (edt_nama.text.isEmpty()){
            edt_nama.error =" Nama tidak boleh kosong"
            edt_nama.requestFocus()
            return
        }else if (edt_email.text.isEmpty()){
            edt_email.error ="Email tidak boleh kosong"
            edt_email.requestFocus()
            return
        }else if (edt_telp.text.isEmpty()){
            edt_telp.error ="Telepon tidak boleh kosong"
            edt_telp.requestFocus()
            return
        }else if (edt_password.text.isEmpty()){
            edt_password.error ="Password tidak boleh kosong"
            edt_password.requestFocus()
            return
        }
        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.register(edt_nama.text.toString(),edt_email.text.toString(),edt_telp.text.toString(),edt_password.text.toString()).enqueue(object:Callback<ResponseModel>{
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText( this@RegisterActivity,"Error"+t.message,Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                pb.visibility = View.GONE
                val respon = response.body()!!
                if(respon.success == 1){
                    s.setStatusLogin(true)
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    //berhasil
                    Toast.makeText( this@RegisterActivity,"Register Berhasil"+respon.user.name,Toast.LENGTH_SHORT).show()
                }else{
                    //gagal
                    Toast.makeText( this@RegisterActivity,"Error"+respon.message,Toast.LENGTH_SHORT).show()
                }


            }

        })


    }
}
