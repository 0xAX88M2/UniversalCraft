
package gg.essential.universal.wrappers.message

import gg.essential.universal.UChat
import gg.essential.universal.utils.*
import net.minecraft.text.ClickEvent
import net.minecraft.text.HoverEvent
import net.minecraft.util.Formatting
import net.minecraft.text.Text
import net.minecraft.text.Style

//#if MC>=11900
import net.minecraft.text.TextContent
//#else
//$$ import net.minecraft.text.LiteralText
//#endif

//#if MC>=11602
import net.minecraft.text.OrderedText
import net.minecraft.text.MutableText
import net.minecraft.text.CharacterVisitor
import net.minecraft.text.TextColor
import net.minecraft.text.StringVisitable.StyledVisitor
import net.minecraft.text.StringVisitable.Visitor
import java.util.Optional
//#if MC<11900
//$$ import java.util.function.UnaryOperator
//#endif
//#endif

//#if MC==11502
//$$ import java.util.stream.Stream
//$$ import java.util.function.Consumer
//#endif

@Suppress("MemberVisibilityCanBePrivate")
//#if MC>=11900
class UTextComponent : Text {
    lateinit var component: MutableText
//#elseif MC>=11600
//$$ class UTextComponent : MutableText {
//$$     lateinit var component: MutableText
//#else
//$$ class UTextComponent : ITextComponent {
//$$     lateinit var component: ITextComponent
//#endif
        private set
    var text: String
        set(value) {
            field = value
            reInstance()
        }
    var formatted = true
        set(value) {
            field = value
            reInstance()
        }

    var clickAction: MCClickEventAction? = null
        set(value) {
            field = value
            reInstance()
        }
    var clickValue: String? = null
        set(value) {
            field = value
            reInstance()
        }
    var hoverAction: MCHoverEventAction? = null
        set(value) {
            field = value
            reInstance()
        }
    var hoverValue: Any? = null
        set(value) {
            field = value
            reInstance()
        }

    constructor(text: String) {
        this.text = text
        reInstance()
    }

    //#if MC>=11600
    constructor(component: Text) : this(component.copy())
    constructor(component: MutableText) {
    //#else
    //$$ constructor(component: ITextComponent) {
    //#endif
        this.component = component

        //#if MC>=11602
        text = formattedText
        //#else
        //$$ text = component.formattedText
        //#endif

        //#if MC>=11202
        val clickEvent = component.style.clickEvent
        //#else
        //$$ val clickEvent = component.chatStyle.chatClickEvent
        //#endif

        if (clickEvent != null) {
            clickAction = clickEvent.action
            clickValue = clickEvent.value
        }

        //#if MC>=11202
        val hoverEvent = component.style.hoverEvent
        //#else
        //$$ val hoverEvent = component.chatStyle.chatHoverEvent
        //#endif

        if (hoverEvent != null) {
            hoverAction = hoverEvent.action

            //#if MC>=11602
            hoverValue = hoverEvent.getValue(hoverAction)
            //#else
            //$$ hoverValue = hoverEvent.value
            //#endif
        }
    }

    fun setClick(action: MCClickEventAction, value: String) = apply {
        clickAction = action
        clickValue = value
        reInstance()
    }

    fun setHover(action: MCHoverEventAction, value: Any) = apply {
        hoverAction = action
        hoverValue = value
        reInstance()
    }

    fun chat() {
        UMessage(this).chat()
    }

    fun actionBar() {
        UMessage(this).actionBar()
    }

    private fun reInstance() {
        //#if MC>=11900
        component = Text.literal(text.formatIf(formatted))
        //#else
        //$$ component = LiteralText(text.formatIf(formatted))
        //#endif

        reInstanceClick()
        reInstanceHover()
    }

    private fun reInstanceClick() {
        if (clickAction == null || clickValue == null)
            return

        val event = ClickEvent(clickAction, clickValue!!.formatIf(formatted))

        //#if MC>=11600
        component.style = component.style.withClickEvent(event)
        //#elseif MC>=11202
        //$$ component.style.clickEvent = event
        //#else
        //$$ component.chatStyle.chatClickEvent = event
        //#endif
    }

