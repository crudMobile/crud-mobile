package com.example.crud_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class TambahDokterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_dokter)

        val txtnama = findViewById<EditText>(R.id.txtnama)
        val txtspesialis = findViewById<EditText>(R.id.txtSpesialis)
        val txtjadwale = findViewById<EditText>(R.id.txtJadwal)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        btnSimpan.setOnClickListener {
            val dbSaya = MyDBHelper(this)
            dbSaya.tambahDokter(
                txtnama.text.toString().trim(),
                txtspesialis.text.toString().trim(),
                txtjadwale.text.toString().trim()
            )
        }

    }
}