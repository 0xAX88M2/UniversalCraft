package gg.essential.universal

import gg.essential.universal.wrappers.message.UTextComponent

//#if MC>=11602
import gg.essential.universal.wrappers.UPlayer
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket

//#endif

//#if MC>=11901
//#else
//#if MC>=11900
//$$ // FIXME preprocessor bug: remaps alias references in code to remapped type, so we can't keep the alias
//#if FABRIC
//$$ import net.minecraft.network.message.MessageType as MCMessageType
//#else
//$$ import net.minecraft.network.chat.ChatType
//#endif
//$$ import net.minecraft.util.registry.Registry
//$$ import net.minecraft.util.registry.RegistryKey
//$$
//$$ private object MessageType {
//$$     private fun get(key: RegistryKey<MCMessageType>): Int {
//$$         val registry = UMinecraft.getNetHandler()!!.registryManager.get(Registry.MESSAGE_TYPE_KEY)
//$$         return registry.getRawId(registry.get(key))
//$$     }
//$$     val CHAT: Int
//$$         get() = get(MCMessageType.CHAT)
//$$     val GAME_INFO: Int
//$$         get() = get(MCMessageType.GAME_INFO)
//$$ }
//#elseif MC>=11202
//$$ import net.minecraft.network.MessageType
//$$
//$$ private object ChatType {
//$$     const val CHAT: Byte = 0
//$$     const val GAME_INFO: Byte = 2
//$$ }
//#endif
//#endif

object UPacket {
    @JvmStatic
    fun sendChatMessage(message: UTextComponent) {
        UMinecraft.getNetHandler()!!.handleSystemChat(ClientboundSystemChatPacket(
            message,
            //#if MC>=11901
            false,
            //#else
            //$$ MessageType.CHAT,
            //#endif
            //#if MC>=11600 && MC<11900
            //$$ UPlayer.getUUID(),
            //#endif
        ))
    }
    
    @JvmStatic
    fun sendActionBarMessage(message: UTextComponent) {
        UMinecraft.getNetHandler()!!.handleSystemChat(
            ClientboundSystemChatPacket(
            message,
            //#if MC>=11901
            true,
            //#else
            //$$ MessageType.GAME_INFO,
            //#endif
            //#if MC>=11600 && MC<11900
            //$$ UPlayer.getUUID(),
            //#endif
        )
        )
    }
}
