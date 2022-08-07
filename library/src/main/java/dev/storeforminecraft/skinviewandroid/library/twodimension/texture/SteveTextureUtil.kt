package dev.storeforminecraft.skinviewandroid.library.twodimension.texture

import android.graphics.Bitmap
import androidx.core.graphics.scale
import dev.storeforminecraft.skinviewandroid.library.datas.ModelSourceTextureType
import dev.storeforminecraft.skinviewandroid.library.libs.SkinUtil

object SteveTextureUtil {
    fun getSteveFrontTex(skin: Bitmap): SteveFrontTexture {
        val texType = SkinUtil.getTexType(skin)

        val scale =
            if (skin.width >= 64 && texType == ModelSourceTextureType.RATIO_1_1) skin.width / 64 else 1

        val tex = if (texType != ModelSourceTextureType.RATIO_2_1) {
            val slim = texType == ModelSourceTextureType.RATIO_1_1_SLIM
            SteveFrontTexture(
                skin,
                texType = texType,
                skinImageScale = scale,
                head = Bitmap.createBitmap(
                    skin, 8 * scale, 8 * scale,
                    8 * scale, 8 * scale
                ),
                hat = Bitmap.createBitmap(
                    skin, 40 * scale, 8 * scale,
                    8 * scale, 8 * scale
                ),
                torso = Bitmap.createBitmap(
                    skin, 20 * scale, 20 * scale,
                    8 * scale, 12 * scale
                ),
                torso2nd = Bitmap.createBitmap(
                    skin, 20 * scale, 36 * scale,
                    8 * scale, 12 * scale
                ),
                rightArm = Bitmap.createBitmap(
                    skin, 36 * scale, 52 * scale,
                    (if (slim) 3 else 4) * scale, 12 * scale
                ),
                rightArm2nd = Bitmap.createBitmap(
                    skin, 52 * scale, 52 * scale,
                    (if (slim) 3 else 4) * scale, 12 * scale
                ),
                leftArm = Bitmap.createBitmap(
                    skin, 44 * scale, 20 * scale,
                    (if (slim) 3 else 4) * scale, 12 * scale
                ),
                leftArm2nd = Bitmap.createBitmap(
                    skin, 44 * scale, 36 * scale,
                    (if (slim) 3 else 4) * scale, 12 * scale
                ),
                rightLeg = Bitmap.createBitmap(
                    skin, 20 * scale, 52 * scale,
                    4 * scale, 12 * scale
                ),
                rightLeg2nd = Bitmap.createBitmap(
                    skin, 4 * scale, 52 * scale,
                    4 * scale, 12 * scale
                ),
                leftLeg = Bitmap.createBitmap(
                    skin, 4 * scale, 20 * scale,
                    4 * scale, 12 * scale
                ),
                leftLeg2nd = Bitmap.createBitmap(
                    skin, 4 * scale, 36 * scale,
                    4 * scale, 12 * scale
                )
            )
        } else {
            SteveFrontTexture(
                skin,
                texType = texType,
                skinImageScale = 1,
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