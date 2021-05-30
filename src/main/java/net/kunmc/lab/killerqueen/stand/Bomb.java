package net.kunmc.lab.killerqueen.stand;

import net.kunmc.lab.killerqueen.enums.BlastType;
import net.kunmc.lab.killerqueen.enums.BombCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class Bomb {

    private BombCategory category;
    private BlastType blastType;
    private Block bombBlock;
    private Entity bombEntity;

    public Bomb(Block bombBlock, BombCategory category, BlastType type) {
        this.bombBlock = bombBlock;
        this.category = category;
        this.blastType = type;
    }

    public Bomb(Entity bombEntity, BombCategory category, BlastType type) {
        this.bombEntity = bombEntity;
        this.category = category;
        this.blastType = type;
    }

    public BombCategory category() {
        return category;
    }

    public void category(BombCategory category) {
        this.category = category;
    }

    public BlastType blastType() {
        return blastType;
    }

    public void blastType(BlastType type) {
        this.blastType = type;
    }

    public Block bombBlock() {
        return bombBlock;
    }

    public void bombBlock(Block bombBlock) {
        this.bombBlock = bombBlock;
    }

    public Entity bombEntity() {
        return bombEntity;
    }

    public void bombEntity(Entity bombEntity) {
        this.bombEntity = bombEntity;
    }

    public void blast() {

    }
}
