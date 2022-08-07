package dev.storeforminecraft.skinviewandroid.library.twodimension.texture

import android.graphics.Bitmap
import androidx.core.graphics.scale
import dev.storeforminecraft.skinviewandroid.library.datas.ModelSourceTextureType

data class SteveFrontTexture(
    val originalFile : Bitmap,
    val texType: ModelSourceTextureType,
    val skinImageScale : Int = 1,
    var head: Bitmap,
    var hat: Bitmap,
    var torso: Bitmap,
    var torso2nd: Bitmap? = null,
    var leftArm: Bitmap,
    var leftArm2nd: Bitmap? = null,
    var rightArm: Bitmap,
    var rightArm2nd: Bitmap? = null,
    var leftLeg: Bitmap,
    var leftLeg2nd: Bitmap? = null,
    var rightLeg: Bitmap,
    var rightLeg2nd: Bitmap? = null
) {
    fun scale(scale: Int, secondLayerScale: Float = 1.10f) {
        head = head.let { it.scale(it.width * scale, it.height * scale, false) }
        torso = torso.let { it.scale(it.width * scale, it.height * scale, false) }
        leftArm = leftArm.let { it.scale(it.width * scale, it.height * scale, false) }
        rightArm = rightArm.let { it.scale(it.width * scale, it.height * scale, false) }
        leftLeg = leftLeg.let { it.scale(it.width * scale, it.height * scale, false) }
        rightLeg = rightLeg.let { it.scale(it.width * scale, it.height * scale, false) }

        hat = hat.let {
            it.scale(
                (it.width * scale * secondLayerScale).toInt(),
                (it.height * scale * secondLayerScale).toInt(), false
            )
        }
        torso2nd = torso2nd?.let {
            it.scale(
                (it.width * scale * secondLayerScale).toInt(),
                (it.height * scale * secondLayerScale).toInt(), false
            )
        }
        leftArm2nd = leftArm2nd?.let {
            it.scale(
                (it.width * scale * secondLayerScale).toInt(),
                (it.height * scale * secondLayerScale).toInt(), false
            )
        }
        rightArm2nd = rightArm2nd?.let {
            it.scale(
                (it.width * scale * secondLayerScale).toInt(),
                (it.height * scale * secondLayerScale).toInt(), false
            )
        }
        leftLeg2nd = leftLeg2nd?.let {
            it.scale(
                (it.width * scale * secondLayerScale).toInt(),
                (it.height * scale * secondLayerScale).toInt(), false
            )
        }
        rightLeg2nd = rightLeg2nd?.let {
            it.scale(
                (it.width * scale * secondLayerScale).toInt(),
                (it.height * scale * secondLayerScale).toInt(), false
            )
        }
    }
}