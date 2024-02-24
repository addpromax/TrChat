repositories {
    maven("https://nexus.scarsz.me/content/groups/public/")
}

dependencies {
    compileOnly(project(":project:common"))
    compileOnly(project(":project:module-adventure"))
    compileOnly(project(":project:module-compat"))
    compileOnly(project(":project:module-nms"))
    compileOnly("ink.ptms.core:v12004:12004:universal")
    compileOnly("net.md-5:bungeecord-api:1.20-R0.1-SNAPSHOT")
    compileOnly(fileTree("libs"))

    compileOnly("me.clip:placeholderapi:2.11.5") { isTransitive = false }
    compileOnly("com.discordsrv:discordsrv:1.26.0") { isTransitive = false }
}

taboolib { subproject = true }