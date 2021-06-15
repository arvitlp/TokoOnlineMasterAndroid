package com.arvita.tokolistrik.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.arvita.tokolistrik.MainActivity

import com.arvita.tokolistrik.R
import com.arvita.tokolistrik.adapter.AdapterProduk
import com.arvita.tokolistrik.adapter.AdapterSlider
import com.arvita.tokolistrik.app.ApiConfig
import com.arvita.tokolistrik.model.Produk
import com.arvita.tokolistrik.model.ResponseModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    lateinit var vpSlider: ViewPager
    lateinit var rvProduk:RecyclerView
    lateinit var rvElektronik:RecyclerView
    lateinit var rvListrik:RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val view:View = inflater.inflate(R.layout.fragment_home, container, false)
        init(view)
        getProduk()


        return view
    }
    fun displayProduk(){
        val arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.slider)
        arrSlider.add(R.drawable.slider1)

        val adapterSlider=AdapterSlider(arrSlider,activity)
        vpSlider.adapter = adapterSlider

        val layoutManager= LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2= LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager3= LinearLayoutManager(activity)
        layoutManager3.orientation = LinearLayoutManager.HORIZONTAL

       rvProduk.adapter =AdapterProduk(requireActivity(),listProduk)
        rvProduk.layoutManager=layoutManager

        rvElektronik.adapter =AdapterProduk(requireActivity(),listProduk)
        rvElektronik.layoutManager=layoutManager2

        rvListrik.adapter =AdapterProduk( requireActivity(),listProduk)
        rvListrik.layoutManager=layoutManager3
    }

    private var listProduk:ArrayList<Produk> = ArrayList()
    fun getProduk(){
        ApiConfig.instanceRetrofit.getProduk().enqueue(object:
            Callback<ResponseModel> {
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val res = response.body()!!
                if(res.success == 1){
                    listProduk = res.produks
                    displayProduk()

                }
            }

        })
    }
    fun init(view: View){
        vpSlider = view.findViewById(R.id.vp_slider)
        rvProduk = view.findViewById(R.id.rv_produk)
        rvElektronik= view.findViewById(R.id.rv_elektronik)
        rvListrik= view.findViewById(R.id.rv_listrik)

    }
    //val arrProduk:ArrayList<Produk>get(){
        //val arr = ArrayList<Produk>()

//        val p1= Produk()
//        p1.nama ="Lampu Philip 16 W"
//        p1.harga ="Rp.34.000"
//        p1.gambar = R.drawable.l1

//        val p2= Produk()
//        p2.nama ="Lampu Philip 16 W"
//        p2.harga ="Rp.34.000"
//        p2.gambar = R.drawable.lampu_17_watt

//        val p3= Produk()
//        p3.nama ="Lampu Philip 16 W"
//        p3.harga ="Rp.34.000"
//        p3.gambar = R.drawable.lampu_17_watt2

  //      arr.add(p1)
    //    arr.add(p2)
    //      arr.add(p3)
    //    return arr
    }
    //val arrElektronik:ArrayList<Produk>get(){
//        val arr = ArrayList<Produk>()

//        val p1= Produk()
//        p1.nama ="Lampu Philip 16 W"
//        p1.harga ="Rp.34.000"
//        p1.gambar = R.drawable.l1

//        val p2= Produk()
//        p2.nama ="Lampu Philip 16 W"
//        p2.harga ="Rp.34.000"
//        p2.gambar = R.drawable.lampu_17_watt

//        val p3= Produk()
//        p3.nama ="Lampu Philip 16 W"
//        p3.harga ="Rp.34.000"
//        p3.gambar = R.drawable.lampu_17_watt2

  //      arr.add(p1)
  //      arr.add(p2)
  //      arr.add(p3)
  //    return arr
   // }
    //val arrListrik:ArrayList<Produk>get(){
        //val arr = ArrayList<Produk>()

       // val p1= Produk()
     //   p1.nama ="Lampu Philip 16 W"
       // p1.harga ="Rp.34.000"
     //   p1.gambar = R.drawable.l1

        //val p2= Produk()
     //   p2.nama ="Lampu Philip 16 W"
     //   p2.harga ="Rp.34.000"
     //   p2.gambar = R.drawable.lampu_17_watt

        //val p3= Produk()
     //   p3.nama ="Lampu Philip 16 W"
     //   p3.harga ="Rp.34.000"
     //   p3.gambar = R.drawable.lampu_17_watt2

     //   arr.add(p1)
     //   arr.add(p2)
     //   arr.add(p3)
       // return arr
   // }

//}
