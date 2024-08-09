package org.robok.g3d

import org.robok.gl.GLObject
import org.robok.graphics.Color
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/** 3D Cube */
class Cube: GLObject {

  /**
   * The Color Of Cube. Default white.
   */
  var color: Color = Color.White
    set(value) {
      if (value != field) {
        field = value
        updateCubeColorBuffer()
      }
    }

  private var cubeVertexBuffer: FloatBuffer? = null
  private var cubeColorBuffer: FloatBuffer? = null

  init {
    initialize()
  }

  private fun initialize() {
    // TODO
  }

  private fun updateCubeColorBuffer() {
    val color32 = color.toFloatArray32() // FloatArray with 32 elements.
    val tempBuffer = ByteBuffer.allocateDirect(color32.size * 4) // 4 byter per float.
    tempBuffer.order(ByteOrder.nativeOrder())
    cubeColorBuffer = tempBuffer.asFloatBuffer()
    cubeColorBuffer?.put(color32)
    cubeColorBuffer?.position(0)
  }
}