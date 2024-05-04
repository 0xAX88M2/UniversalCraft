package gg.essential.universal.utils

typealias MCMinecraft = net.minecraft.client.MinecraftClient
typealias MCFontRenderer = net.minecraft.client.font.TextRenderer

typealias MCClickEventAction = net.minecraft.text.ClickEvent.Action
//#if MC>=11600
typealias MCHoverEventAction = net.minecraft.text.HoverEvent.Action<*>
//#else
//$$ typealias MCHoverEventAction = net.minecraft.util.text.event.HoverEvent.Action
//#endif
//#if MC>=11600
typealias MCIMutableText = net.minecraft.text.MutableText
//#else
//$$ typealias MCIMutableText = net.minecraft.util.text.ITextComponent
//#endif
typealias MCITextComponent = net.minecraft.text.Text
typealias MCClickEvent = net.minecraft.text.ClickEvent
typealias MCHoverEvent = net.minecraft.text.HoverEvent

typealias MCSettings = net.minecraft.client.option.GameOptions
typealias MCWorld = net.minecraft.client.world.ClientWorld
typealias MCEntityPlayerSP = net.minecraft.client.network.ClientPlayerEntity
typealias MCScreen = net.minecraft.client.gui.screen.Screen
typealias MCChatScreen = net.minecraft.client.gui.hud.ChatHud
typealias MCMainMenuScreen = net.minecraft.client.gui.screen.TitleScreen
typealias MCClientNetworkHandler = net.minecraft.client.network.ClientPlayNetworkHandler

//#if MC>=11502
typealias MCButton = net.minecraft.client.gui.widget.ButtonWidget
//#else
//$$ typealias MCButton = net.minecraft.client.gui.GuiButton
//#endif

typealias MCStringTextComponent = net.minecraft.text.PlainTextContent.Literal
typealias MCSChatPacket = net.minecraft.network.packet.s2c.play.GameMessageS2CPacket
