package dev.storeforminecraft.skinviewandroid.skinview3d.rendermodel.model

import java.util.*

object SteveTextures {
    fun getHeadTex(offsetU: Float = 0f, offsetV: Float = 0f): FloatArray {
        return floatArrayOf(
            // back
            24f, 8f, 32f, 8f, 24f, 16f, 32f, 16f,
            // front
            8f, 8f, 16f, 8f, 8f, 16f, 16f, 16f,
            // left
            0f, 8f, 8f, 8f, 0f, 16f, 8f, 16f,
            // right
            16f, 8f, 24f, 8f, 16f, 16f, 24f, 16f,
            // top
            8f, 0f, 16f, 0f, 8f, 8f, 16f, 8f,
            // bottom
            16f, 0f, 24f, 0f, 16f, 8f, 24f, 8f
        ).mapIndexed { index, fl ->
            if (index % 2 == 0) {
                fl + offsetU
            } else {
                fl + offsetV
            }
        }.map { it / 64f }.toFloatArray()
    }

    fun getLegArmTex(offsetU: Float = 0f, offsetV: Float = 0f, right: Boolean): FloatArray {
        val tex = floatArrayOf(
            // back
            12f, 4f, 16f, 4f, 12f, 16f, 16f, 16f,
            // front
            4f, 4f, 8f, 4f, 4f, 16f, 8f, 16f,
            // outside
            0f, 4f, 4f, 4f, 0f, 16f, 4f, 16f,
            // inside
            8f, 4f, 12f, 4f, 8f, 16f, 12f, 16f,
            // top
            4f, 0f, 8f, 0f, 4f, 4f, 8f, 4f,
            // bottom
            8f, 0f, 12f, 0f, 8f, 4f, 12f, 4f,
        ).mapIndexed { index, fl ->
            if (index % 2 == 0) {
                fl + offsetU
            } else {
                fl + offsetV
            }
        }.map { it / 64f }

        if (right) {
            for (idx in 16..23)
                Collections.swap(tex, idx, idx + 8)
        }

        return tex.toFloatArray()
    }

    fun getTorsoTex(offsetU: Float = 0f, offsetV: Float = 0f): FloatArray {
        return floatArrayOf(
            // back
            16f, 4f, 24f, 4f, 16f, 16f, 24f, 16f,
            // front
            4f, 4f, 12f, 4f, 12f, 16f, 12f, 16f,
            // left
            0f, 4f, 4f, 4f, 0f, 16f, 4f, 16f,
            // right
            12f, 4f, 16f, 4f, 12f, 16f, 16f, 16f,
            // top
            4f, 0f, 12f, 0f, 4f, 4f, 12f, 4f,
            // bottom
            12f, 0f, 20f, 0f, 12f, 4f, 20f, 4f
        ).mapIndexed { index, fl ->
            if (index % 2 == 0) {
                fl + offsetU
            } else {
                fl + offsetV
            }
        }.map { it / 64f }.toFloatArray()
    }
}