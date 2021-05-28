package net.kunmc.lab.killerqueen.listener;

import net.kunmc.lab.killerqueen.KillerQueenWerewolf;
import net.kunmc.lab.killerqueen.util.BlastType;
import net.kunmc.lab.killerqueen.util.BombManager;
import net.kunmc.lab.killerqueen.util.StandUserManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;


public class StandListener implements Listener {

    private StandUserManager standUserManager = StandUserManager.getInstance();
    private BombManager bombManager = BombManager.getInstance();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
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

    /**
     * クレイジーダイヤモンドの装備を固定する
     * @param e
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (!(holder instanceof Player)) return;
        Player p = (Player) holder;
        if (standUserManager.isCrazyDiamond(p.getPlayerProfile().getName()) &&
                (36 <= e.getSlot() && e.getSlot() <= 39)) {
            e.setCancelled(true);
            p.updateInventory();
        }
    }

}
