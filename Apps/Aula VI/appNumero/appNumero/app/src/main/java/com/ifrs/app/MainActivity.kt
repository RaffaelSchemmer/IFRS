package com.ifrs.app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import androidx.appcompat.app.AppCompatActivity

// MainActivity é a Activity principal do app
class MainActivity : AppCompatActivity() {

    // Declaração das views que serão usadas
    private lateinit var editTextNumber: EditText       // Campo de entrada do palpite
    private lateinit var buttonPalpite: Button         // Botão para enviar o palpite
    private lateinit var progressBar: ProgressBar      // Spinner de loading
    private var numeroAleatorio: Int = 0              // Número aleatório a ser adivinhado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)       // Define o layout da Activity

        // Inicializa as views usando findViewById
        editTextNumber = findViewById(R.id.textViewCharada)
        buttonPalpite = findViewById(R.id.buttonPalpite)
        progressBar = findViewById(R.id.loadingSpinner)

        // Gera um número aleatório de 0 a 9 no início do app
        numeroAleatorio = Random.nextInt(0, 10)

        // Define o comportamento ao clicar no botão de palpite
        buttonPalpite.setOnClickListener {

            val palpiteStr = editTextNumber.text.toString() // Pega o texto digitado

            if (palpiteStr.isNotEmpty()) {                  // Só prossegue se não estiver vazio

                buttonPalpite.isEnabled = false           // Desabilita o botão enquanto processa
                val palpite = palpiteStr.toInt()          // Converte o texto para número inteiro

                // Mostra o spinner de loading
                progressBar.visibility = ProgressBar.VISIBLE

                // Cria um delay de 2 segundos simulando pesquisa/loading
                Handler(Looper.getMainLooper()).postDelayed({

                    // Esconde o spinner após 2 segundos
                    progressBar.visibility = ProgressBar.GONE

                    // Calcula a diferença absoluta entre o palpite e o número aleatório
                    val diff = kotlin.math.abs(palpite - numeroAleatorio)

                    // Define a mensagem a ser exibida dependendo da proximidade

                    val mensagem = when {
                        diff == 0 -> "Acertou!"              // Acertou o número
                        diff >= 5 -> "Está frio!"            // Muito distante
                        diff in 3..4 -> "Está morno!"        // Um pouco distante
                        else -> "Está quente!"               // Muito próximo
                    }

                    // Mostra a mensagem em um Toast
                    Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()

                    // Se acertou, gera um novo número aleatório e reseta o EditText
                    if (diff == 0) {
                        numeroAleatorio = Random.nextInt(0, 10)
                        editTextNumber.setText("0")        // Reseta o campo para 0
                    }

                }, 2000) // 2000ms = 2 segundos de delay
                lifecycleScope.launch {
                    delay(5000) // espera o Toast desaparecer
                    buttonPalpite.isEnabled = true        // Reabilita o botão após o delay
                }
            }
        }
    }
}
