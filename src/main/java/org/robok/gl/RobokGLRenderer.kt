package org.robok.gl

import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import org.robok.unit.Size

/**
 * The main renderer class for the game that interacts with OpenGL's GLSurfaceView.
 * This class implements the GLSurfaceView.Renderer interface, delegating OpenGL
 * rendering tasks to the provided OnRenderer instance.
 * 
 * @property onRenderer A callback interface to handle specific OpenGL rendering behavior.
 */
internal class RobokGLRenderer(private val onRenderer: RobokGLRenderer.OnRenderer) : GLSurfaceView.Renderer, GLContext {

  /**
   * Called when the OpenGL surface is created. This method delegates the call to
   * the onRenderer's onSurfaceCreated method.
   * 
   * @param gl The OpenGL context used for rendering.
   * @param eglConfig The configuration of the OpenGL surface.
   */
  override fun onSurfaceCreated(gl: GL10, eglConfig: EGLConfig) {
    onRenderer.onSurfaceCreated(gl, eglConfig)
  }

  /**
   * Called when the OpenGL surface size changes (e.g., on device rotation).
   * This method delegates the call to the onRenderer's onSurfaceChanged method.
   * 
   * @param gl The OpenGL context used for rendering.
   * @param width The new width of the surface.
   * @param height The new height of the surface.
   */
  override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
    onRenderer.onSurfaceChanged(gl, Size(width, height))
  }

  /**
   * Called to render the current frame. This method delegates the call to
   * the onRenderer's onDrawFrame method.
   * 
   * @param gl The OpenGL context used for rendering.
   */
  override fun onDrawFrame(gl: GL10) {
    onRenderer.onDrawFrame(gl)
  }

  /**
   * Interface for handling OpenGL rendering tasks.
   * Classes that need to define custom OpenGL rendering behavior should implement this interface.
   */
  interface OnRenderer {
    
    /**
     * Called when the OpenGL surface is created.
     * 
     * @param gl The OpenGL context used for rendering.
     * @param eglConfig The configuration of the OpenGL surface.
     */
    fun onSurfaceCreated(gl: GL10, eglConfig: EGLConfig)
    
    /**
     * Called when the OpenGL surface size changes.
     * 
     * @param gl The OpenGL context used for rendering.
     * @param size The new size of the surface.
     */
    fun onSurfaceChanged(gl: GL10, size: Size)
    
    /**
     * Called to render the current frame.
     * 
     * @param gl The OpenGL context used for rendering.
     */
    fun onDrawFrame(gl: GL10)
  }
}
