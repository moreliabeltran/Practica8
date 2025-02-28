package mx.edu.itesca.practica8

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detalle_pelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_pelicula)

        val iv_pelicula_imagen: ImageView = findViewById(R.id.iv_pelicula_imagen)
        val tv_nombre_pelicula: TextView = findViewById(R.id.tv_nombre_pelicula)
        val tv_pelicula_desc: TextView = findViewById(R.id.tv_pelicula_desc)
        val seatsAvailable: TextView = findViewById(R.id.seatLeft)
        val button: Button = findViewById(R.id.buyTickets)
        var seats = -1
        var id = -1;

        val bundle = intent.extras
        if (bundle != null) {
            iv_pelicula_imagen.setImageResource(bundle.getInt("header"))
            tv_nombre_pelicula.setText(bundle.getString("titulo"))
            tv_pelicula_desc.setText(bundle.getString("sinopsis"))
            seats = bundle.getInt("numberSeats")
            id = bundle.getInt("id")
            seatsAvailable.setText(seats.toString() + " seats available")
        }
        if (seats == 0) {
            button.isActivated = false
        } else {
            button.isActivated = true
            button.setOnClickListener {
                var intent: Intent = Intent(this, SeatSelection::class.java)
                intent.putExtra("name", tv_nombre_pelicula.text.toString())
                intent.putExtra("id", id)
                startActivityForResult(intent, 200)
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val seatsAvailable: TextView = findViewById(R.id.seatLeft)

        if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            if (data!=null){
                val bundle=data.extras
                if (bundle!=null){
                    val asiento=bundle.getInt("seat")
                    val posMovie=bundle.getInt("id")
                    val seats=bundle.getInt("seats")
                    seatsAvailable.text = seats.toString()+" seats available"

                    val intento = Intent()
                    intento.putExtra("seat", asiento)
                    intento.putExtra("posMovie", posMovie)
                    setResult(Activity.RESULT_OK, intento)
                    finish()
                }
            }
        }
    }

}