    private fun reInstanceHover() {
        if (hoverAction == null || hoverValue == null)
            return

        //#if MC>=11602
        val event = HoverEvent<Any>(hoverAction as HoverEvent.Action<Any>, hoverValue!!)
        setHoverEventHelper(event)
        //#else
        //$$ val value: ITextComponent = when (hoverValue) {
        //$$     is String -> TextComponentString(hoverValue as String)
        //$$     is UTextComponent -> (hoverValue as UTextComponent).component
        //$$     is ITextComponent -> hoverValue as ITextComponent
        //$$     else -> TextComponentString(hoverValue.toString())
        //$$ }
        //$$ setHoverEventHelper(HoverEvent(
        //$$     hoverAction,
        //$$     value
        //$$ ))
        //#endif
    }

    private fun setHoverEventHelper(event: HoverEvent) {
        //#if MC>=11600
        component.style = component.style.withHoverEvent(event)
        //#elseif MC>=11202
        //$$ component.style.hoverEvent = event
        //#else
        //$$ component.chatStyle.chatHoverEvent = event
        //#endif
    }

    private fun String.formatIf(predicate: Boolean) = if (predicate) UChat.addColor(this) else this

    //#if MC>=11602
    private class TextBuilder(private val isFormatted: Boolean) : CharacterVisitor {
        private val builder = StringBuilder()
        private var cachedStyle: Style? = null

        override fun accept(index: Int, style: Style, codePoint: Int): Boolean  {
            if (isFormatted && style != cachedStyle) {
                cachedStyle = style
                builder.append(formatString(style))
            }

            builder.append(codePoint.toChar())
            return true
        }

        fun getString() = builder.toString()

        private fun formatString(style: Style): String {
            val builder = StringBuilder("§r")

            when {
                style.isBold -> builder.append("§l")
                style.isItalic -> builder.append("§o")
                style.isUnderlined -> builder.append("§n")
                style.isStrikethrough -> builder.append("§m")
                style.isObfuscated -> builder.append("§k")
            }

            style.color?.let(colorToFormatChar::get)?.let {
                builder.append(it)
            }
            return builder.toString()
        }

        companion object {
            private val colorToFormatChar = Formatting.values().mapNotNull { format ->
                TextColor.fromFormatting(format)?.let { it to format }
            }.toMap()
        }
    }
    //#endif

    // **********************
    // * METHOD DELEGATIONS *
    // **********************

    //#if MC>=11900
    override fun getContent(): TextContent = component.content
    //#endif

    //#if MC>=11602
    val unformattedText: String get() {
        val builder = TextBuilder(false)
        component.asOrderedText().accept(builder)
        return builder.getString()
    }

    val formattedText: String get() {
        val builder = TextBuilder(true)
        component.asOrderedText().accept(builder)
        return builder.getString()
    }

    fun appendSibling(text: Text): MutableText = component.append(text)

    //#if MC<11900
    //$$ override fun setStyle(style: Style): MutableText = component.setStyle(style)
    //$$
    //$$ override fun append(sibling: Text): MutableText = component.append(sibling)
    //$$
    //$$ override fun append(string: String): MutableText = component.append(string)
    //$$
    //$$ override fun styled(func: UnaryOperator<Style>): MutableText = component.styled(func)
    //$$
    //$$ override fun fillStyle(style: Style): MutableText = component.fillStyle(style)
    //$$
    //$$ override fun formatted(vararg formats: Formatting): MutableText = component.formatted(*formats)
    //$$
    //$$ override fun formatted(format: Formatting): MutableText = component.formatted(format)
    //#endif

    override fun getString(): String = component.string

    override fun asTruncatedString(maxLen: Int): String = component.asTruncatedString(maxLen)

    override fun <T> visit(p_230439_1_: StyledVisitor<T>, p_230439_2_: Style): Optional<T> {
        return component.visit(p_230439_1_, p_230439_2_)
    }

    override fun <T> visit(p_230438_1_: Visitor<T>): Optional<T> {
        return component.visit(p_230438_1_)
    }

    //#if MC<11900
    //$$ override fun <T> visitSelf(p_230534_1_: StyledVisitor<T>, p_230534_2_: Style): Optional<T> {
    //$$     return component.visitSelf(p_230534_1_, p_230534_2_)
    //$$ }
    //$$
    //$$ override fun <T> visitSelf(p_230533_1_: Visitor<T>): Optional<T> {
    //$$     return component.visitSelf(p_230533_1_)
    //$$ }
    //#endif

    override fun getStyle(): Style = component.style

    //#if MC<11900
    //$$ override fun asString(): String = component.asString()
    //#endif

    override fun getSiblings(): MutableList<Text> = component.siblings

    override fun copyContentOnly(): MutableText = component.copyContentOnly()

