package net.kunmc.lab.killerqueen.stand;

import net.kunmc.lab.killerqueen.util.BlastType;
import net.kunmc.lab.killerqueen.util.Bomb;
import net.kunmc.lab.killerqueen.util.BombCategory;
import net.kunmc.lab.killerqueen.util.StandType;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

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
