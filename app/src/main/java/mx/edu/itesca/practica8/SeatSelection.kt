package mx.edu.itesca.practica8

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SeatSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_selection)
        val title: TextView = findViewById(R.id.titleSeats)

        var posMovie = -1
        var seats = 0
        val bundle = intent.extras
        if (bundle != null) {
            title.setText(bundle.getString("name"))
            posMovie = bundle.getInt("id")
            seats = bundle.getInt("seats")
        }
        val row1: RadioGroup = findViewById(R.id.row1)
        val row2: RadioGroup = findViewById(R.id.row2)
        val row3: RadioGroup = findViewById(R.id.row3)
        val row4: RadioGroup = findViewById(R.id.row4)

        val confirm: Button = findViewById(R.id.confirm)
        confirm.setOnClickListener {
            Toast.makeText(this, "Seats reserved successfully!", Toast.LENGTH_LONG).show()
            val fila1 = row1.checkedRadioButtonId
            val fila2 = row2.checkedRadioButtonId
            val fila3 = row3.checkedRadioButtonId
            val fila4 = row4.checkedRadioButtonId
            var asientoSeleccionado = -1;
            if (fila1 > -1) {
                asientoSeleccionado = fila1;

                } else if (fila2 > -1) {
                asientoSeleccionado = fila2;
                } else if (fila3 > -1) {
                asientoSeleccionado = fila3;
                } else if (fila4 > -1) {
                asientoSeleccionado = fila4;
               }
            if (asientoSeleccionado != -1) {
                val intento = Intent()
                intento.putExtra("seat", asientoSeleccionado)
                intento.putExtra("seats", (seats - 1))
                intento.putExtra("id", posMovie)
                setResult(Activity.RESULT_OK, intento)
                finish()
            }
        }
        row1.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row2.clearCheck()
                row3.clearCheck()
                row4.clearCheck()
                row1.check(checkedId)
            }
        }
        row2.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row1.clearCheck()
                row3.clearCheck()
                row4.clearCheck()
                row2.check(checkedId)
            }
        }
        row3.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row2.clearCheck()
                row1.clearCheck()
                row4.clearCheck()
                row3.check(checkedId)
            }
        }
        row4.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row2.clearCheck()
                row3.clearCheck()
                row1.clearCheck()
                row4.check(checkedId)
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}