package net.kunmc.lab.killerqueen.listener;

import net.kunmc.lab.killerqueen.KillerQueenWerewolf;
import net.kunmc.lab.killerqueen.util.BlastType;
import net.kunmc.lab.killerqueen.util.BombManager;
import net.kunmc.lab.killerqueen.util.StandUserManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class StandListener implements Listener {

    private StandUserManager standUserManager = StandUserManager.getInstance();
    private BombManager bombManager = BombManager.getInstance();

    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        KillerQueenWerewolf.plugin.getServer().getLogger().info("イベントリスナー");
        System.out.println("イベントリスナー");
        if(!standUserManager.isKillerQueen(player.getPlayerProfile().getName())){
            return;
        }
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }
        Block block = e.getClickedBlock();
        bombManager.setBombMap(player.getPlayerProfile().getName(), block, BlastType.SWITCH);
        System.out.println("爆弾をセット");
    }

    public void onMove(PlayerMoveEvent e){
        KillerQueenWerewolf.plugin.getServer().getLogger().info("Moveリスナー");
        System.out.println("Moveリスナー");
    }
}
