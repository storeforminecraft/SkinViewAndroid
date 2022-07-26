package dev.storeforminecraft.skinviewandroid.skinview3d.rendermodel.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLSurfaceView
import dev.storeforminecraft.skinviewandroid.R
import dev.storeforminecraft.skinviewandroid.skinview3d.rendermodel.render.SkinView3DRenderer
import java.io.IOException
import java.io.InputStream

class SkinView3DSurfaceView(context: Context) : GLSurfaceView(context) {

    private val renderer: SkinView3DRenderer

    init {
        val `is`: InputStream = getResources()
            .openRawResource(R.raw.skin)
        val bitmap: Bitmap
        bitmap = try {
            BitmapFactory.decodeStream(`is`)
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
                // Ignore.
            }
        }

        setEGLContextClientVersion(3)
        renderer = SkinView3DRenderer(bitmap)
        setRenderer(renderer)
    }
}
