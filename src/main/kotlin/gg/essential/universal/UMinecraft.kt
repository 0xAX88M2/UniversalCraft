package gg.essential.universal

import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.hud.ChatHud
import net.minecraft.client.world.ClientWorld
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.client.option.GameOptions

//#if MC>=11502
import net.minecraft.client.util.GlfwUtil
//#endif

object UMinecraft {
    //#if MC>=11900
    private var guiScaleValue: Int
        get() = getSettings().guiScale.value
        set(value) { getSettings().guiScale.value = value }
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
            val scaleFactor = window.calculateScaleFactor(value, mc.forcesUnicodeFont())
            window.scaleFactor = scaleFactor.toDouble()
            //#endif
        }

    @JvmField
    val isRunningOnMac: Boolean =
        MinecraftClient.IS_SYSTEM_MAC

    @JvmStatic
    fun getMinecraft(): MinecraftClient {
        return MinecraftClient.getInstance()
    }

    @JvmStatic
    fun getWorld(): ClientWorld? {
        return getMinecraft().world
    }

    @JvmStatic
    fun getNetHandler(): ClientPlayNetworkHandler? {
        return getMinecraft().networkHandler
    }

    @JvmStatic
    fun getPlayer(): ClientPlayerEntity? {
        return getMinecraft().player
    }

    @JvmStatic
    fun getFontRenderer(): TextRenderer {
        return getMinecraft().textRenderer
    }

    @JvmStatic
    fun getTime(): Long {
        //#if MC>=11502
        return (GlfwUtil.getTime() * 1000).toLong()
        //#else
        //$$ return Minecraft.getSystemTime()
        //#endif
    }

    @JvmStatic
    fun getChatGUI(): ChatHud? = getMinecraft().inGameHud?.chatHud

    @JvmStatic
    fun getSettings(): GameOptions = getMinecraft().options
}
