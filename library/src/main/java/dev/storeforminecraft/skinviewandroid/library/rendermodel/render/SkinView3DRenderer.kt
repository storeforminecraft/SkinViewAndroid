package dev.storeforminecraft.skinviewandroid.library.rendermodel.render

import android.graphics.Bitmap
import android.opengl.GLES20
import android.opengl.GLES31
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import dev.storeforminecraft.skinviewandroid.library.rendermodel.model.Steve
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class SkinView3DRenderer : GLSurfaceView.Renderer {


    private val vPMatrix = FloatArray(16)
    private val projectionMatrix = FloatArray(16)
    private val viewMatrix = FloatArray(16)

    var angleY = 0f
    var angleX = 0f
    var bitmap: Bitmap? = null
    lateinit var steve: Steve

    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        // Set the background frame color
        GLES31.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        GLES31.glEnable(GLES31.GL_DEPTH_TEST)


        GLES31.glEnable(GLES31.GL_BLEND)
        GLES31.glBlendFunc(GLES31.GL_SRC_ALPHA, GLES31.GL_ONE_MINUS_SRC_ALPHA)

        GLES31.glFrontFace(GLES31.GL_CCW)
        GLES31.glCullFace(GLES31.GL_BACK)
        GLES31.glEnable(GLES31.GL_CULL_FACE)

        bitmap?.also {
            steve = Steve(it)
        }
    }

    private val rotationMatrix = FloatArray(16)

    override fun onDrawFrame(unused: GL10) {
        GLES31.glClear(GLES31.GL_COLOR_BUFFER_BIT or GLES31.GL_DEPTH_BUFFER_BIT)

        // Redraw background color
        GLES31.glClear(GLES31.GL_COLOR_BUFFER_BIT)

        // Set the camera position (View matrix)
        Matrix.setLookAtM(
            viewMatrix, 0,
            0.0f, 0.0f, 7.0f,
            0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f
        )

        // Create a rotation and translation for the cube
        Matrix.setIdentityM(rotationMatrix, 0);

        //move the cube up/down and left/right
        Matrix.translateM(rotationMatrix, 0, 0f, 0f, 0f);

        //mangle is how fast, x,y,z which directions it rotates.
        Matrix.rotateM(rotationMatrix, 0, angleY, 0.0f, 1.0f, 0.0f);

        Matrix.rotateM(rotationMatrix, 0, angleX, 1.0f, 0.0f, 0.0f);

        // combine the model with the view matrix
        Matrix.multiplyMM(vPMatrix, 0, viewMatrix, 0, rotationMatrix, 0);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, vPMatrix, 0)

        // Draw shape
        steve.draw(vPMatrix)

//        angleY += 0.3f
    }

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        GLES31.glViewport(0, 0, width, height)

        val ratio: Float = width.toFloat() / height.toFloat()
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 2f, 10f)
    }

    companion object {
        fun loadShader(type: Int, shaderCode: String): Int {
            return GLES31.glCreateShader(type).also { shader ->
                GLES31.glShaderSource(shader, shaderCode)
                GLES31.glCompileShader(shader)
            }
        }
    }


}