package com.ifrs.app

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {

    private lateinit var minhaImagem: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity) // seu XML do layout

        // pega a referência da ImageView
        minhaImagem = findViewById(R.id.minhaImagem)

        // exemplo de uso: alterar largura e altura via código
        setWidthPercent(0.5f)  // agora 50% do parent
        setHeightPercent(0.4f) // agora 40% do parent
    }

    // -------- GETTERS --------
    fun getIdView(): Int = minhaImagem.id
    fun getWidth(): Int = minhaImagem.layoutParams.width
    fun getHeight(): Int = minhaImagem.layoutParams.height
    fun getSrc(): Int = minhaImagem.drawable?.constantState?.hashCode() ?: 0
    fun getScaleType(): ImageView.ScaleType = minhaImagem.scaleType

    // -------- SETTERS --------
    fun setWidth(width: Int) {
        val params = minhaImagem.layoutParams
        params.width = width
        minhaImagem.layoutParams = params
    }

    fun setHeight(height: Int) {
        val params = minhaImagem.layoutParams
        params.height = height
        minhaImagem.layoutParams = params
    }

    fun setSrc(resourceId: Int) {
        minhaImagem.setImageResource(resourceId)
    }

    fun setScaleType(type: ImageView.ScaleType) {
        minhaImagem.scaleType = type
    }

    fun setWidthPercent(percent: Float) {
        val parent = minhaImagem.parent as ConstraintLayout
        val cs = ConstraintSet()
        cs.clone(parent)
        cs.constrainPercentWidth(minhaImagem.id, percent)
        cs.applyTo(parent)
    }

    fun setHeightPercent(percent: Float) {
        val parent = minhaImagem.parent as ConstraintLayout
        val cs = ConstraintSet()
        cs.clone(parent)
        cs.constrainPercentHeight(minhaImagem.id, percent)
        cs.applyTo(parent)
    }
}