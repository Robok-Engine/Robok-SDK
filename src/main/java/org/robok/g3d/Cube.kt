package org.robok.g3d

import android.content.Context
import android.opengl.Matrix
import org.robok.gl.GLContext
import org.robok.gl.GLES32
import org.robok.gl.GLObject
import org.robok.gl.attachShader
import org.robok.gl.createProgram
import org.robok.gl.disableVertexAttributeArray
import org.robok.gl.draw
import org.robok.gl.enableVertexAttributeArray
import org.robok.gl.getAttributeLocation
import org.robok.gl.getUniformLocation
import org.robok.gl.linkProgram
import org.robok.gl.uniformMatrix4fv
import org.robok.gl.useProgram
import org.robok.gl.vertexAttributePointer
import org.robok.graphics.Color
import org.robok.graphics.shader.Shader
import org.robok.graphics.shader.ShaderSource
import org.robok.graphics.shader.compileShaderSource
import org.robok.graphics.shader.readShaderSourceFromAndroidAssets
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

/** 3D Cube */
class Cube(
  private val androidContext: Context,
  private val glContext: GLContext
): GLObject {

  companion object {
    private const val COORDS_PER_VERTEX = 3

    private val CUBE_COORDS = floatArrayOf(
        -0.5f, 0.5f, 0.5f,   // Vertex 0
        -0.5f, -0.5f, 0.5f,  // Vertex 1
        0.5f, -0.5f, 0.5f,   // Vertex 2
        0.5f, 0.5f, 0.5f,    // Vertex 3
        -0.5f, 0.5f, -0.5f,  // Vertex 4
        -0.5f, -0.5f, -0.5f, // Vertex 5
        0.5f, -0.5f, -0.5f,  // Vertex 6
        0.5f, 0.5f, -0.5f    // Vertex 7
    )

    private val CUBE_DRAW_ORDER = shortArrayOf(
        0, 1, 2, 0, 2, 3,   // Front face
        4, 5, 6, 4, 6, 7,   // Back face
        0, 1, 5, 0, 5, 4,   // Left face
        2, 3, 7, 2, 7, 6,   // Right face
        0, 3, 7, 0, 7, 4,   // Top face
        1, 2, 6, 1, 6, 5    // Bottom face
    )
  }

  /**
   * The Color Of Cube. Default white.
   */
  public var color: Color = Color.White
    set(value) {
      if (value != field) {
        field = value
        updateCubeColorBuffer()
      }
    }

  private val cubeModelMatrix = FloatArray(size = 16)

  private var cubeProgram: Int? = null

  private var cubeVertexBuffer: FloatBuffer? = null
  private var cubeColorBuffer: FloatBuffer? = null
  private var cubeDrawListBuffer: ShortBuffer? = null

  init {
    glContext.initialize()
  }

  public fun GLContext.draw(mvpMatrix: FloatArray) {
    cubeProgram?.let { program ->
      cubeVertexBuffer?.let { vertexBuffer ->
        cubeColorBuffer?.let { colorBuffer ->
          cubeDrawListBuffer?.let { drawListBuffer ->
            useProgram(program)

            val positionHandle = getAttributeLocation(program, "vPosition")
            enableVertexAttributeArray(positionHandle)
            vertexAttributePointer(
              index = positionHandle,
              size = COORDS_PER_VERTEX,
              buffer = vertexBuffer
            )

            val colorHandle = getAttributeLocation(program, "vColor")
            enableVertexAttributeArray(colorHandle)
            vertexAttributePointer(
              index = colorHandle,
              size = 4,
              buffer = colorBuffer
            )

            val matrixHandle = getUniformLocation(program, "uMVPMatrix")
            uniformMatrix4fv(
              location = matrixHandle,
              count = 1,
              value = mvpMatrix
            )

            draw(
              count = CUBE_DRAW_ORDER.size,
              type = GLES32.GL_UNSIGNED_SHORT,
              buffer = drawListBuffer
            )

            disableVertexAttributeArray(positionHandle)
            disableVertexAttributeArray(colorHandle)
          }
        }
      }
    }
  }

  private fun GLContext.initialize() {
    initializeCubeVertexBuffer()
    updateCubeColorBuffer()
    initializeCubeDrawListBuffer()
    loadShaders()
    Matrix.setIdentityM(cubeModelMatrix, 0)
  }

  private fun initializeCubeVertexBuffer() {
    val tempBuffer = ByteBuffer.allocateDirect(CUBE_COORDS.size * 4) // 4 bytes per float
    tempBuffer.order(ByteOrder.nativeOrder())
    cubeVertexBuffer = tempBuffer.asFloatBuffer()
    cubeVertexBuffer?.put(CUBE_COORDS)
    cubeVertexBuffer?.position(0)
  }

  private fun updateCubeColorBuffer() {
    val color32 = color.toFloatArray32() // FloatArray with 32 elements.
    val tempBuffer = ByteBuffer.allocateDirect(color32.size * 4) // 4 byter per float.
    tempBuffer.order(ByteOrder.nativeOrder())
    cubeColorBuffer = tempBuffer.asFloatBuffer()
    cubeColorBuffer?.put(color32)
    cubeColorBuffer?.position(0)
  }

  private fun initializeCubeDrawListBuffer() {
    val tempBuffer = ByteBuffer.allocateDirect(CUBE_DRAW_ORDER.size * 2) // 2 bytes per short
    tempBuffer.order(ByteOrder.nativeOrder())
    cubeDrawListBuffer = tempBuffer.asShortBuffer()
    cubeDrawListBuffer?.put(CUBE_DRAW_ORDER)
    cubeDrawListBuffer?.position(0)
  }

  private fun GLContext.loadShaders() {
    val shaderSource = readShaderSourceFromAndroidAssets(
      androidContext = androidContext,
      vertexShaderPath = "shaders/cube/vertex_shader.vert",
      fragmentShaderPath = "shaders/cube/fragment_shader.frag"
    )
    val shader = compileShaderSource(shaderSource)
    cubeProgram = createProgram()
    cubeProgram?.let {
      attachShader(it, shader)
      linkProgram(it)
    }
  }
}