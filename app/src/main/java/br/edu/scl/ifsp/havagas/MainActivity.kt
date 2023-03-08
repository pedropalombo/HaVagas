package br.edu.scl.ifsp.havagas

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.havagas.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val activityMainBinding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // - | After View Binding Inflation | -
        //activityMainBinding = ActivityMainBinding.inflate(layoutInflater) |-> using lateinit (w/ lazy doesn't need it)
        setContentView(activityMainBinding.root)


        with(activityMainBinding) {

            telefoneCelularCb.setOnClickListener {
                if(telefoneCelularCb.isChecked) {
                    celTelefoneEt.visibility = View.VISIBLE

                } else {
                    celTelefoneEt.visibility = View.GONE
                    celTelefoneEt.text.clear() // also nomeConjugeEt.setText("")
                }
            }

            val editFields : Array<EditText> = arrayOf(nomeEt, emailEt, dataNascEt, mainTelefoneEt, vagasEt)

            //"Salvar" button
            salvarBt.setOnClickListener {
                val fieldsOK = validate(editFields)

                val fieldsMissing = getMissingFields(editFields)

                if(fieldsOK) {
                    Toast.makeText(this@MainActivity, "Todos os campos foram preenchidos com sucesso!", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this@MainActivity, "Os campos a seguir estão faltando: "+fieldsMissing.toString(), Toast.LENGTH_SHORT).show()

                }
            }

            limparBt.setOnClickListener {
                emptyFields(editFields)
                Toast.makeText(this@MainActivity, "Todos os campos foram limpados com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //checks if fields are empty
    private fun validate(fields: Array<EditText>): Boolean {
        for (i in fields.indices) {
            val currentField = fields[i]
            if (currentField.text.toString().length <= 0) {
                return false
            }
        }
        return true
    }

    //returns which fields are empty
    private fun getMissingFields(fields: Array<EditText>): MutableList<String> {
        val missingArray : MutableList<String> = mutableListOf()

        for (i in fields.indices) {
            val currentField = fields[i]
            if (currentField.text.toString().length <= 0) {
                missingArray.add(currentField.hint.toString())
            }
        }

        return missingArray

    }

    private fun emptyFields(fields: Array<EditText>) {
        for (i in fields.indices) {
            fields[i].setText("")
        }
    }
}