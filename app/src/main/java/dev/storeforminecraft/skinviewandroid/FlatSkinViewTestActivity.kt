package dev.storeforminecraft.skinviewandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FlatSkinViewTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flat_skin_view_test)
        startActivity(Intent(this, MainActivity::class.java))
    }
}