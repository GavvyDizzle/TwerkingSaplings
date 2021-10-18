package gavvydizzle.twerkingsaplings;

import gavvydizzle.twerkingsaplings.commands.ReloadConfig;
import gavvydizzle.twerkingsaplings.eventlisteners.PlayerCrouchEvent;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class TwerkingSaplings extends JavaPlugin {

    public static TwerkingSaplings instance;

    @Override
    public void onEnable() {

        instance = this;

        reloadConfig();

        Objects.requireNonNull(getCommand("twerkingsaplings")).setExecutor(new ReloadConfig());
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();

        saveDefaultConfig();

        String[] saplingsArr = {"OAK_SAPLING", "BIRCH_SAPLING", "SPRUCE_SAPLING", "DARK_OAK_SAPLING",
                "ACACIA_SAPLING", "JUNGLE_SAPLING", "CRIMSON_FUNGUS", "WARPED_FUNGUS"};

        getConfig().addDefault("vertical-distance", 1);
        getConfig().addDefault("y-offset", 0);
        getConfig().addDefault("horizontal-distance", 3);
        getConfig().addDefault("crouches", 50);
        getConfig().addDefault("saplings", saplingsArr);

        getConfig().options().copyDefaults(true);
        saveConfig();

        HandlerList.unregisterAll();
        getServer().getPluginManager().registerEvents(new PlayerCrouchEvent(), this);
    }
}
