package robok.game.gui

open class GUIView {

    var name: String? = null
    var id: String? = null

    var width: Int = 0
    var height: Int = 0

    var listener: GUIViewListener? = null

    open fun onViewCreated() {
        // TO-DO: Implement logic to create base view
        // Example of how to program the click listener
        // view.setOnClickListener {
        //     listener?.onClick(view)
        // }
    }

    override fun toString(): String {
        return "GUI View ID: $id\nName: $name\nWidth: $width\nHeight: $height"
    }

    fun setGUIViewListener(listener: GUIViewListener?) {
        this.listener = listener
    }
}
