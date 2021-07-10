package com.arvita.tokolistrik.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.arvita.tokolistrik.model.Alamat
import com.arvita.tokolistrik.model.Produk

@Dao
interface DaoAlamat {

    @Insert(onConflict = REPLACE)
    fun insert(data: Alamat)

    @Delete
    fun delete(data: Alamat)

    @Update
    fun update(data: Alamat): Int

    @Query("SELECT * from alamat ORDER BY id ASC")
    fun getAll(): List<Alamat>

    @Query("SELECT * FROM alamat WHERE id = :id LIMIT 1")
    fun getProduk(id: Int): Alamat

    @Query("SELECT * FROM alamat WHERE isSelected = :status LIMIT 1")
    fun getByStatus(status: Boolean): Alamat?

    @Query("DELETE FROM keranjang")
    fun deleteAll(): Int
}
