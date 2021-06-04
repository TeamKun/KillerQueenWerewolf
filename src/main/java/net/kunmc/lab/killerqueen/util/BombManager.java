package net.kunmc.lab.killerqueen.util;

import net.kunmc.lab.killerqueen.enums.BlastType;
import net.kunmc.lab.killerqueen.enums.BombCategory;
import net.kunmc.lab.killerqueen.stand.Bomb;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BombManager {

    private final Map<String, Bomb> bombMap = new HashMap<>();
    private static final BombManager INSTANCE = new BombManager();

    private BombManager() {
    }

    public static BombManager getInstance() {
        return INSTANCE;
    }

    /**
     * エンティティを爆弾としてセットする
     *
     * @param playerName 　プレイヤー名
     * @param entity     　爆弾になるエンティティ
     * @param type       　爆破のタイプ
     */
    public void setBombMap(String playerName, Entity entity, BlastType type) {
        Bomb bomb = new Bomb(entity, BombCategory.ENTITY, type);
        if (bombMap.containsKey(playerName)) {
            Bukkit.getPlayer(playerName).sendActionBar("既に爆弾は設置済みです。");
        } else {
            bombMap.put(playerName, bomb);
        }
    }

    /**
     * ブロックを爆弾としてセットする
     *
     * @param playerName 　プレイヤー名
     * @param block      　爆弾になるブロック
     * @param type       　爆破タイプ
     */
    public void setBombMap(String playerName, Block block, BlastType type) {
        Bomb bomb = new Bomb(block, BombCategory.BLOCK, type);
        if (bombMap.containsKey(playerName)) {
            Bukkit.getPlayer(playerName).sendActionBar("既に爆弾は設置済みです。");
        } else {
            System.out.println(playerName);
            bombMap.put(playerName, bomb);
        }
    }

    /**
     * プレイヤーが爆弾にしている物があれば爆弾を返却する
     *
     * @param playerName 　プレイヤー名
     * @return　爆弾 or null
     */
    public Bomb getBomb(String playerName) {
        if (bombMap.containsKey(playerName)) {
            return bombMap.get(playerName);
        }
        return null;
    }

    public void removeBomb(String playerName){
        if(bombMap.containsKey(playerName)){
            bombMap.remove(playerName);
        }
    }

    /**
     * 指定されたカテゴリの爆弾リストを返す
     * NONEの場合は全件返却
     *
     * @param category　取得したい爆弾のカテゴリ
     * @return 指定カテゴリの爆弾のリスト
     */
    public ArrayList<Bomb> getBombList(BombCategory category) {
        ArrayList<Bomb> bombList = new ArrayList<>();
        for (Bomb bomb : bombMap.values()) {
            if (category == BombCategory.NONE) {
                bombList.add(bomb);
            } else {
                if(category == bomb.category()){
                    bombList.add(bomb);
                }
            }

        }
        return bombList;
    }

}
