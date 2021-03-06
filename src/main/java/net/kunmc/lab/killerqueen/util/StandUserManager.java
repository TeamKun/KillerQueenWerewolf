package net.kunmc.lab.killerqueen.util;

import net.kunmc.lab.killerqueen.enums.StandType;
import net.kunmc.lab.killerqueen.stand.CrazyDiamond;
import net.kunmc.lab.killerqueen.stand.KillerQueen;
import net.kunmc.lab.killerqueen.stand.Stand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class StandUserManager {

    private final Map<String, Stand> standUsers = new HashMap<>();
    private static final StandUserManager INSTANCE = new StandUserManager();

    private StandUserManager() {
    }

    public static StandUserManager getInstance() {
        return INSTANCE;
    }

    /**
     * スタンドを使えるユーザーをセットする
     *
     * @param sender     　コマンド送信者
     * @param playerName 　スタンド使いになるプレイヤーの名前
     * @param standType  　キラークイーン or クレイジーダイヤモンド
     */
    public void setStandUsers(CommandSender sender, String playerName, StandType standType) {
        if (standUsers.containsKey(playerName)) {
            if (standUsers.get(playerName).getStandType() == standType) {
                sender.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + "には既に対象のスタンドがセットされています。");
                return;
            }
            if (standType == StandType.KILLERQUEEN) {
                standUsers.replace(playerName, new KillerQueen());
                sender.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + "に" + ChatColor.LIGHT_PURPLE + "キラークイーン" + ChatColor.WHITE + "のスタンドを付与しました。");
            }
            if (standType == StandType.CRAZYDIAMOND) {
                standUsers.replace(playerName, new CrazyDiamond());
                Player p = Bukkit.getPlayer(playerName);
                setCrazyDiamondArmors(p);
                sender.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + "に" + ChatColor.AQUA + "クレイジー・ダイヤモンド" + ChatColor.WHITE + "のスタンドを付与しました。");
            }
        } else {
            if (standType == StandType.KILLERQUEEN) {
                standUsers.put(playerName, new KillerQueen());
                sender.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + "に" + ChatColor.LIGHT_PURPLE + "キラークイーン" + ChatColor.WHITE + "のスタンドを付与しました。");
            }
            if (standType == StandType.CRAZYDIAMOND) {
                standUsers.put(playerName, new CrazyDiamond());
                Player p = Bukkit.getPlayer(playerName);
                setCrazyDiamondArmors(p);
                sender.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + "に" + ChatColor.AQUA + "クレイジー・ダイヤモンド" + ChatColor.WHITE + "のスタンドを付与しました。");
            }
        }
    }

    /**
     * スタンドを消す
     *
     * @param sender     　コマンド送信者
     * @param playerName 　スタンド使いの名前
     * @param standType  　キラークイーン or クレイジーダイヤモンド
     */
    public void removeStandUsers(CommandSender sender, String playerName, StandType standType) {
        if (!standUsers.containsKey(playerName)) {
            sender.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + "にはスタンドがセットされていません。");
            return;
        }
        if (standUsers.get(playerName).getStandType() != standType) {
            sender.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + "には対象のスタンドがセットされていません。");
            return;
        }
        if (standType == StandType.KILLERQUEEN) {
            standUsers.remove(playerName);
            sender.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + "から" + ChatColor.LIGHT_PURPLE + "キラークイーン" + ChatColor.WHITE + "のスタンドを削除しました。");
        }
        if (standType == StandType.CRAZYDIAMOND) {
            standUsers.remove(playerName);
            Player p = Bukkit.getPlayer(playerName);
            removeCrazyDiamondArmors(p);
            sender.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + "から" + ChatColor.AQUA + "クレイジー・ダイヤモンド" + ChatColor.WHITE + "のスタンドを削除しました。");
        }
    }

    /**
     * プレイヤーがキラークイーンかどうか判定
     * @param playerName 対象プレイヤー
     * @return 対象がキラークイーンか否か
     */
    public boolean isKillerQueen(String playerName) {
        if (standUsers.containsKey(playerName)) {
            return standUsers.get(playerName).getStandType() == StandType.KILLERQUEEN;
        }
        return false;
    }

    /**
     * プレイヤーがクレイジー・ダイヤモンドかどうか判定
     * @param playerName　対象プレイヤー
     * @return 対象がクレイジー・ダイヤモンドか否か
     */
    public boolean isCrazyDiamond(String playerName) {
        if (standUsers.containsKey(playerName)) {
            return standUsers.get(playerName).getStandType() == StandType.CRAZYDIAMOND;
        }
        return false;
    }

    /**
     * クレイジー・ダイヤモンドとして設定されたプレイヤーに
     * ダイヤモンドのフル装備をセットする
     * @param p クレイジー・ダイヤモンドに指定されたプレイヤー
     */
    public void setCrazyDiamondArmors(Player p){
        ItemStack[] armors = p.getInventory().getArmorContents();
        armors[3] = new ItemStack(Material.DIAMOND_HELMET);
        armors[2] = new ItemStack(Material.DIAMOND_CHESTPLATE);
        armors[1] = new ItemStack(Material.DIAMOND_LEGGINGS);
        armors[0] = new ItemStack(Material.DIAMOND_BOOTS);
        p.getInventory().setArmorContents(armors);
    }

    /**
     * クレイジー・ダイヤモンドとして設定されたプレイヤーが
     * removeされた際に装備を外す。
     * @param p クレイジー・ダイヤモンドに指定されたプレイヤー
     */
    public void removeCrazyDiamondArmors(Player p){
        ItemStack[] armors = p.getInventory().getArmorContents();
        armors[3] = new ItemStack(Material.AIR);
        armors[2] = new ItemStack(Material.AIR);
        armors[1] = new ItemStack(Material.AIR);
        armors[0] = new ItemStack(Material.AIR);
        p.getInventory().setArmorContents(armors);
    }
}
