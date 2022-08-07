package dev.storeforminecraft.skinviewandroid.library.libs

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.graphics.get
import androidx.core.graphics.set
import dev.storeforminecraft.skinviewandroid.library.datas.ModelSourceTextureType

object SkinUtil {

    fun getTexType(bitmap: Bitmap): ModelSourceTextureType {
        return if (bitmap.width >= 64 && bitmap.height >= 64 && bitmap.width == bitmap.height) {
            if (isSlimSkin(bitmap)) {
                ModelSourceTextureType.RATIO_1_1_SLIM
            } else {
                ModelSourceTextureType.RATIO_1_1
            }
        } else if (bitmap.width == bitmap.height * 2) {
            ModelSourceTextureType.RATIO_2_1
        } else {
            ModelSourceTextureType.UNKNOWN
        }
    }

    private fun isSlimSkin(bitmap: Bitmap): Boolean {
        val scale = bitmap.width / 64
        return checkColor(
            bitmap,
            50 * scale,
            16 * scale,
            2 * scale,
            4 * scale,
            Color.TRANSPARENT
        ) ||
                checkColor(
                    bitmap,
                    54 * scale,
                    20 * scale,
                    2 * scale,
                    12 * scale,
                    Color.TRANSPARENT
                ) ||
                checkColor(
                    bitmap,
                    42 * scale,
                    48 * scale,
                    2 * scale,
                    4 * scale,
                    Color.TRANSPARENT
                ) ||
                checkColor(bitmap, 46 * scale, 52 * scale, 2 * scale, 12 * scale, Color.TRANSPARENT)
    }

    private fun checkColor(bitmap: Bitmap, x: Int, y: Int, w: Int, h: Int, checkColor: Int): Boolean {
        for (wi in 1..w) {
            for (hi in 1..h) {
                if (bitmap[x + wi, y + hi] != checkColor) {
                    return false
                }
            }
        }
        return true
    }
}