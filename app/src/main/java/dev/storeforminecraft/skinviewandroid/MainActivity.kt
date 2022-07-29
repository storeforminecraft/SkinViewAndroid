package dev.storeforminecraft.skinviewandroid

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.storeforminecraft.skinviewandroid.library.rendermodel.ui.SkinView3DSurfaceView
import java.io.IOException
import java.io.InputStream

class  MainActivity : AppCompatActivity() {

    private lateinit var gLView: SkinView3DSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gLView = SkinView3DSurfaceView(this)

        val `is`: InputStream = resources
            .openRawResource(R.raw.skin_steve)
        val bitmap: Bitmap = try {
            BitmapFactory.decodeStream(`is`)
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
            }
        }

        gLView.render(bitmap)

        setContentView(gLView)
    }
}