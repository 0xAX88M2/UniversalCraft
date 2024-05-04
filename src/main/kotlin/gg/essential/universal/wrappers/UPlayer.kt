package gg.essential.universal.wrappers

import gg.essential.universal.UMinecraft
import gg.essential.universal.wrappers.message.UTextComponent
import net.minecraft.client.network.ClientPlayerEntity
import java.util.*

object UPlayer {
    @JvmStatic
    fun getPlayer(): ClientPlayerEntity? {
        return UMinecraft.getMinecraft().player
    }

    @JvmStatic
    fun hasPlayer() = getPlayer() != null

    @JvmStatic
    fun sendClientSideMessage(message: UTextComponent) {
        //#if MC>=11900
        getPlayer()!!.sendMessage(message)
        //#elseif MC>=11602
        //$$ getPlayer()!!.sendSystemMessage(message, null)
        //#elseif MC>=11202
        //$$ getPlayer()!!.sendMessage(message)
        //#else
        //$$ getPlayer()!!.addChatMessage(message)
        //#endif
    }

    @JvmStatic
    fun getUUID(): UUID {
        //#if MC>=12002
        return UMinecraft.getMinecraft().session.uuidOrNull!!
        //#else
        //$$ return UMinecraft.getMinecraft().session.profile.id
        //#endif
    }

    @JvmStatic
    fun getPosX(): Double {
        return getPlayer()?.x
            ?: throw NullPointerException("UPlayer.getPosX() called with no existing Player")
    }

    @JvmStatic
    fun getPosY(): Double {
        return getPlayer()?.y
            ?: throw NullPointerException("UPlayer.getPosY() called with no existing Player")
    }

    @JvmStatic
    fun getPosZ(): Double {
        return getPlayer()?.z
            ?: throw NullPointerException("UPlayer.getPosZ() called with no existing Player")
    }

    @JvmStatic
    fun getPrevPosX(): Double {
        return getPlayer()?.prevX
            ?: throw NullPointerException("UPlayer.getPrevPosX() called with no existing Player")
    }

    @JvmStatic
    fun getPrevPosY(): Double {
        return getPlayer()?.prevY
            ?: throw NullPointerException("UPlayer.getPrevPosY() called with no existing Player")
    }

    @JvmStatic
    fun getPrevPosZ(): Double {
        return getPlayer()?.prevZ
            ?: throw NullPointerException("UPlayer.getPrevPosZ() called with no existing Player")
    }
}
