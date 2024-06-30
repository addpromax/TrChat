package me.arasple.mc.trchat.api.nms

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import taboolib.common.platform.function.adaptPlayer
import taboolib.module.chat.ComponentText
import taboolib.module.nms.LocaleKey
import java.util.*

class NMSImpl12100 : NMS() {

    override fun craftChatMessageFromComponent(component: ComponentText): Any {
        TODO("Not yet implemented")
    }

    override fun rawMessageFromCraftChatMessage(component: Any): String {
        TODO("Not yet implemented")
    }

    override fun sendMessage(receiver: Player, component: ComponentText, sender: UUID?, usePacket: Boolean) {
        component.sendTo(adaptPlayer(receiver))
    }

    override fun hoverItem(component: ComponentText, itemStack: ItemStack): ComponentText {
        TODO("Not yet implemented")
    }

    override fun optimizeNBT(itemStack: ItemStack, nbtWhitelist: Array<String>): ItemStack {
        TODO("Not yet implemented")
    }

    override fun getLocaleKey(itemStack: ItemStack): LocaleKey {
        TODO("Not yet implemented")
    }
}