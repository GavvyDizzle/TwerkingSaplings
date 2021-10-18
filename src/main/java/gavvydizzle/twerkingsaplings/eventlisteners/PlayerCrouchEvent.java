package gavvydizzle.twerkingsaplings.eventlisteners;

import gavvydizzle.twerkingsaplings.TwerkingSaplings;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.ArrayList;

public class PlayerCrouchEvent implements Listener {

    private final int verticalSize = TwerkingSaplings.instance.getConfig().getInt("vertical-distance");
    private final int yOffset = TwerkingSaplings.instance.getConfig().getInt("y-offset");
    private final int horizontalSize = TwerkingSaplings.instance.getConfig().getInt("horizontal-distance");
    private final double crouchesPerTree = TwerkingSaplings.instance.getConfig().getDouble("crouches");
    private final ArrayList<String> saplingsArr = (ArrayList<String>) TwerkingSaplings.instance.getConfig().getStringList("saplings");

    @EventHandler
    public void onPlayerCrouchEvent(PlayerToggleSneakEvent e) {

        Player p = e.getPlayer();

        if (!p.isSneaking()) {
            return;
        }

        Location playerLoc = p.getLocation();

        for (int y = yOffset; y < yOffset + verticalSize; y++) {
            for (int x = -horizontalSize/2; x <= horizontalSize/2; x++) {
                for (int z = -horizontalSize/2; z <= horizontalSize/2; z++) {

                    Location blockLoc = new Location(playerLoc.getWorld(), x + playerLoc.getBlockX(), y + playerLoc.getBlockY(), z + playerLoc.getBlockZ());
                    BlockData sapling = blockLoc.getBlock().getBlockData();

                    if (saplingsArr.contains(sapling.getMaterial().toString())) {

                        if (sapling.getMaterial().toString().equals("CRIMSON_FUNGUS") || sapling.getMaterial().toString().equals("WARPED_FUNGUS")) {
                            if (Math.random() <= 1 / (Math.max(1, crouchesPerTree) / 2.5)) {
                                blockLoc.getBlock().applyBoneMeal(BlockFace.DOWN);
                            }
                        }
                        else {
                            if (Math.random() <= 1 / (Math.max(1, crouchesPerTree) / 4.5)) {
                                blockLoc.getBlock().applyBoneMeal(BlockFace.DOWN);
                            }
                        }
                    }
                }
            }
        }
    }
}
