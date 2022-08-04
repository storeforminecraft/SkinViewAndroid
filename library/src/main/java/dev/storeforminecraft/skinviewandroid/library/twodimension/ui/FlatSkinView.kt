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

    private lateinit var steveFrontTex: SteveFrontTexture

    private var scale = 32
    private var halfSkinMode = false

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        applyAttrs(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {
        applyAttrs(context, attrs)
    }

    private fun applyAttrs(context: Context, attrs: AttributeSet) {
        val at = context.obtainStyledAttributes(attrs, R.styleable.FlatSkinView, 0, 0)

        var skinRes: Int = R.raw.skin_steve

        for (i in 0 until at.indexCount) {
            val attr = at.getIndex(i)
            when (attr) {
                R.styleable.FlatSkinView_skin -> {
                    val ref = at.getResourceId(R.styleable.FlatSkinView_skin, -1)
                    skinRes = ref
                }
                R.styleable.FlatSkinView_halfview -> {
                    halfSkinMode = at.getBoolean(R.styleable.FlatSkinView_halfview, false)
                }
                R.styleable.FlatSkinView_scale -> {
                    scale = at.getInt(R.styleable.FlatSkinView_scale, 32)
                }
            }
        }

        val `is`: InputStream = resources.openRawResource(skinRes)

        val bitmap: Bitmap = try {
            BitmapFactory.decodeStream(`is`)
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
            }
        }

        renderSkin(bitmap)
        at.recycle()
    }

    fun renderSkin(bitmap: Bitmap) {
        steveFrontTex = SteveTextureUtil.getSteveFrontTex(bitmap).apply {
            scale(scale)
        }
        bitmap.recycle()
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (halfSkinMode)
            setMeasuredDimension(16 * scale, 32 * scale / 2)
        else
            setMeasuredDimension(16 * scale, 32 * scale)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null) return

        steveFrontTex.let { tex ->
            tex.head.let {
                canvas.drawBitmap(it, tex.leftArm.width, 0, null)
            }
            tex.hat.let {
                canvas.drawBitmap(
                    it,
                    tex.leftArm.width - it.calcLayerLeftOffset(tex.head),
                    -it.calcLayerTopOffset(tex.head),
                    null
                )
            }

            tex.torso.let {
                canvas.drawBitmap(it, tex.leftArm.width, tex.head.height, null)
            }
            tex.torso2nd?.let {
                canvas.drawBitmap(
                    it,
                    tex.leftArm.width - it.calcLayerLeftOffset(tex.torso),
                    tex.head.height - it.calcLayerTopOffset(tex.torso),
                    null
                )
            }

            tex.leftArm.let {
                canvas.drawBitmap(it, 0, tex.head.height, null)
            }
            tex.leftArm2nd?.let {
                canvas.drawBitmap(
                    it,
                    -it.calcLayerLeftOffset(tex.leftArm),
                    tex.head.height - it.calcLayerTopOffset(tex.leftArm),
                    null
                )
            }

            tex.rightArm.let {
                canvas.drawBitmap(
                    it,
                    tex.leftLeg.width + tex.torso.width,
                    tex.head.height,
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

            if (halfSkinMode) return@let

            tex.leftLeg.let {
                canvas.drawBitmap(
                    it,
                    tex.leftArm.width,
                    tex.head.height + tex.torso.height,
                    null
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

            tex.rightLeg.let {
                canvas.drawBitmap(
                    it,
                    tex.leftArm.width + tex.leftLeg.width,
                    tex.head.height + tex.torso.height,
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

private fun Bitmap.half(): Bitmap {
    return Bitmap.createBitmap(this, 0, 0, width, height / 2)
}