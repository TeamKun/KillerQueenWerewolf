package net.kunmc.lab.killerqueen.logic;

import net.kunmc.lab.killerqueen.enums.BlastType;
import net.kunmc.lab.killerqueen.enums.BombCategory;
import net.kunmc.lab.killerqueen.stand.Bomb;
import net.kunmc.lab.killerqueen.util.BombManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class StandAbilityLogic {

    private static final StandAbilityLogic INSTANCE = new StandAbilityLogic();
    BombManager bombManager = BombManager.getInstance();

    private StandAbilityLogic() {
    }

    public static StandAbilityLogic getInstance() {
        return INSTANCE;
    }

    /**
     * ブロックが爆弾か判定する
     * @param player
     * @param block
     */
    public void checkTouchBlockBomb(Player player, Block block) {
        ArrayList<Bomb> bombList = bombManager.getBombList(BombCategory.BLOCK);
        boolean isBomb = false;
        BlastType blastType = BlastType.NONE;
        //叩かれたブロックが爆弾リストに含まれているか判定
        for (Bomb bomb : bombList) {
            if (block == bomb.bombBlock()) {
                isBomb = true;
                blastType = bomb.blastType();
                break;
            }
        }
        if (isBomb && blastType == BlastType.SWITCH) {
            block.getWorld().createExplosion(player.getLocation(), 1);
        }
    }

}
