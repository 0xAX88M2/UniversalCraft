package gg.essential.universal

import gg.essential.universal.shader.MCShader
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackResources
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.metadata.MetadataSectionSerializer
import net.minecraft.server.packs.resources.IoSupplier
import java.io.InputStream

/**
 * A dummy resource pack for use in [MCShader], since the [Resource] constructor
 * on 1.19.3+ requires a [ResourcePack] instead of a String name.
 */
internal object DummyPack : PackResources {
    override fun packId(): String = "__generated__"

    override fun close() {
        throw UnsupportedOperationException()
    }

    override fun getRootResource(vararg segments: String?): IoSupplier<InputStream>? {
        throw UnsupportedOperationException()
    }

    override fun getResource(type: PackType?, id: ResourceLocation?): IoSupplier<InputStream>? {
        throw UnsupportedOperationException()
    }

    override fun listResources(type: PackType?, namespace: String?, prefix: String?, consumer: PackResources.ResourceOutput?) {
        throw UnsupportedOperationException()
    }

    override fun getNamespaces(type: PackType?): MutableSet<String> {
        throw UnsupportedOperationException()
    }

    override fun <T : Any?> getMetadataSection(metaReader: MetadataSectionSerializer<T>?): T? {
        throw UnsupportedOperationException()
    }
}
