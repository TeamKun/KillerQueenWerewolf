package net.kunmc.lab.killerqueen.stand;

import net.kunmc.lab.killerqueen.enums.BlastType;
import net.kunmc.lab.killerqueen.enums.BombCategory;
import net.kunmc.lab.killerqueen.enums.StandType;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class KillerQueen implements Stand {

    private Bomb bomb;

    public KillerQueen(){

    }

    @Override
    public StandType getStandType() {
        return StandType.KILLERQUEEN;
    }

    public void setBomb(Block bombBlock, BombCategory category, BlastType type){
        this.bomb = new Bomb(bombBlock, category, type);
    }

    public void setBomb(Entity bombEntity, BombCategory category, BlastType type){
        this.bomb = new Bomb(bombEntity, category, type);
    }


}
