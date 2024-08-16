package robok.game.screen

import android.app.Activity
import android.os.Bundle

open class GameScreen : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onScreenCreated()
    }

    // Method similar to onCreate for additional setup
    open fun onScreenCreated() {
        // Implement additional logic for screen setup here
    }
}
