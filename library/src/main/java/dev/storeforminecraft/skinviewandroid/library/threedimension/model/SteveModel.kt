package dev.storeforminecraft.skinviewandroid.library.threedimension.model

import android.graphics.Bitmap
import android.opengl.GLES20
import android.opengl.GLES31
import android.opengl.GLUtils
import dev.storeforminecraft.skinviewandroid.library.libs.BufferUtil
import dev.storeforminecraft.skinviewandroid.library.datas.ModelSourceTextureType
import dev.storeforminecraft.skinviewandroid.library.threedimension.model.texture.Steve3DTexture
import dev.storeforminecraft.skinviewandroid.library.threedimension.render.SkinView3DRenderer.Companion.loadShader
import java.nio.FloatBuffer
import java.nio.ShortBuffer

/**
 * Minecraft Character Model for OpenGl render
 */
class SteveModel(val bitmap: Bitmap) {
    private val vertexShaderCode =
    // This matrix member variable provides a hook to manipulate
        // the coordinates of the objects that use this vertex shader
        "uniform mat4 uMVPMatrix;" +
                "attribute vec4 vPosition;" +
                "attribute vec2 a_texCoord;" +
                "varying vec2 v_texCoord;" +
                "void main() {" +
                "  v_texCoord = a_texCoord;" +
                "  gl_Position = uMVPMatrix * vPosition;" +
                "}"

    private val fragmentShaderCode =
        "precision mediump float;" +
                "varying vec2 v_texCoord;" +
                "uniform sampler2D s_texture;" +
                "uniform vec4 vColor;" +
                "void main() {" +
                "  gl_FragColor = texture2D(s_texture, v_texCoord);" +
                "}"

    private var program: Int

    private val steveModelType: ModelSourceTextureType
    private val steveTextureCoords: FloatArray
    private val steveModelCoords: FloatArray
    private val steveModelDrawOrder: ShortArray

    private val textureBuffer: FloatBuffer

    private val vertexBuffer: FloatBuffer

    private val drawListBuffer: ShortBuffer

    init {
        val vertexShader: Int = loadShader(GLES31.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader: Int = loadShader(GLES31.GL_FRAGMENT_SHADER, fragmentShaderCode)

        steveModelType =
            if (bitmap.width == bitmap.height) ModelSourceTextureType.RATIO_1_1 else ModelSourceTextureType.RATIO_2_1

        val steve = SteveCoords.getSteve(steveModelType)
        steveModelCoords = steve.first
        steveModelDrawOrder = steve.second
        steveTextureCoords = Steve3DTexture.getSteveTexture(steveModelType)

        vertexBuffer = BufferUtil.createFloatBuffer(steveModelCoords)
        drawListBuffer = BufferUtil.createShortBuffer(steveModelDrawOrder)
        textureBuffer = BufferUtil.createFloatBuffer(steveTextureCoords)

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

        val textureCoordHandle = GLES31.glGetAttribLocation(program, "a_texCoord")
        GLES31.glEnableVertexAttribArray(textureCoordHandle)
        GLES31.glVertexAttribPointer(
            textureCoordHandle,
            2,
            GLES31.GL_FLOAT,
            false,
            2 * 4,
            textureBuffer
        )

        GLES31.glGetUniformLocation(program, "s_texture").also { textureHandle ->
            GLES31.glUniform1i(textureHandle, 0)
        }

        GLES31.glDrawElements(
            GLES31.GL_TRIANGLES,
            steveModelDrawOrder.size,
            GLES31.GL_UNSIGNED_SHORT,
            drawListBuffer
        )

        GLES31.glDisableVertexAttribArray(positionHandle)
        GLES31.glDisableVertexAttribArray(textureCoordHandle)
    }
}