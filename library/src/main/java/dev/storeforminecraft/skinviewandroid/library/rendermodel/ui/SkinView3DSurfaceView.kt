package dev.storeforminecraft.skinviewandroid.library.rendermodel.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLSurfaceView
import android.util.Log
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintSet
import dev.storeforminecraft.skinviewandroid.library.R
import dev.storeforminecraft.skinviewandroid.library.rendermodel.render.SkinView3DRenderer
import java.io.IOException
import java.io.InputStream

class SkinView3DSurfaceView(context: Context) : GLSurfaceView(context) {

    private val renderer: SkinView3DRenderer

    init {
        val `is`: InputStream = getResources()
            .openRawResource(R.raw.creep)
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

    private val TOUCH_SCALE_FACTOR: Float = 100.0f / 320f


    private var previousX: Float = 0f
    private var previousY: Float = 0f

    override fun onTouchEvent(e: MotionEvent): Boolean {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        val x: Float = e.x
        val y: Float = e.y

        when (e.action) {
            MotionEvent.ACTION_MOVE -> {

                var dx: Float = x - previousX
                var dy: Float = y - previousY

                // reverse direction of rotation above the mid-line
                if (y > height / 2) {
                    dx *= -1
                }

                // reverse direction of rotation to left of the mid-line
                if (x < width / 2) {
                    dy *= -1
                }

                renderer.angleY -= (dx) * TOUCH_SCALE_FACTOR
                renderer.angleX += (dy) * TOUCH_SCALE_FACTOR

                requestRender()
            }
        }

        previousX = x
        previousY = y
        return true
    }


}
