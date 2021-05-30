package net.kunmc.lab.killerqueen.logic;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class StandAbilityLogic {

    private static final StandAbilityLogic INSTANCE = new StandAbilityLogic();

    private StandAbilityLogic() {
    }

    public static StandAbilityLogic getInstance() {
        return INSTANCE;
    }

    public void checkBomb(Player player, Block block) {
        System.out.println("爆弾チェック");
    }

}
