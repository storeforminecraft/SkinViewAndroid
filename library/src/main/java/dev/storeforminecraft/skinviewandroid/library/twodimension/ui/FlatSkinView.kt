package dev.storeforminecraft.skinviewandroid.library.twodimension.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import dev.storeforminecraft.skinviewandroid.library.R
import dev.storeforminecraft.skinviewandroid.library.twodimension.texture.SteveFrontTexture
import dev.storeforminecraft.skinviewandroid.library.twodimension.texture.SteveTextureUtil
import java.io.IOException
import java.io.InputStream


class FlatSkinView : View {

    private val SCALE = 32

    private var skinBitmap: Bitmap? = null

    private var steveFrontTex: SteveFrontTexture

    constructor(context: Context) : super(context) {
        val `is`: InputStream = resources
            .openRawResource(R.raw.test2)

        val bitmap: Bitmap = try {
            BitmapFactory.decodeStream(`is`)
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
            }
        }
        skinBitmap = bitmap
        steveFrontTex = SteveTextureUtil.getSteveFrontTex(bitmap).apply {
            scale(SCALE)
        }
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {

        val `is`: InputStream = resources
            .openRawResource(R.raw.test2)

        val bitmap: Bitmap = try {
            BitmapFactory.decodeStream(`is`)
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
            }
        }
        skinBitmap = bitmap
        steveFrontTex = SteveTextureUtil.getSteveFrontTex(bitmap).apply {
            scale(SCALE)
        }
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {
        val `is`: InputStream = resources
            .openRawResource(R.raw.test2)

        val bitmap: Bitmap = try {
            BitmapFactory.decodeStream(`is`)
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
            }
        }
        skinBitmap = bitmap
        steveFrontTex = SteveTextureUtil.getSteveFrontTex(bitmap).apply {
            scale(SCALE)
        }
    }

//    private fun applyAttrs(context: Context, attrs: AttributeSet) {
//        context.obtainStyledAttributes(attrs, R.styleable.FlatSkinView, 0, 0).also {
//            val ref = it.getResourceId(R.styleable.FlatSkinView_skin, -1)
//
//            val `is`: InputStream = resources
//                .openRawResource(ref)
//
//            val bitmap: Bitmap = try {
//                BitmapFactory.decodeStream(`is`)
//            } finally {
//                try {
//                    `is`.close()
//                } catch (e: IOException) {
//                }
//            }
//            skinBitmap = bitmap
//        }
//
//
//    }

