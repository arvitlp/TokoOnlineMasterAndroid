package com.arvita.tokolistrik

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.arvita.tokolistrik.activity.LoginActivity
import com.arvita.tokolistrik.activity.MasukActivity
import com.arvita.tokolistrik.fragment.AkunFragment
import com.arvita.tokolistrik.fragment.HomeFragment
import com.arvita.tokolistrik.fragment.KeranjangFragment
import com.arvita.tokolistrik.helper.SharedPref
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
     private val fragmentHome: Fragment = HomeFragment()
     private var fragmentAkun:Fragment = AkunFragment()
     private val fragmentKeranjang:Fragment = KeranjangFragment()
     private val fm:FragmentManager = supportFragmentManager
     private var active: Fragment = fragmentHome

     private lateinit var menu: Menu
     private  lateinit var menuItem: MenuItem
     private  lateinit var bottomNavigationView: BottomNavigationView

    private var statusLogin = false
    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        s = SharedPref(this)

        setUpBottomNav()
    }
    fun setUpBottomNav(){
        fm.beginTransaction().add(R.id.container, fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container, fragmentAkun).hide(fragmentAkun).commit()
        fm.beginTransaction().add(R.id.container, fragmentKeranjang).hide(fragmentKeranjang)
            .commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navigation_home -> {
                    callFragment(0, fragmentHome)
                }
                R.id.navigation_keranjang -> {
                    callFragment(1, fragmentKeranjang)
                }
                R.id.navigation_akun -> {
                    if (s.getStatusLogin()){
                        callFragment(2, fragmentAkun)
                    }else{
                        startActivity(Intent(this,MasukActivity::class.java))
                    }

                }
            }
            false
        }
    }
    fun callFragment(int: Int,fragment: Fragment){
        menuItem = menu.getItem( int)
        menuItem.isChecked=true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active= fragment

    }
}
