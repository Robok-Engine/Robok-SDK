package org.robok.internal

import java.io.File

object Dirs {
  object Project {
    fun Root(projectName: String): File {
      return File("/sdcard/Robok/Projects", projectName)
    }

    fun Res(projectName: String): File {
      return File(Root(projectName), "game/src/resources")
    }
  }
}