//    private fun refine() {
//        val head = Bitmap.createBitmap(skinBitmap!!, 8, 8, 8, 8)
//            .scale(8 * SCALE, 8 * SCALE, false)
//        skinParts.add(head)
//
//        val torso = Bitmap.createBitmap(skinBitmap!!, 20, 20, 8, 12)
//            .scale(8 * SCALE, 12 * SCALE, false)
//        skinParts.add(torso)
//
//        val leftarm = Bitmap.createBitmap(skinBitmap!!, 36, 52, 4, 12)
//            .scale(4 * SCALE, 12 * SCALE, false)
//        skinParts.add(leftarm)
//
//        val rightarm = Bitmap.createBitmap(skinBitmap!!, 44, 20, 4, 12)
//            .scale(4 * SCALE, 12 * SCALE, false)
//        skinParts.add(rightarm)
//
//        val leftleg = Bitmap.createBitmap(skinBitmap!!, 20, 52, 4, 12)
//            .scale(4 * SCALE, 12 * SCALE, false)
//        skinParts.add(leftleg)
//
//        val rightleg = Bitmap.createBitmap(skinBitmap!!, 4, 20, 4, 12)
//            .scale(4 * SCALE, 12 * SCALE, false)
//        skinParts.add(rightleg)
//
//        val hat = Bitmap.createBitmap(skinBitmap!!, 40, 8, 8, 8)
//            .scale((8 * SCALE * 1.125f).toInt(), (8 * SCALE * 1.125f).toInt(), false)
//        skinParts.add(hat)
//
//        val torso2nd = Bitmap.createBitmap(skinBitmap!!, 20, 36, 8, 12)
//            .scale((8 * SCALE * 1.1f).toInt(), (12 * SCALE * 1.1f).toInt(), false)
//        skinParts.add(torso2nd)
//
//        val leftarm2nd = Bitmap.createBitmap(skinBitmap!!, 52, 52, 4, 12)
//            .scale((4 * SCALE * 1.1f).toInt(), (12 * SCALE * 1.1f).toInt(), false)
//        skinParts.add(leftarm2nd)
//
//        val rightarm2nd = Bitmap.createBitmap(skinBitmap!!, 44, 36, 4, 12)
//            .scale((4 * SCALE * 1.1f).toInt(), (12 * SCALE * 1.1f).toInt(), false)
//        skinParts.add(rightarm2nd)
//
//        val leftleg2nd = Bitmap.createBitmap(skinBitmap!!, 4, 52, 4, 12)
//            .scale((4 * SCALE * 1.1f).toInt(), (12 * SCALE * 1.1f).toInt(), false)
//        skinParts.add(leftarm2nd)
//
//        val rightleg2nd = Bitmap.createBitmap(skinBitmap!!, 4, 36, 4, 12)
//            .scale((4 * SCALE * 1.1f).toInt(), (12 * SCALE * 1.1f).toInt(), false)
//        skinParts.add(rightarm2nd)
//    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null) return

        steveFrontTex.let { tex ->
            tex.head.let {
                canvas.drawBitmap(it, tex.leftArm.width, 0, null)
            }
            tex.torso.let {
                canvas.drawBitmap(it, tex.leftArm.width, tex.head.height, null)
            }
            tex.leftArm.let {
                canvas.drawBitmap(it, 0, tex.head.height, null)
            }
            tex.rightArm.let {
                canvas.drawBitmap(it, tex.leftLeg.width + tex.torso.width, tex.head.height, null)
            }
            tex.leftLeg.let {
                canvas.drawBitmap(it, tex.leftArm.width, tex.head.height + tex.torso.height, null)
            }
            tex.rightLeg.let {
                canvas.drawBitmap(
                    it,
                    tex.leftArm.width + tex.leftLeg.width,
                    tex.head.height + tex.torso.height,
                    null
                )
            }

            tex.hat.let {
                canvas.drawBitmap(
                    it,
                    tex.leftArm.width - it.calcLayerLeftOffset(tex.head),
                    -it.calcLayerTopOffset(tex.head),
                    null
                )
            }

            tex.torso2nd?.let {
                canvas.drawBitmap(
                    it,
                    tex.leftArm.width - it.calcLayerLeftOffset(tex.torso),
                    tex.head.height - it.calcLayerTopOffset(tex.torso),
                    null
                )
            }

            tex.leftArm2nd?.let {
                canvas.drawBitmap(
                    it,
                    -it.calcLayerLeftOffset(tex.leftArm),
                    tex.head.height - it.calcLayerTopOffset(tex.leftArm),
                    null
                )
            }

            tex.rightArm2nd?.let {
                canvas.drawBitmap(
                    it,
                    tex.leftLeg.width + tex.torso.width - it.calcLayerLeftOffset(tex.rightArm),
                    tex.head.height - it.calcLayerTopOffset(tex.rightArm), null
                )
            }

            tex.leftLeg2nd?.let {
                canvas.drawBitmap(
                    it,
                    tex.leftArm.width - it.calcLayerLeftOffset(tex.leftLeg),
                    tex.head.height + tex.torso.height - it.calcLayerTopOffset(tex.leftLeg),
                    null
                )
            }
            tex.rightLeg2nd?.let {
                canvas.drawBitmap(
                    it,
                    tex.leftArm.width + tex.leftLeg.width - it.calcLayerLeftOffset(tex.rightLeg),
                    tex.head.height + tex.torso.height - it.calcLayerTopOffset(tex.rightLeg),
                    null
                )
            }
        }
    }
}

private fun Canvas.drawBitmap(it: Bitmap, left: Int, top: Int, paint: Paint?) {
    this.drawBitmap(it, left.toFloat(), top.toFloat(), paint)
}

private fun Bitmap.calcLayerLeftOffset(firstLayer: Bitmap): Int {
    return this.width.minus(firstLayer.width).times(0.5f).toInt()
}

private fun Bitmap.calcLayerTopOffset(firstLayer: Bitmap): Int {
    return this.height.minus(firstLayer.height).times(0.5f).toInt()
}