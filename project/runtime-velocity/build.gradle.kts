dependencies {
    compileOnly(project(":project:common"))
    compileOnly("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

taboolib { subproject = true }