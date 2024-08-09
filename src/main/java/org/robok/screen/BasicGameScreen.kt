package org.robok.screen

import org.robok.gl.GLContext
import org.robok.gl.RobokGLActivity
import org.robok.gl.clearColor
import org.robok.gl.clearColorBufferBit
import org.robok.gl.clearDepthBufferBit
import org.robok.gl.enableDepthTest
import org.robok.gl.setViewport
import org.robok.graphics.Color
import org.robok.unit.Size

abstract class BasicGameScreen: GameScreen() {
  override fun GLContext.onStart() {
    enableDepthTest()
    clearColor(color = Color.Black)
  }

  override fun GLContext.onSizeChanged(newSize: Size) {
    setViewport(size = newSize)
  }

  override fun GLContext.onUpdate() {
    clearColorBufferBit()
    clearDepthBufferBit()
  }
}