    override fun copy(): MutableText = component.copy()

    override fun asOrderedText(): OrderedText = component.asOrderedText()
    //#elseif MC>=11502
    //$$ val unformattedText: String get() = getUnformattedComponentText()
    //$$
    //$$ override fun appendText(text: String): ITextComponent = component.appendText(text)
    //$$
    //$$ override fun getString(): String = component.string
    //$$
    //$$ override fun getStringTruncated(maxLen: Int): String = component.getStringTruncated(maxLen)
    //$$
    //$$ override fun getFormattedText(): String = component.formattedText
    //$$
    //$$ override fun func_212637_f(): Stream<ITextComponent> = component.func_212637_f()
    //$$
    //$$ override fun iterator(): MutableIterator<ITextComponent> = component.iterator()
    //$$
    //$$ override fun deepCopy(): ITextComponent = component.deepCopy()
    //$$
    //$$ override fun applyTextStyle(styleConsumer: Consumer<Style>): ITextComponent {
    //$$     return component.applyTextStyle(styleConsumer)
    //$$ }
    //$$
    //$$ override fun applyTextStyles(vararg colors: TextFormatting): ITextComponent {
    //$$     return component.applyTextStyles(*colors)
    //$$ }
    //$$
    //$$ override fun applyTextStyle(color: TextFormatting): ITextComponent {
    //$$     return component.applyTextStyle(color)
    //$$ }
    //$$
    //$$ override fun setStyle(style: Style): ITextComponent {
    //$$     return component.setStyle(style)
    //$$ }
    //$$
    //$$ override fun getStyle(): Style = component.style
    //$$
    //$$ override fun appendSibling(other: ITextComponent): ITextComponent = component.appendSibling(other)
    //$$
    //$$ override fun getUnformattedComponentText(): String = component.unformattedComponentText
    //$$
    //$$ override fun getSiblings(): MutableList<ITextComponent> = component.siblings
    //$$
    //$$ override fun stream(): Stream<ITextComponent> = component.stream()
    //$$
    //$$ override fun shallowCopy(): ITextComponent = component.shallowCopy()
    //#elseif MC>=11202
    //$$ override fun setStyle(style: Style): ITextComponent = component.setStyle(style)
    //$$
    //$$ override fun getStyle(): Style = component.style
    //$$
    //$$ override fun appendText(text: String): ITextComponent = component.appendText(text)
    //$$
    //$$ override fun appendSibling(other: ITextComponent): ITextComponent = component.appendSibling(other)
    //$$
    //$$ override fun getUnformattedComponentText(): String = component.unformattedComponentText
    //$$
    //$$ override fun getUnformattedText(): String = component.unformattedText
    //$$
    //$$ override fun getFormattedText(): String = component.formattedText
    //$$
    //$$ override fun getSiblings(): MutableList<ITextComponent> = component.siblings
    //$$
    //$$ override fun createCopy(): ITextComponent = component.createCopy()
    //$$
    //$$ override fun iterator(): MutableIterator<ITextComponent> = component.iterator()
    //#else
    //$$ override fun setChatStyle(style: ChatStyle): IChatComponent = component.setChatStyle(style)
    //$$
    //$$ override fun getChatStyle(): ChatStyle = component.chatStyle
    //$$
    //$$ override fun appendText(text: String): IChatComponent = component.appendText(text)
    //$$
    //$$ override fun appendSibling(other: IChatComponent): IChatComponent = component.appendSibling(other)
    //$$
    //$$ override fun getUnformattedTextForChat(): String = component.unformattedTextForChat
    //$$
    //$$ override fun getUnformattedText(): String = component.unformattedText
    //$$
    //$$ override fun getFormattedText(): String = component.formattedText
    //$$
    //$$ override fun getSiblings(): MutableList<IChatComponent> = component.siblings
    //$$
    //$$ override fun createCopy(): IChatComponent = component.createCopy()
    //$$
    //$$ override fun iterator(): MutableIterator<IChatComponent> = component.iterator()
    //#endif

    companion object {
        fun from(obj: Any): UTextComponent? {
            return when (obj) {
                is UTextComponent -> obj
                is String -> UTextComponent(obj)
                is Text -> UTextComponent(obj)
                else -> null
            }
        }

        fun stripFormatting(string: String): String {
            //#if MC>=11202
            return Formatting.strip(string)!!
            //#else
            //$$ return EnumChatFormatting.getTextWithoutFormattingCodes(string)
            //#endif
        }
    }
}
