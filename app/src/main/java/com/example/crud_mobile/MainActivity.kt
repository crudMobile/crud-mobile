package com.example.crud_mobile

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    val dokter_id: ArrayList<String>      = arrayListOf()
    val dokter_nama: ArrayList<String>   = arrayListOf()
    val dokter_spesialis: ArrayList<String> = arrayListOf()
    val dokter_jadwal: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTambah = findViewById<FloatingActionButton>(R.id.float_add)
        btnTambah.setOnClickListener {
            val intentKita = Intent(this, TambahDokterActivity::class.java)
            startActivity(intentKita)
        }
        simpanDataDiArray()
        val dokterAdapter = DokterAdapter(this,dokter_id,dokter_nama,dokter_spesialis,dokter_jadwal)
        val rv_dokter = findViewById<RecyclerView>(R.id.rv_dokter)
        rv_dokter.adapter = dokterAdapter
        rv_dokter.layoutManager = LinearLayoutManager(this)
        rv_dokter.itemAnimator = DefaultItemAnimator()
    }
    fun simpanDataDiArray(){
        val dbSaya            = MyDBHelper(this)
        val dataSaya: Cursor = dbSaya.BacaSemuaData()

        if(dataSaya.count == 0){
            Toast.makeText(this,"Data Tidak Ada!", Toast.LENGTH_SHORT).show()
        }
        else{
            while (dataSaya.moveToNext()){
                dokter_id.add(dataSaya.getString(0))
                dokter_nama.add(dataSaya.getString(1))
                dokter_spesialis.add(dataSaya.getString(2))
                dokter_jadwal.add(dataSaya.getString(3))
            }
        }
    }
}