package net.kunmc.lab.killerqueen.stand;

import net.kunmc.lab.killerqueen.enums.BlastType;
import net.kunmc.lab.killerqueen.enums.BombCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class Bomb {

    private BombCategory category;
    private BlastType type;
    private Block bombBlock;
    private Entity bombEntity;

    public Bomb(Block bombBlock, BombCategory category, BlastType type) {
        this.bombBlock = bombBlock;
        this.category = category;
        this.type = type;
    }

    public Bomb(Entity bombEntity, BombCategory category, BlastType type) {
        this.bombEntity = bombEntity;
        this.category = category;
        this.type = type;
    }

    public BombCategory category() {
        return category;
    }

    public void category(BombCategory category) {
        this.category = category;
    }

    public BlastType type() {
        return type;
    }

    public void type(BlastType type) {
        this.type = type;
    }

    public void blast() {

    }
}
