package org.robok.gl

import android.content.Context
import android.opengl.GLSurfaceView

/**
 * The Main OpenGL Surface View of Game.
 *
 * @property renderer The Renderer of Game.
 * @property context The Android Context.
 */
internal class RobokGLView(
  private val context: Context,
  private val renderer: RobokGLRenderer
): GLSurfaceView(context) {

  init {
    setEGLContextClientVersion(2)
    setRenderer(renderer)
  }
}