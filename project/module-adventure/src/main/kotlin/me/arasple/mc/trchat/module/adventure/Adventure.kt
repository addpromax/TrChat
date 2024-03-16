package me.arasple.mc.trchat.module.adventure

import me.arasple.mc.trchat.api.nms.NMS
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.inventory.ItemStack
import taboolib.library.reflex.Reflex.Companion.invokeMethod
import taboolib.module.chat.ComponentText
import taboolib.module.chat.Components
import taboolib.module.nms.Packet

private val legacySerializer: Any? = try {
    LegacyComponentSerializer.legacySection()
} catch (_: Throwable) {
    null
}

private val gsonSerializer: Any? = try {
    GsonComponentSerializer.gson()
} catch (_: Throwable) {
    null
}

fun gson(component: Component) = (gsonSerializer as GsonComponentSerializer).serialize(component)

fun gson(string: String) = (gsonSerializer as GsonComponentSerializer).deserialize(string)

fun Component.toNative() = Components.parseRaw(gson(this))

fun ComponentText.toAdventure() = gson(toRawMessage())

fun ItemStack.toTranslatableComponentAdventure(): ComponentText {
    return Component.translatable(this).toNative()
}

fun ComponentText.hoverItemAdventure(item: ItemStack): ComponentText {
    return toAdventure().hoverEvent(item).toNative()
}

fun Packet.getComponent(): ComponentText? {
    return when (name) {
        "ClientboundSystemChatPacket" -> {
            val iChat = source.invokeMethod<Any>("a", findToParent = false, remap = false) ?: return null
            Components.parseRaw(NMS.instance.rawMessageFromCraftChatMessage(iChat))
        }
        "PacketPlayOutChat" -> {
            val iChat = read<Any>("a") ?: return null
            Components.parseRaw(NMS.instance.rawMessageFromCraftChatMessage(iChat))
        }
        else -> error("Unsupported packet $name")
    }
}