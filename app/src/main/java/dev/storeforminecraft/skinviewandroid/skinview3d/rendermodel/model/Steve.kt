package dev.storeforminecraft.skinviewandroid.skinview3d.rendermodel.model

import android.graphics.Bitmap
import android.opengl.GLES20
import android.opengl.GLES31
import android.opengl.GLUtils
import dev.storeforminecraft.skinviewandroid.libs.BufferUtil
import dev.storeforminecraft.skinviewandroid.skinview3d.rendermodel.render.SkinView3DRenderer.Companion.loadShader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

class Steve(val bitmap: Bitmap) {

    val steve = SteveCoords.getSteve()

    val headCoords = steve.first

    val headIndicies = steve.second

    val textureCoords = SteveCoords.getSteveUV()

    private val vertexShaderCode =
    // This matrix member variable provides a hook to manipulate
        // the coordinates of the objects that use this vertex shader
        "uniform mat4 uMVPMatrix;" +
                "attribute vec4 vPosition;" +
                "attribute vec2 a_texCoord;" +
                "varying vec2 v_texCoord;" +
                "void main() {" +
                "  v_texCoord = a_texCoord;" +
                // the matrix must be included as a modifier of gl_Position
                // Note that the uMVPMatrix factor *must be first* in order
                // for the matrix multiplication product to be correct.
                "  gl_Position = uMVPMatrix * vPosition;" +
                "}"

    private val fragmentShaderCode =
        "precision mediump float;" +
                "varying vec2 v_texCoord;" +
                "uniform sampler2D s_texture;" +
                "uniform vec4 vColor;" +
                "void main() {" +
//                "  gl_FragColor = vColor;" +
                "  gl_FragColor = texture2D(s_texture, v_texCoord);" +
                "}"

    private val textureBuffer: FloatBuffer = BufferUtil.createFloatBuffer(textureCoords)

    private val vertexBuffer: FloatBuffer = BufferUtil.createFloatBuffer(headCoords)

    private val drawListBuffer: ShortBuffer = BufferUtil.createShortBuffer(headIndicies)

    val color = floatArrayOf(0.63671875f, 0.76953125f, 0.22265625f, 1.0f)

    private var program: Int

    init {
        val vertexShader: Int = loadShader(GLES31.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader: Int = loadShader(GLES31.GL_FRAGMENT_SHADER, fragmentShaderCode)

        // create empty OpenGL ES Program
        program = GLES31.glCreateProgram().also {

            // add the vertex shader to program
            GLES31.glAttachShader(it, vertexShader)

            // add the fragment shader to program
            GLES31.glAttachShader(it, fragmentShader)

            // creates OpenGL ES program executables
            GLES31.glLinkProgram(it)
        }

        loadTexture()
    }


    private fun loadTexture() {
        val textures = IntArray(1)

        GLES31.glGenTextures(1, textures, 0)
        GLES31.glActiveTexture(GLES31.GL_TEXTURE0)
        GLES31.glBindTexture(GLES31.GL_TEXTURE_2D, textures[0])

        GLES31.glTexParameteri(
            GLES20.GL_TEXTURE_2D,
            GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR
        )
        GLES31.glTexParameteri(
            GLES20.GL_TEXTURE_2D,
            GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST
        )
        GLES31.glTexParameteri(
            GLES20.GL_TEXTURE_2D,
            GLES20.GL_TEXTURE_WRAP_S,
            GLES20.GL_CLAMP_TO_EDGE
        )
        GLES31.glTexParameteri(
            GLES20.GL_TEXTURE_2D,
            GLES20.GL_TEXTURE_WRAP_T,
            GLES20.GL_CLAMP_TO_EDGE
        )

        GLUtils.texImage2D(GLES31.GL_TEXTURE_2D, 0, bitmap, 0)
    }

    fun draw(mvpMatrix: FloatArray) {
        // Add program to OpenGL ES environment
        GLES31.glUseProgram(program)

        val positionHandle = GLES31.glGetAttribLocation(program, "vPosition")
        GLES31.glEnableVertexAttribArray(positionHandle)
        GLES31.glVertexAttribPointer(positionHandle, 3, GLES31.GL_FLOAT, false, 3 * 4, vertexBuffer)

        val vPMatrixHandle = GLES31.glGetUniformLocation(program, "uMVPMatrix")
        GLES31.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0)

//        val colorHandle = GLES31.glGetUniformLocation(program, "vColor")
//        GLES31.glUniform4fv(colorHandle, 1, color, 0)

        val textureCoordHandle = GLES31.glGetAttribLocation(program, "a_texCoord")
        GLES31.glEnableVertexAttribArray(textureCoordHandle)
        GLES31.glVertexAttribPointer(textureCoordHandle, 2, GLES31.GL_FLOAT, false, 2 * 4, textureBuffer)

        GLES31.glGetUniformLocation(program, "s_texture").also { textureHandle ->
            GLES31.glUniform1i(textureHandle, 0)
        }

        GLES31.glDrawElements(
            GLES31.GL_TRIANGLES,
            headIndicies.size,
            GLES31.GL_UNSIGNED_SHORT,
            drawListBuffer
        )

        GLES31.glDisableVertexAttribArray(positionHandle)
        GLES31.glDisableVertexAttribArray(textureCoordHandle)
    }
}