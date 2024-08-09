@file:JvmName("GL")

package org.robok.gl

import org.robok.graphics.Color
import org.robok.graphics.shader.Shader
import org.robok.unit.Size
import java.nio.Buffer

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
@JvmOverloads
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

fun GLContext.createProgram(): Int {
  return GLES32.glCreateProgram()
}

fun GLContext.linkProgram(program: Int) {
  GLES32.glLinkProgram(program)
}

fun GLContext.useProgram(program: Int) {
  GLES32.glUseProgram(program)
}

fun GLContext.loadShader(
  type: Int,
  code: String
): Int {
  val shader = GLES32.glCreateShader(type)
  GLES32.glShaderSource(shader, code)
  GLES32.glCompileShader(shader)
  return shader
}

fun GLContext.attachShader(
  program: Int,
  shader: Shader
) {
  GLES32.glAttachShader(program, shader.vertexShader)
  GLES32.glAttachShader(program, shader.fragmentShader)
}

fun GLContext.getAttributeLocation(
  program: Int,
  varName: String
): Int {
  return GLES32.glGetAttribLocation(program, varName)
}

fun GLContext.getUniformLocation(
  program: Int,
  varName: String
): Int {
  return GLES32.glGetUniformLocation(program, varName)
}

fun GLContext.enableVertexAttributeArray(handle: Int) {
  GLES32.glEnableVertexAttribArray(handle)
}

fun GLContext.disableVertexAttributeArray(handle: Int) {
  GLES32.glDisableVertexAttribArray(handle)
}

@JvmOverloads
fun GLContext.vertexAttributePointer(
  type: Int = GLES32.GL_FLOAT,
  normalized: Boolean = false,
  stride: Int = 0,
  index: Int,
  size: Int,
  buffer: Buffer
) {
  GLES32.glVertexAttribPointer(
    index,
    size,
    type,
    normalized,
    stride,
    buffer
  )
}

@JvmOverloads
fun GLContext.uniformMatrix4fv(
  count: Int = 0,
  transpose: Boolean = false,
  offset: Int = 0,
  location: Int,
  value: FloatArray,
) {
  GLES32.glUniformMatrix4fv(
    location,
    count,
    transpose,
    value,
    offset
  )
}

@JvmOverloads
fun GLContext.draw(
  mode: Int = GLES32.GL_TRIANGLES,
  count: Int,
  type: Int,
  buffer: Buffer
) {
  GLES32.glDrawElements(mode, count, type, buffer)
}