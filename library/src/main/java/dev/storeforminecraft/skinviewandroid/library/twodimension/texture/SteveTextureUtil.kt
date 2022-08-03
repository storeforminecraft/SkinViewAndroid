package dev.storeforminecraft.skinviewandroid.library.twodimension.texture

import android.graphics.Bitmap
import dev.storeforminecraft.skinviewandroid.library.datas.ModelSourceTextureType

object SteveTextureUtil {
    fun getSteveFrontTex(skin: Bitmap): SteveFrontTexture {
        val texType =
            if (skin.width == skin.height) ModelSourceTextureType.RATIO_1_1 else ModelSourceTextureType.RATIO_2_1
        val tex = if (texType == ModelSourceTextureType.RATIO_1_1) {
            SteveFrontTexture(
                texType = texType,
                head = Bitmap.createBitmap(skin, 8, 8, 8, 8),
                hat = Bitmap.createBitmap(skin, 40, 8, 8, 8),
                torso = Bitmap.createBitmap(skin, 20, 20, 8, 12),
                torso2nd = Bitmap.createBitmap(skin, 20, 36, 8, 12),
                leftArm = Bitmap.createBitmap(skin, 36, 52, 4, 12),
                leftArm2nd = Bitmap.createBitmap(skin, 52, 52, 4, 12),
                rightArm = Bitmap.createBitmap(skin, 44, 20, 4, 12),
                rightArm2nd = Bitmap.createBitmap(skin, 44, 36, 4, 12),
                leftLeg = Bitmap.createBitmap(skin, 20, 52, 4, 12),
                leftLeg2nd = Bitmap.createBitmap(skin, 4, 52, 4, 12),
                rightLeg = Bitmap.createBitmap(skin, 4, 20, 4, 12),
                rightLeg2nd = Bitmap.createBitmap(skin, 4, 36, 4, 12)
            )
        } else {
            SteveFrontTexture(
                texType = texType,
                head = Bitmap.createBitmap(skin, 8, 8, 8, 8),
                hat = Bitmap.createBitmap(skin, 40, 8, 8, 8),
                torso = Bitmap.createBitmap(skin, 20, 20, 8, 12),
                leftArm = Bitmap.createBitmap(skin, 44, 20, 4, 12),
                rightArm = Bitmap.createBitmap(skin, 44, 20, 4, 12),
                leftLeg = Bitmap.createBitmap(skin, 4, 20, 4, 12),
                rightLeg = Bitmap.createBitmap(skin, 4, 20, 4, 12)
            )
        }
        return tex
    }
}