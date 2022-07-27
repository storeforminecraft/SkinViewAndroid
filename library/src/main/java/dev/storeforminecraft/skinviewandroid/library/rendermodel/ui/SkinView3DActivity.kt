package dev.storeforminecraft.skinviewandroid.library.rendermodel.ui

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle

class SkinView3DActivity : Activity() {

    private lateinit var gLView: GLSurfaceView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gLView = SkinView3DSurfaceView(this)
        setContentView(gLView)
    }
}