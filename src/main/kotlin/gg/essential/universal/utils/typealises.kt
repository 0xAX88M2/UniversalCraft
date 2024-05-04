package gg.essential.universal.utils

typealias MCMinecraft = net.minecraft.client.Minecraft
typealias MCFontRenderer = net.minecraft.client.gui.Font

typealias MCClickEventAction = net.minecraft.network.chat.ClickEvent.Action
//#if MC>=11600
typealias MCHoverEventAction = net.minecraft.network.chat.HoverEvent.Action<*>
//#else
//$$ typealias MCHoverEventAction = net.minecraft.util.text.event.HoverEvent.Action
//#endif
//#if MC>=11600
typealias MCIMutableText = net.minecraft.network.chat.MutableComponent
//#else
//$$ typealias MCIMutableText = net.minecraft.util.text.ITextComponent
//#endif
typealias MCITextComponent = net.minecraft.network.chat.Component
typealias MCClickEvent = net.minecraft.network.chat.ClickEvent
typealias MCHoverEvent = net.minecraft.network.chat.HoverEvent

typealias MCSettings = net.minecraft.client.Options
typealias MCWorld = net.minecraft.client.multiplayer.ClientLevel
typealias MCEntityPlayerSP = net.minecraft.client.player.LocalPlayer
typealias MCScreen = net.minecraft.client.gui.screens.Screen
typealias MCChatScreen = net.minecraft.client.gui.components.ChatComponent
typealias MCMainMenuScreen = net.minecraft.client.gui.screens.TitleScreen
typealias MCClientNetworkHandler = net.minecraft.client.multiplayer.ClientPacketListener

//#if MC>=11502
typealias MCButton = net.minecraft.client.gui.components.Button
//#else
//$$ typealias MCButton = net.minecraft.client.gui.GuiButton
//#endif

typealias MCStringTextComponent = net.minecraft.network.chat.contents.PlainTextContents.LiteralContents
typealias MCSChatPacket = net.minecraft.network.protocol.game.ClientboundSystemChatPacket
