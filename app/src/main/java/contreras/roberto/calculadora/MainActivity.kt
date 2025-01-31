package contreras.roberto.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var n1: Int = 0
    var n2: Int? = null
    var op: Char? = null
    var numeros
    var result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        result: TextView = findViewById(R.id.result)
        val minus: Button = findViewById(R.id.minus)
        val plus: Button = findViewById(R.id.plus)
        val equals: Button = findViewById(R.id.equals)

        numeros = setOf(defBtn(R.id.b1,1),defBtn(R.id.b2,2),defBtn(R.id.b3,3),
                        defBtn(R.id.b4,4),defBtn(R.id.b5,5),defBtn(R.id.b6,6),
                        defBtn(R.id.b7,7),defBtn(R.id.b8,8),defBtn(R.id.b9,9))
        
        minus.setOnClickListener {
            op = '-'
            if (n2==null) n2 = 0
            actualizarPantalla()
        }
        plus.setOnClickListener {
            op = '+'
            if (n2==null) n2 = 0
            actualizarPantalla()
        }
        
        equals.setOnClickListener {
            if (op!=null) {
                var r = 0

                if (op=='+') r = n1+n2
                else if (op=='-') r = n1-n2 

                n1 = r
                op = null
                n2 = null
                actualizarPantalla()
            }
        }
        
    }

    fun defBtn(id: Int, n: Int): Button {
        val btn = findViewById(id)

        btn.setOnClickListener {
            if (op==null) {
                n1 = n1*10
                n1 = n1+n
            } else {
                if (n2==null) n2 = 0
                n2 = n2*10
                n2 = n2+n
            }
            actualizarPantalla()
        }

        return btn
    }

    fun actualizarPantalla() {
        var texto = ""
        if (op==null) texto = "$n1"
        else texto = "$n1 $op $n2"

        result.setText(texto)
    }
}
