package me.arasple.mc.trchat.util.color

import taboolib.module.nms.MinecraftVersion

fun isHigherOrEqual11600() = MinecraftVersion.isHigherOrEqual(MinecraftVersion.V1_16)

fun String.colorify() = HexUtils.colorify(this)

fun String.parseLegacy() = HexUtils.parseLegacy(this)

fun String.parseHex() = HexUtils.parseHex(this)

fun String.parseRainbow() = HexUtils.parseRainbow(this)

fun String.parseGradients() = HexUtils.parseGradients(this)