package net.kunmc.lab.killerqueen.listener;

import net.kunmc.lab.killerqueen.util.BlastType;
import net.kunmc.lab.killerqueen.util.BombManager;
import net.kunmc.lab.killerqueen.util.StandUserManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class KillerQueenEvent implements Listener {

    private StandUserManager standUserManager = new StandUserManager();
    private BombManager bombManager = new BombManager();

    public void onRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if(!standUserManager.isKillerQueen(player.getPlayerProfile().getName())){
            return;
        }
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }
        Block block = e.getClickedBlock();
        bombManager.setBombMap(player.getPlayerProfile().getName(), block, BlastType.SWITCH);

    }
}
