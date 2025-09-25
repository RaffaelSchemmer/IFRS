package com.ifrs.app

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
class MainActivity : AppCompatActivity()
{
    // Método é chamado toda vez que o app abrir
    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity) // seu XML do layout

        val imgGif: ImageView = findViewById(R.id.image)
        Glide.with(this)
            .asGif()
            .load(R.drawable.weather) // ou uma URL: "https://..."
            .into(imgGif)

    }

}