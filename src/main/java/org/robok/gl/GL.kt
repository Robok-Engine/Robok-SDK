@file:JvmName("GL")

package org.robok.gl

import android.opengl.GLES32
import org.robok.graphics.Color
import org.robok.unit.Size

/**
 * Sets the background color of the OpenGL context.
 * 
 * @param color The [Color] to set as the background color. 
 *              The color's `r`, `g`, `b`, and `a` values will be used 
 *              to set the red, green, blue, and alpha (transparency) channels respectively.
 */
fun GLContext.clearColor(color: Color) {
  GLES32.glClearColor(color.r, color.g, color.b, color.a)
}

/**
 * Sets the OpenGL viewport to a specific region of the window.
 * The viewport defines the rectangular area where the rendering will be displayed.
 * 
 * @param x The x-coordinate of the lower-left corner of the viewport (default is 0).
 * @param y The y-coordinate of the lower-left corner of the viewport (default is 0).
 * @param size The [Size] object representing the width and height of the viewport.
 */
fun GLContext.setViewport(
  x: Int = 0,
  y: Int = 0,
  size: Size
) {
  GLES32.glViewport(
    x,
    y,
    size.width,
    size.height
  )
}

/**
 * Enables a specified OpenGL capability.
 *
 * This function calls the OpenGL ES 3.2 `glEnable` function to enable a specified capability.
 *
 * @param cap The OpenGL capability to enable. This is typically one of the constants such as
 *            `GLES32.GL_DEPTH_TEST`, `GLES32.GL_CULL_FACE`, etc.
 */
fun GLContext.enable(cap: Int) {
  GLES32.glEnable(cap)
}

/**
 * Clears the color or depth buffer, or any combination of them.
 *
 * This function calls the OpenGL ES 3.2 `glClear` function with the given mask, which can represent
 * one or more buffers to be cleared.
 *
 * @param mask The mask specifying which buffers to clear. This can be a combination of flags such
 *             as `GLES32.GL_COLOR_BUFFER_BIT`, `GLES32.GL_DEPTH_BUFFER_BIT`, etc.
 */
fun GLContext.clear(mask: Int) {
  GLES32.glClear(mask)
}

/**
 * Enables the depth test functionality in OpenGL.
 *
 * This function calls `enable` with the constant `GLES32.GL_DEPTH_TEST`, which enables depth testing
 * in the OpenGL context. Depth testing ensures that pixels are rendered correctly in 3D space based on
 * their depth values.
 */
fun GLContext.enableDepthTest() {
  enable(GLES32.GL_DEPTH_TEST)
}

/**
 * Clears the color buffer bit in the OpenGL context.
 *
 * This function calls `clear` with the constant `GLES32.GL_COLOR_BUFFER_BIT`, which clears the color
 * buffer, effectively resetting the contents of the screen.
 */
fun GLContext.clearColorBufferBit() {
  clear(GLES32.GL_COLOR_BUFFER_BIT)
}

/**
 * Clears the depth buffer bit in the OpenGL context.
 *
 * This function calls `clear` with the constant `GLES32.GL_DEPTH_BUFFER_BIT`, which clears the depth
 * buffer. This is useful when you need to reset depth values, typically when rendering a new frame.
 */
fun GLContext.clearDepthBufferBit() {
  clear(GLES32.GL_DEPTH_BUFFER_BIT)
}