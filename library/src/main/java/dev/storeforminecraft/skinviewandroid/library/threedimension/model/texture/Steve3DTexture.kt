package dev.storeforminecraft.skinviewandroid.library.threedimension.model.texture

import dev.storeforminecraft.skinviewandroid.library.datas.ModelSourceTextureType

object Steve3DTexture {
    fun getSteveTexture(type: ModelSourceTextureType): FloatArray {
        if (type == ModelSourceTextureType.RATIO_2_1) {
            return getHeadTex(type) +
                    getTorsoTex(type, 16f, 16f) +
                    getLegArmTex(type, 40f, 16f) +
                    getLegArmTex(type, 40f, 16f) +
                    getLegArmTex(type, 0f, 16f) +
                    getLegArmTex(type, 0f, 16f) +
                    getHeadTex(type, 32f, 0f)
        } else if (type == ModelSourceTextureType.RATIO_1_1) {
            return getHeadTex(type) +
                    getTorsoTex(type, 16f, 16f) +
                    getLegArmTex(type, 32f, 48f) +
                    getLegArmTex(type, 40f, 16f) +
                    getLegArmTex(type, 16f, 48f) +
                    getLegArmTex(type, 0f, 16f) +
                    getHeadTex(type, 32f, 0f) +
                    getTorsoTex(type, 16f, 32f) +
                    getLegArmTex(type, 48f, 48f) +
                    getLegArmTex(type, 40f, 32f) +
                    getLegArmTex(type, 0f, 48f) +
                    getLegArmTex(type, 0f, 32f)
        }
        return floatArrayOf()
    }

    fun getHeadTex(type: ModelSourceTextureType, offsetU: Float = 0f, offsetV: Float = 0f): FloatArray {
        return floatArrayOf(
            // back
            32f, 8f, 32f, 16f, 24f, 16f, 24f, 8f,
            // front
            8f, 8f, 8f, 16f, 16f, 16f, 16f, 8f,
            // left
            0f, 8f, 0f, 16f, 8f, 16f, 8f, 8f,
            // right
            16f, 8f, 16f, 16f, 24f, 16f, 24f, 8f,
            // top
            8f, 0f, 8f, 8f, 16f, 8f, 16f, 0f,
            // bottom
            24f, 0f, 24f, 8f, 16f, 8f, 16f, 0f
        ).mapIndexed { index, fl ->
            if (index % 2 == 0) {
                fl + offsetU
            } else {
                fl + offsetV
            }
        }.mapIndexed { index, fl ->
            if (index % 2 != 0 && type == ModelSourceTextureType.RATIO_2_1) {
                fl / 32f
            } else {
                fl / 64f
            }
        }.toFloatArray()
    }

    fun getLegArmTex(
        type: ModelSourceTextureType,
        offsetU: Float = 0f,
        offsetV: Float = 0f,
    ): FloatArray {
        val tex = floatArrayOf(
            // back
            12f, 4f, 12f, 16f, 16f, 16f, 16f, 4f,
            // front
            4f, 4f, 4f, 16f, 8f, 16f, 8f, 4f,
            // left
            0f, 4f, 0f, 16f, 4f, 16f, 4f, 4f,
            // right
            8f, 4f, 8f, 16f, 12f, 16f, 12f, 4f,
            // top
            4f, 0f, 4f, 4f, 8f, 4f, 8f, 0f,
            // bottom
            12f, 0f, 12f, 4f, 8f, 4f, 8f, 0f,
        ).mapIndexed { index, fl ->
            if (index % 2 == 0) {
                fl + offsetU
            } else {
                fl + offsetV
            }
        }.mapIndexed { index, fl ->
            if (index % 2 != 0 && type == ModelSourceTextureType.RATIO_2_1) {
                fl / 32f
            } else {
                fl / 64f
            }
        }

        return tex.toFloatArray()
    }


    fun getTorsoTex(type: ModelSourceTextureType, offsetU: Float = 0f, offsetV: Float = 0f): FloatArray {
        return floatArrayOf(
            // back
            24f, 4f, 24f, 16f, 16f, 16f, 16f, 4f,
            // front
            4f, 4f, 4f, 16f, 12f, 16f, 12f, 4f,
            // left
            0f, 4f, 0f, 16f, 4f, 16f, 4f, 4f,
            // right
            12f, 4f, 12f, 16f, 16f, 16f, 16f, 4f,
            // top
            4f, 0f, 4f, 4f, 12f, 4f, 12f, 0f,
            // bottom
            20f, 0f, 20f, 4f, 12f, 4f, 12f, 0f
        ).mapIndexed { index, fl ->
            if (index % 2 == 0) {
                fl + offsetU
            } else {
                fl + offsetV
            }
        }.mapIndexed { index, fl ->
            if (index % 2 != 0 && type == ModelSourceTextureType.RATIO_2_1) {
                fl / 32f
            } else {
                fl / 64f
            }
        }.toFloatArray()
    }
}