@file:Suppress("Deprecation")

package me.arasple.mc.trchat.module.internal.listener

import me.arasple.mc.trchat.module.display.channel.Channel
import me.arasple.mc.trchat.module.internal.TrChatBukkit
import me.arasple.mc.trchat.util.session
import org.bukkit.event.player.AsyncPlayerChatEvent
import taboolib.common.platform.Platform
import taboolib.common.platform.PlatformSide
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.configuration.ConfigNode

/**
 * @author ItsFlicker
 * @date 2019/11/30 12:10
 */
@PlatformSide(Platform.BUKKIT)
object ListenerBukkitChat {

    @ConfigNode("Options.Always-Cancel-Chat-Event", "settings.yml")
    var cancelEvent = false
        private set

    @SubscribeEvent(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun onBukkitChat(e: AsyncPlayerChatEvent) {
        if (e.isCancelled) return
        if (cancelEvent) {
            e.isCancelled = true
        } else {
            e.recipients.clear()
        }
        val player = e.player
        val session = player.session

        Channel.channels.values.forEach { channel ->
            channel.bindings.prefix?.forEach {
                if (e.message.startsWith(it, ignoreCase = true)) {
                    if (channel.settings.isPrivate) e.isCancelled = true
                    channel.execute(player, e.message.substring(it.length), TrChatBukkit.isPaperEnv || cancelEvent)
                    return
                }
            }
        }
        session.getChannel()?.let {
            if (it.settings.isPrivate) e.isCancelled = true
            it.execute(player, e.message, TrChatBukkit.isPaperEnv || cancelEvent)
        }
    }

}