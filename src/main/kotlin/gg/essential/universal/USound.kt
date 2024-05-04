package gg.essential.universal

import net.minecraft.client.resources.sounds.SimpleSoundInstance
import net.minecraft.core.Holder
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents


object USound {
    //#if MC>10809
    fun playSoundStatic(event: SoundEvent, volume: Float, pitch: Float) {
    //#else
    //$$ fun playSoundStatic(event: ResourceLocation, volume: Float, pitch: Float) {
        //#endif

        // sound handler can be null whenever switching devices or openal fails to
        // initialize the sound handler correctly, which is very common, so protect against that
        val soundHandler = UMinecraft.getMinecraft().soundManager ?: return

        SimpleSoundInstance.forUI(event, volume, pitch)?.let { soundHandler.play(it) }
    }

    //#if MC>=11903
    fun playSoundStatic(registryEntry: Holder<SoundEvent>, volume: Float, pitch: Float) {
        playSoundStatic(registryEntry.value(), volume, pitch)
    }
    //#endif

    @JvmOverloads
    fun playButtonPress(volume: Float = 0.25f) {
        //#if MC>10809
        playSoundStatic(SoundEvents.UI_BUTTON_CLICK, volume, 1.0f)
        //#else
        //$$ playSoundStatic(ResourceLocation("gui.button.press"), volume, 1.0F);
        //#endif
    }

    fun playExpSound() {
        //#if MC>10809
        playSoundStatic(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.25F, 1.0f)
        //#else
        //$$ playSoundStatic(ResourceLocation("random.orb"), 0.25F, 1.0F);
        //#endif
    }

    fun playLevelupSound() {
        //#if MC>10809
        playSoundStatic(SoundEvents.PLAYER_LEVELUP, 0.25F, 1.0f)
        //#else
        //$$ playSoundStatic(ResourceLocation("random.levelup"), 0.25F, 1.0F);
        //#endif
    }

    fun playPlingSound() {
        //#if MC>10809
        playSoundStatic(SoundEvents.NOTE_BLOCK_PLING, 0.25F, 1.0f)
        //#else
        //$$ playSoundStatic(ResourceLocation("note.pling"), 0.25F, 1.0F);
        //#endif
    }


}