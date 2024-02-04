package me.arasple.mc.trchat.module.internal

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import taboolib.common.platform.ProxyCommandSender
import taboolib.platform.VelocityPlugin

class VelocityConsole : ProxyCommandSender {

    private val sender = TrChatVelocity.plugin.server.consoleCommandSource

    override val origin: Any
        get() = sender

    override val name: String
        get() = "console"

    override var isOp: Boolean
        get() = error("unsupported")
        set(_) {
            error("unsupported")
        }

    override fun isOnline(): Boolean {
        return true
    }

    // TrPlugins/TrChat#350
    override fun sendMessage(message: String) {
        sender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(message))
    }

    override fun performCommand(command: String): Boolean {
        VelocityPlugin.getInstance().server.commandManager.executeAsync(sender, command)
        return true
    }

    override fun hasPermission(permission: String): Boolean {
        return sender.hasPermission(permission)
    }

}