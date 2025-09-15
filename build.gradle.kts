plugins {
    id("fabric-loom") version "1.11-SNAPSHOT"
}

base {
    archivesName = properties["archives_base_name"] as String
    version = properties["mod_version"] as String
    group = properties["maven_group"] as String
}

repositories {
    maven {
        name = "meteor-maven"
        url = uri("https://maven.meteordev.org/releases")
    }
    maven {
        name = "meteor-maven-snapshots"
        url = uri("https://maven.meteordev.org/snapshots")
    }
    maven {
        name = "jitpack"
        url = uri("https://jitpack.io")
    }
    maven {
        name = "modrinth"
        url = uri("https://api.modrinth.com/maven")
    }
}

dependencies {
    // Fabric
    minecraft("com.mojang:minecraft:${properties["minecraft_version"] as String}")
    mappings("net.fabricmc:yarn:${properties["yarn_mappings"] as String}:v2")
    modImplementation("net.fabricmc:fabric-loader:${properties["loader_version"] as String}")

    // Meteor
    modImplementation("meteordevelopment:meteor-client:${properties["minecraft_version"] as String}-SNAPSHOT")

    // Litematica
    modImplementation("com.github.sakura-ryoko:litematica:${properties["minecraft_version"] as String}-${properties["litematica_version"] as String}")
    modImplementation("com.github.sakura-ryoko:malilib:${properties["minecraft_version"] as String}-${properties["malilib_version"] as String}")

    // XaeroPlus
    modImplementation("maven.modrinth:xaeroplus:${project.properties["xaeroplus_version"] as String}")

    // XaeroWorldMap
    modImplementation("maven.modrinth:xaeros-world-map:${project.properties["xaeros_worldmap_version"] as String}")

    // XaeroMinimap
    modImplementation("maven.modrinth:xaeros-minimap:${project.properties["xaeros_minimap_version"] as String}")

    // lenni
    modImplementation("net.lenni0451:LambdaEvents:2.4.2")
    modImplementation("com.github.ben-manes.caffeine:caffeine:3.1.8")

    modCompileOnly("meteordevelopment:baritone:${project.properties["baritone_version"] as String}")
}

tasks {
    processResources {
        val propertyMap = mapOf(
            "version" to project.version,
            "mc_version" to project.property("minecraft_version"),
        )

        inputs.properties(propertyMap)

        filteringCharset = "UTF-8"

        filesMatching("fabric.mod.json") {
            expand(propertyMap)
        }
    }

    jar {
        inputs.property("archivesName", project.base.archivesName.get())

        from("LICENSE") {
            rename { "${it}_${inputs.properties["archivesName"]}" }
        }
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release = 21
        options.compilerArgs.add("-Xlint:deprecation")
        options.compilerArgs.add("-Xlint:unchecked")
    }
}
