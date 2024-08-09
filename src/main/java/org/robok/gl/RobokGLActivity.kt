package org.robok.gl

import android.app.Activity
import android.content.Context
import android.os.Bundle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import org.robok.unit.Size

abstract class RobokGLActivity: Activity(), GLContext {

  private var glView: RobokGLView? = null
  private var glRenderer: RobokGLRenderer? = null
  private var glOnRenderer: RobokGLRenderer.OnRenderer? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    configureGLRenderer()
    configureGLView(this)
    glView?.let {
      setContentView(it)
    } ?: throw IllegalStateException("Something Wrong Happened, GLView is Null!")
  }

  private fun GLContext.configureGLRenderer() {
    glOnRenderer = object: RobokGLRenderer.OnRenderer {
      override fun onSurfaceCreated(gl: GL10, eglConfig: EGLConfig) {
        onStart()
      }
      override fun onSurfaceChanged(gl: GL10, size: Size) {
        onSizeChanged(size)
      }
      override fun onDrawFrame(gl: GL10) {
        onUpdate()
      }
    }
    glOnRenderer?.let {
      glRenderer = RobokGLRenderer(it)
    }
  }

  private fun GLContext.configureGLView(context: Context) {
    glRenderer?.let { renderer->
      glView = RobokGLView(this@RobokGLActivity, renderer)
    } ?: throw IllegalStateException("You should call configureGLRenderer() before call configureGLView(Context)")
  }

  protected abstract fun GLContext.onStart()
  protected abstract fun GLContext.onSizeChanged(newSize: Size)
  protected abstract fun GLContext.onUpdate()
}