package org.robok.graphics

import androidx.annotation.FloatRange

/**
 * The class that represents the Color.
 *
 * @property r The percentage of red in the color.
 * @property g The percentage of green in the color.
 * @property b The percentage of blue in the color.
 * @property a The amount of alpha.
 */
class Color(
  @FloatRange(from = 0.0, to = 1.0) val r: Float,
  @FloatRange(from = 0.0, to = 1.0) val g: Float,
  @FloatRange(from = 0.0, to = 1.0) val b: Float,
  @FloatRange(from = 0.0, to = 1.0) val a: Float = 1.0f
) {
  companion object {
    val Red = Color(1.0f, 0.0f, 0.0f, 1.0f)
    val Green = Color(0.0f, 1.0f, 0.0f, 1.0f)
    val Blue = Color(0.0f, 0.0f, 1.0f, 1.0f)
    val White = Color(1.0f, 1.0f, 1.0f, 1.0f)
    val Black = Color(0.0f, 0.0f, 0.0f, 1.0f)
  }

  /** Returns the current Color as Integer */
  fun toInt(): Int {
    val alphaInt = (a * 255).toInt()
    val redInt = (r * 255).toInt()
    val greenInt = (g * 255).toInt()
    val blueInt = (b * 255).toInt()
    return (alphaInt shl 24) or (redInt shl 16) or (greenInt shl 8) or blueInt
  }

  /**
   * Returns a hexadecimal representing current Color.
   */
  fun toHex(): String {
    val alphaHex = (a * 255).toInt().toString(16).padStart(2, '0')
    val redHex = (r * 255).toInt().toString(16).padStart(2, '0')
    val greenHex = (g * 255).toInt().toString(16).padStart(2, '0')
    val blueHex = (b * 255).toInt().toString(16).padStart(2, '0')

    return "#$alphaHex$redHex$greenHex$blueHex"
  }

  /**
   * Converts the RGBA components to a FloatArray containing 4 values.
   *
   * @return A FloatArray with the values of r, g, b, and a in that order.
   */
  fun toFloatArray4(): FloatArray {
    return floatArrayOf(r, g, b, a)
  }

  /**
   * Converts the RGBA components to a FloatArray containing 32 values.
   * 
   * This function returns an array where the RGBA values are repeated 8 times in the same order,
   * creating a total of 32 values in the array.
   * 
   * @return A FloatArray with 32 values, consisting of repeated RGBA components.
   */
  fun toFloatArray32(): FloatArray {
    return floatArrayOf(
        r, g, b, a,
        r, g, b, a,
        r, g, b, a,
        r, g, b, a,
        r, g, b, a,
        r, g, b, a,
        r, g, b, a,
        r, g, b, a,
    )
  }

  /**
   * Mix the current color with another color.
   *
   * @param color The color to mix with the current one.
   * @param weight The Weight that each Color will contribute to the final color.
   */
  fun blend(
    other: Color,
    @FloatRange(from = 0.0, to = 1.0) weight: Float
  ): Color {
    val blendedR = r * (1 - weight) + other.r * weight
    val blendedG = g * (1 - weight) + other.g * weight
    val blendedB = b * (1 - weight) + other.b * weight
    val blendedA = a * (1 - weight) + other.a * weight
    return Color(blendedR, blendedG, blendedB, blendedA)
  }

  override fun toString(): String {
    return "Color(r=$r, g=$g, b=$b, a=$a)"
  }
}
