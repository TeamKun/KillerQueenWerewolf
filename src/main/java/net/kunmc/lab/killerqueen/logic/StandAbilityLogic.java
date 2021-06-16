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
     * @param player 触れたプレイヤー
     * @param block　触れたブロック
     */
    public void checkTouchBlockBomb(Player player, Block block) {
        ArrayList<Bomb> bombList = bombManager.getBombList(BombCategory.BLOCK);
        boolean isBomb = false;
        BlastType blastType = BlastType.NONE;
        //叩かれたブロックが爆弾リストに含まれているか判定
        for (Bomb bomb : bombList) {
            if (block.getLocation().equals(bomb.bombBlock().getLocation())) {
                isBomb = true;
                blastType = bomb.blastType();
                break;
            }
        }
        if (isBomb && blastType == BlastType.SWITCH) {
            player.getWorld().createExplosion(player, 4F);
            bombManager.removeBomb(player.getPlayerProfile().getName());
        }
    }

    public void activateButtonBomb(String playerName){
        Bomb bomb = bombManager.getBomb(playerName);
        BombCategory category =  bomb.category();
        if(category.equals(BombCategory.ENTITY)){
            bomb.bombEntity().getWorld().createExplosion(bomb.bombEntity(), 4);
        } else if(category.equals(BombCategory.BLOCK)){
            bomb.bombBlock().getWorld().createExplosion(bomb.bombBlock().getLocation(), 4);
        }
    }

}
