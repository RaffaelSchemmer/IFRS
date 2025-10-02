package com.ifrs.app

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {

    private lateinit var minhaImagem: ImageView
    private lateinit var meuSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Referências
        minhaImagem = findViewById(R.id.minhaImagem)
        meuSwitch = findViewById(R.id.meuSwitch)

        // Listener do Switch para alternar cores
        meuSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // switch ligado
                minhaImagem.setColorFilter(Color.parseColor("#78F86A"))
            } else {
                // switch desligado
                minhaImagem.setColorFilter(Color.parseColor("#2596BE"))
            }
        }
    }

    // -------- GETTERS E SETTERS IMAGEVIEW --------
    fun getImageId(): Int = minhaImagem.id
    fun getImageWidth(): Int = minhaImagem.layoutParams.width
    fun getImageHeight(): Int = minhaImagem.layoutParams.height
    fun getImageSrc(): Int = minhaImagem.drawable.constantState.hashCode()
    fun getImageScaleType(): ImageView.ScaleType = minhaImagem.scaleType

    fun setImageWidth(width: Int) {
        val params = minhaImagem.layoutParams
        params.width = width
        minhaImagem.layoutParams = params
    }

    fun setImageHeight(height: Int) {
        val params = minhaImagem.layoutParams
        params.height = height
        minhaImagem.layoutParams = params
    }

    fun setImageSrc(resourceId: Int) {
        minhaImagem.setImageResource(resourceId)
    }

    fun setImageScaleType(type: ImageView.ScaleType) {
        minhaImagem.scaleType = type
    }

    fun setImageWidthPercent(percent: Float) {
        val parent = minhaImagem.parent as ConstraintLayout
        val cs = ConstraintSet()
        cs.clone(parent)
        cs.constrainPercentWidth(minhaImagem.id, percent)
        cs.applyTo(parent)
    }

    fun setImageHeightPercent(percent: Float) {
        val parent = minhaImagem.parent as ConstraintLayout
        val cs = ConstraintSet()
        cs.clone(parent)
        cs.constrainPercentHeight(minhaImagem.id, percent)
        cs.applyTo(parent)
    }

    // -------- GETTERS E SETTERS SWITCH --------
    fun getSwitchState(): Boolean = meuSwitch.isChecked

    fun setSwitchState(state: Boolean) {
        meuSwitch.isChecked = state
    }


    fun setSwitchScale(scaleX: Float, scaleY: Float) {
        meuSwitch.scaleX = scaleX
        meuSwitch.scaleY = scaleY
    }

    fun setSwitchText(text: String) {
        meuSwitch.text = text
    }

}
