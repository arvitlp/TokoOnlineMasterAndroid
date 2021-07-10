package com.arvita.tokolistrik.app

import com.arvita.tokolistrik.model.Chekout
import com.arvita.tokolistrik.model.ResponseModel
import com.arvita.tokolistrik.model.rajaongkir.ResponOngkir
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") notelp: String,
        @Field("password") password: String

    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseModel>


    @POST("chekout")
    fun chekout(
        @Body data: Chekout
    ): Call<ResponseModel>


    @GET("produk")
    fun getProduk(): Call<ResponseModel>

    @GET("province")
    fun getProvinsi(
        @Header("key") key: String
    ): Call<ResponseModel>

    @GET("city")
    fun getKota(
        @Header("key") key: String,
        @Query("province")id:String
    ): Call<ResponseModel>

    @GET("kecamatan")
    fun getKecamatan(
        @Query("id_kota")id:Int
    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("cost")
    fun ongkir(
        @Header("key") key: String,
        @Field("origin") origin: String,
        @Field("destination") destination: String,
        @Field("weight") weight: Int,
    @Field("courier") courier: String
    ): Call<ResponOngkir>

    @GET("chekout/user/{id}")
    fun getRiwayat(
        @Path("id")id: Int
    ): Call<ResponseModel>
}
