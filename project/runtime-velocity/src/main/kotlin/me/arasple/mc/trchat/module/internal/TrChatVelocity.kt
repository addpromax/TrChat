package me.arasple.mc.trchat.module.internal

import me.arasple.mc.trchat.api.impl.VelocityProxyManager
import taboolib.common.platform.Platform
import taboolib.common.platform.PlatformSide
import taboolib.common.platform.Plugin
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.command
import taboolib.common.platform.command.suggest
import taboolib.common.platform.function.pluginVersion
import taboolib.common.util.unsafeLazy
import taboolib.module.lang.sendLang
import taboolib.platform.VelocityPlugin

/**
 * TrChatVelocity
 * me.arasple.mc.trchat
 *
 * @author ItsFlicker
 * @since 2021/8/21 13:42
 */
@PlatformSide(Platform.VELOCITY)
object TrChatVelocity : Plugin() {

    val plugin by unsafeLazy { VelocityPlugin.getInstance() }

    override fun onLoad() {
        plugin.server.channelRegistrar.register(VelocityProxyManager.incoming, VelocityProxyManager.outgoing)
        VelocityConsole().sendLang("Plugin-Loading", plugin.server.version.version)
        VelocityConsole().sendLang("Plugin-Proxy-Supported", "Velocity")
    }

    override fun onEnable() {
        command("muteallservers", permission = "trchatv.muteallservers") {
            dynamic("state") {
                suggest {
                    listOf("on", "off")
                }
                execute<ProxyCommandSender> { sender, _, argument ->
                    if (!sender.hasPermission("trchatv.muteallservers")) return@execute
                    VelocityProxyManager.sendMessageToAll("GlobalMute", argument)
                }
            }
        }
        VelocityConsole().sendLang("Plugin-Enabled", pluginVersion)
    }

}