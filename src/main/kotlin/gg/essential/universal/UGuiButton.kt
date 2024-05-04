package gg.essential.universal

import net.minecraft.client.gui.widget.ClickableWidget

object UGuiButton {
    @JvmStatic
    fun getX(button: ClickableWidget): Int {
        return button.x
    }

    @JvmStatic
    fun getY(button: ClickableWidget): Int {
        return button.y
    }
}
