package dev.storeforminecraft.skinviewandroid.library.rendermodel.ui

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLSurfaceView
import android.os.Bundle
import dev.storeforminecraft.skinviewandroid.library.R
import java.io.IOException
import java.io.InputStream

class SkinView3DActivity : Activity() {

    private lateinit var gLView: SkinView3DSurfaceView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gLView = SkinView3DSurfaceView(this)

        val `is`: InputStream = resources
            .openRawResource(R.raw.creep)
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