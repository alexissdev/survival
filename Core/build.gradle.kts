plugins {
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {
    implementation(project(":api"))
    arrayOf(
        "me.fixeddev:commandflow-bukkit:0.5.2",
        "team.unnamed:gui-menu-api:3.4.0-20221007.211619-1",
        "com.github.ben-manes.caffeine:caffeine:2.8.6",
    ).forEach {
        implementation(it)
    }
}

bukkit {
    main = "dev.notcacha.survival.core.Survival"
    name = "survival"
    author = "AlexisDev"
    description = "Simple survival core."
    version = project.version.toString()
}

tasks {
    shadowJar {
        archiveFileName.set("survival-${project.version}.jar")

        val pkg = "us.mineaqua.fullpvp.lib"
        relocate("me.yushust.message", "$pkg.message")
        relocate("team.unnamed.inject", "$pkg.inject")
        relocate("me.fixeddev.commandflow", "$pkg.command")
        relocate("team.unnamed.gui", "$pkg.gui")
        relocate("fr.mrmicky", "$pkg.board")
    }
}