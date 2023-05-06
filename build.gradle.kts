plugins {
    id("java")
    id("java-library")
}

subprojects {
    apply(plugin = "java-library")

    repositories {
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://repo.unnamed.team/repository/unnamed-public/")
        mavenLocal()
    }

    dependencies {
        api("org.jetbrains:annotations:22.0.0")
        compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")

        arrayOf(
            "com.fasterxml.jackson.core:jackson-core:2.12.1",
            "com.fasterxml.jackson.core:jackson-annotations:2.12.1",
            "team.unnamed:inject:1.0.1",
            "me.yushust.message:core:7.0.0",
            "me.yushust.message:sourcetype-bukkit-yml:7.0.0"
        ).forEach {
            implementation(it)
        }
    }
}