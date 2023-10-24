package com.example.example

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
   private lateinit var answerTextView2:TextView
    private var a = 0
    private var b = 0
    private var v = ""

    private var chetv = 0
    private var chetn = 0
    private var tot=0
    private var vern = 0
    private var result = 0
    private var correctAnswers = 0
    private var incorrectAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        answerTextView2=findViewById(R.id.textView2)
        val ButCor:Button=findViewById(R.id.button2)
        val ButNoCor:Button=findViewById(R.id.button3)
         ButCor.setOnClickListener {
            checkAnswer(true)
        }
        ButNoCor.setOnClickListener {
            checkAnswer(false)
        }
        generateProblem()
    }

    fun butStart(view: View) {
        val textView: TextView = findViewById(R.id.textView)
      //  val textView1: TextView = findViewById(R.id.textView3)
       // val textView2: TextView = findViewById(R.id.textView2)
        textView.text = a.toString()+v+b.toString()

        // textView1.text = b.toString()
        // textView2.text = v

         correctAnswers=Calut(a, b, v)
        val isCorrect = (0..1).random() == 1
        if (isCorrect) {
            answerTextView2.text = correctAnswers.toString()
        } else {
            var wrongAnswer = correctAnswers
            while (wrongAnswer == correctAnswers) {
                wrongAnswer = (10..99).random()
            }
            answerTextView2.text = wrongAnswer.toString()
        }

    }




    private fun generateProblem() {
        a = (0..100).random()
        b = (0..100).random()
        val operators = arrayOf("*", "-", "+", "/")
        val ostat=a%b
        v = operators.random()
        if (v == "/" && ostat>0) {
            while (a % b == 0) {
                a = (10..99).random()
                b = (10..99).random()
            }
        }



    }

    private fun checkAnswer(isTrue:Boolean) {

        val textView: TextView = findViewById(R.id.textView)
       val selectedAnswer = answerTextView2.text.toString().toInt()
        val operands = textView.text.split(v)
        val oper1 = operands[0].toInt()
        val oper2 = operands[1].toInt()
        val correctAnswer = Calut(oper1, oper2, v)



        if (selectedAnswer == correctAnswer && isTrue || selectedAnswer != correctAnswer && !isTrue) {
            chetv++
          //  Toast.makeText(this, "ПРАВИЛЬНО", Toast.LENGTH_SHORT).show()
        } else {
            chetn++
            //Toast.makeText(this, "НЕ ПРАВИЛЬНО", Toast.LENGTH_SHORT).show()
        }
       /* if (result == correctAnswers) {

            chetv++
        }*/
        tot++
        checkResult()
        generateProblem()
    }

    private fun checkResult() {
        val textView11: TextView = findViewById(R.id.textView11)
        val textView10: TextView = findViewById(R.id.textView10)
        val textView9: TextView = findViewById(R.id.textView9)
        val textView6: TextView = findViewById(R.id.textView6)
        val correctPercentage = if (tot > 0) (chetv.toFloat() / tot) * 100 else 0.0
        val otvet = String.format("%.2f%%", correctPercentage)
        textView10.text=chetv.toString()
        textView9.text=(chetn).toString()
        textView6.text=tot.toString()
        textView11.text = otvet
    }
    fun Calut(oper1: Int, oper2: Int, znak: String): Int {
        return when (znak) {
            "*" -> oper1 * oper2
            "-" -> oper1 - oper2
            "+" -> oper1 + oper2
            "/" -> oper1 / oper2
            else -> 0
        }

    }
}