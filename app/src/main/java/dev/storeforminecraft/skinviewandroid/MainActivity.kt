package dev.storeforminecraft.skinviewandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.storeforminecraft.skinviewandroid.library.rendermodel.ui.SkinView3DActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this@MainActivity, SkinView3DActivity::class.java))
    }
}