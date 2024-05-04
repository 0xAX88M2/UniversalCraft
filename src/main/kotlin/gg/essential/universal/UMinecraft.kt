package gg.essential.universal

import com.mojang.blaze3d.Blaze3D
import net.minecraft.client.Minecraft
import net.minecraft.client.Options
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.components.ChatComponent
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.client.player.LocalPlayer


object UMinecraft {
    //#if MC>=11900
    private var guiScaleValue: Int
        get() = getSettings().guiScale().get()
        set(value) { getSettings().guiScale().set(value) }
    //#else
    //$$ private var guiScaleValue: Int
    //$$     get() = getSettings().guiScale
    //$$     set(value) { getSettings().guiScale = value }
    //#endif

    @JvmStatic
    var guiScale: Int
        get() = guiScaleValue
        set(value) {
            guiScaleValue = value
            //#if MC>=11502
            val mc = getMinecraft()
            val window = mc.window
            val scaleFactor = window.calculateScale(value, mc.isEnforceUnicode())
            window.guiScale = scaleFactor.toDouble()
            //#endif
        }

    @JvmField
    val isRunningOnMac: Boolean =
        Minecraft.ON_OSX

    @JvmStatic
    fun getMinecraft(): Minecraft {
        return Minecraft.getInstance()
    }

    @JvmStatic
    fun getWorld(): ClientLevel? {
        return getMinecraft().level
    }

    @JvmStatic
    fun getNetHandler(): ClientPacketListener? {
        return getMinecraft().connection
    }

    @JvmStatic
    fun getPlayer(): LocalPlayer? {
        return getMinecraft().player
    }

    @JvmStatic
    fun getFontRenderer(): Font {
        return getMinecraft().font
    }

    @JvmStatic
    fun getTime(): Long {
        //#if MC>=11502
        return (Blaze3D.getTime() * 1000).toLong()
        //#else
        //$$ return Minecraft.getSystemTime()
        //#endif
    }

    @JvmStatic
    fun getChatGUI(): ChatComponent? = getMinecraft().gui?.chat

    @JvmStatic
    fun getSettings(): Options = getMinecraft().options
}
