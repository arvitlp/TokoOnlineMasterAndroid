package com.arvita.tokolistrik.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.arvita.tokolistrik.MainActivity

import com.arvita.tokolistrik.R
import com.arvita.tokolistrik.activity.LoginActivity
import com.arvita.tokolistrik.helper.SharedPref

/**
 * A simple [Fragment] subclass.
 */
class AkunFragment : Fragment() {
    lateinit var s:SharedPref
    lateinit var  btn_logout:TextView
    lateinit var  tvNama:TextView
    lateinit var  tvEmail:TextView
    lateinit var  tvPhone:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_akun, container, false)
        btn_logout = view.findViewById(R.id.btn_logout)
        init(view)


        s = SharedPref(activity!!)
        btn_logout.setOnClickListener{
             s.setStatusLogin(false)
         }
        setData()
        return view
    }
    fun setData(){
        if(s.getUser()== null){
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            return
        }

        val user = s.getUser()!!

        tvNama.text =user.name
        tvEmail.text =user.email
        tvPhone.text =user.phone
    }
    private fun init(view: View) {
        btn_logout = view.findViewById(R.id.btn_logout)
        tvNama = view.findViewById(R.id.tv_nama)
        tvEmail = view.findViewById(R.id.tv_email)
        tvPhone = view.findViewById(R.id.tv_phone)

    }

}
