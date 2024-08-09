@file:JvmName("ShaderUtil")
package org.robok.graphics.shader

import android.content.Context
import org.robok.gl.GLContext
import org.robok.gl.GLES32
import org.robok.gl.loadShader
import org.robok.internal.Dirs
import org.robok.internal.readTextFromAndroidAssets
import java.io.File

data class ShaderSource(
  val vertexShaderCode: String,
  val fragmentShaderCode: String
)

fun GLContext.compileShaderSource(shaderSource: ShaderSource): Shader {
  return Shader(
    vertexShader = loadShader(
      type = GLES32.GL_VERTEX_SHADER,
      code = shaderSource.vertexShaderCode
    ),
    fragmentShader = loadShader(
      type = GLES32.GL_FRAGMENT_SHADER,
      code = shaderSource.fragmentShaderCode
    )
  )
}

fun GLContext.readShaderSourceFromFile(
  vertexShaderFile: File,
  fragmentShaderFile: File
): ShaderSource {
  if (!vertexShaderFile.extension.equals(".vert")) {
    throw ShaderException("The vertex shader file extension must be .vert")
  }
  if (!fragmentShaderFile.extension.equals(".frag")) {
    throw ShaderException("The fragment shader file extension must be .frag")
  }
  val vertexShaderCode = vertexShaderFile.readText()
  val fragmentShaderCode = fragmentShaderFile.readText()
  if (vertexShaderCode.isEmpty()) {
    throw ShaderException("Empty vertex shader code, or could not be read.")
  }
  if (fragmentShaderCode.isEmpty()) {
    throw ShaderException("Empty fragment shader code, or could not be read.")
  }
  return ShaderSource(
    vertexShaderCode = vertexShaderCode,
    fragmentShaderCode = fragmentShaderCode
  )
}

internal fun GLContext.readShaderSourceFromAndroidAssets(
  androidContext: Context,
  vertexShaderPath: String,
  fragmentShaderPath: String
): ShaderSource {
  val vertexShaderCode = androidContext.readTextFromAndroidAssets(vertexShaderPath)
  val fragmentShaderCode = androidContext.readTextFromAndroidAssets(fragmentShaderPath)
  if (vertexShaderCode.isEmpty()) {
    throw ShaderException("Empty vertex shader code, or could not be read.")
  }
  if (fragmentShaderCode.isEmpty()) {
    throw ShaderException("Empty fragment shader code, or could not be read.")
  }
  return ShaderSource(
    vertexShaderCode = vertexShaderCode,
    fragmentShaderCode = fragmentShaderCode
  )
}