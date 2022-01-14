package com.example.crud_mobile

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class UbahDokterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_dokter)

        val actionBar =supportActionBar
        if (intent.hasExtra("nama")) {
            actionBar?.title = intent.getStringExtra("nama")
        }

        val btnUbah = findViewById<Button>(R.id.btn_ubah)

        getIntentData()

        btnUbah.setOnClickListener {
            val dbKita = MyDBHelper(this)

            val idDokter = intent.getStringExtra("id")
            val namaDokter = findViewById<EditText>(R.id.txt_nama).text.toString()
            val spesialisDokter = findViewById<EditText>(R.id.txt_spesialis).text.toString()
            val jadwalDokter = findViewById<EditText>(R.id.txt_jadwal).text.toString()

            dbKita.ubahDokter(idDokter, namaDokter, spesialisDokter, jadwalDokter)
        }
        val  btnHapus = findViewById<Button>(R.id.btnHapus)
        btnHapus.setOnClickListener {
            dialogKonfirmasi()
        }
    }
    fun getIntentData() {
        if (
            intent.hasExtra("id") &&
            intent.hasExtra("nama") &&
            intent.hasExtra("spesialis") &&
            intent.hasExtra("jadwal")
        ){

            val iddokter         = intent.getStringExtra("id")
            val namadokter       = intent.getStringExtra("nama")
            val spesialisdokter   = intent.getStringExtra("spesialis")
            val jadwaldokter       = intent.getStringExtra("jadwal")

            val txtnama = findViewById<EditText>(R.id.txt_nama)
            val txtspesialis = findViewById<EditText>(R.id.txt_spesialis)
            val txtjadwal = findViewById<EditText>(R.id.txt_jadwal)

            txtnama.setText(namadokter)
            txtspesialis.setText(spesialisdokter)
            txtjadwal.setText(jadwaldokter)
        } else{
            Toast.makeText(this,"Tidak Ada Data !", Toast.LENGTH_SHORT).show()
        }
    }
    fun dialogKonfirmasi(){
        val idDokter  = intent.getStringExtra("id")
        val namaDokter = intent.getStringExtra("nama")

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Delete " + namaDokter + " ?")
        alertDialog.setMessage("Apakah anda yakin menghapus " + namaDokter + " ?")

        alertDialog.setPositiveButton("iya", DialogInterface.OnClickListener{dialog, which ->
            val dbKita = MyDBHelper(this)
            dbKita.hapusDokter(idDokter)
            startActivity(Intent(this, MainActivity::class.java))
        })
        alertDialog.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->  })
        alertDialog.create().show()
    }

}