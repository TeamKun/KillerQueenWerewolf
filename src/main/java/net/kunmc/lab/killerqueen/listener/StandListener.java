package net.kunmc.lab.killerqueen.listener;

import net.kunmc.lab.killerqueen.enums.BlastType;
import net.kunmc.lab.killerqueen.logic.StandAbilityLogic;
import net.kunmc.lab.killerqueen.util.BombManager;
import net.kunmc.lab.killerqueen.util.StandUserManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;


public class StandListener implements Listener {

    private final StandUserManager standUserManager = StandUserManager.getInstance();
    private final BombManager bombManager = BombManager.getInstance();
    private final StandAbilityLogic standAbilityLogic = StandAbilityLogic.getInstance();

    /**
     * インタラクトしたときの動作を振り分ける
     * @param e
     */
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!standUserManager.isKillerQueen(player.getPlayerProfile().getName()) &&
                !standUserManager.isCrazyDiamond(player.getPlayerProfile().getName())) {
            return;
        }
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            standAbilityLogic.checkBomb(player, e.getClickedBlock());
            return;
        }
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        Block block = e.getClickedBlock();
        bombManager.setBombMap(player.getPlayerProfile().getName(), block, BlastType.SWITCH);

    }

    /**
     * エンティティを爆弾としてセットする処理
     * @param e
     */
    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        if(!standUserManager.isKillerQueen(player.getPlayerProfile().getName())){
            return;
        }

        Entity entity = e.getRightClicked();
        bombManager.setBombMap(player.getPlayerProfile().getName(), entity, BlastType.TOUCH);

    }

    /**
     * クレイジー・ダイヤモンドの装備を固定する
     * <p>
     * クレイジー・ダイヤモンドに設定されているプレイヤーが
     * インベントリをクリックすると、インベントリをアップデートしてクリックをキャンセルする
     *
     * @param e　イベント
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
