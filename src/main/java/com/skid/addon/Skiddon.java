package com.skid.addon;

import com.skid.addon.commands.CommandExample;
import com.skid.addon.hud.HudExample;
import com.skid.addon.modules.*;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.commands.Commands;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Skiddon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Skiddon");
    public static final HudGroup HUD_GROUP = new HudGroup("Skiddon");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Skiddon");

        // Modules
        Modules.get().add(new AFKVanillaFly());
        Modules.get().add(new AutoTPA());
        Modules.get().add(new LitematicaPrinter());
        Modules.get().add(new Pitch40Util());
        Modules.get().add(new TrailFollower());
        Modules.get().add(new TrailMaker());
        Modules.get().add(new VillagerRoller());

        // Commands
        Commands.add(new CommandExample());

        // HUD
        Hud.get().register(HudExample.INFO);
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "com.skid.addon";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("play-station451", "Skiddon");
    }
}
