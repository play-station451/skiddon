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
import net.minecraft.item.Items;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.slf4j.Logger;

public class Skiddon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Skiddon", Items.NETHER_STAR.getDefaultStack());
    public static final HudGroup HUD_GROUP = new HudGroup("Skiddon");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Skiddon");

        // Modules
        Modules.get().add(new AFKVanillaFly());
        Modules.get().add(new AutoLavaCaster());
        Modules.get().add(new AutoMountain());
        Modules.get().add(new AutoTPA());
        Modules.get().add(new BaseFinder());
        Modules.get().add(new BetterAutoSign());
        Modules.get().add(new BungeeSpoofer());
        Modules.get().add(new FlightAntikick());
        Modules.get().add(new LavaAura());
        Modules.get().add(new LitematicaPrinter());
        Modules.get().add(new Pitch40Util());
        Modules.get().add(new TPFly());
        Modules.get().add(new TrailFollower());
        Modules.get().add(new TrailMaker());
        Modules.get().add(new VillagerRoller());

        // Commands
        Commands.add(new CommandExample());

        // HUD
        Hud.get().register(HudExample.INFO);

        boolean baritoneLoaded = checkModLoaded("baritone", "baritone-meteor");
        boolean xaeroWorldMapLoaded = checkModLoaded("xaeroworldmap");
        boolean xaeroMinimapLoaded = checkModLoaded("xaerominimap");
        boolean xaeroPlusLoaded = checkModLoaded("xaeroplus");

        if (xaeroWorldMapLoaded && xaeroPlusLoaded)
        {
            if (xaeroMinimapLoaded)
            {
                Modules.get().add(new TrailMaker());
            }
            if (baritoneLoaded)
            {
                Modules.get().add(new TrailFollower());
            }
        }
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

    private boolean checkModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    private boolean checkModLoaded(String modid1, String modid2) {
        return FabricLoader.getInstance().isModLoaded(modid1) || FabricLoader.getInstance().isModLoaded(modid2);
    }
}
