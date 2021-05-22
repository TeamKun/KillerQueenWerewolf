package net.kunmc.lab.killerqueen.util;

import net.kunmc.lab.killerqueen.stand.CrazyDiamond;
import net.kunmc.lab.killerqueen.stand.KillerQueen;
import net.kunmc.lab.killerqueen.stand.Stand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class StandUserManager {
    private static HashMap<String, Stand> standUsers = new HashMap<>();

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
                sender.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + "に" + ChatColor.AQUA + "クレイジーダイヤモンド" + ChatColor.WHITE + "のスタンドを付与しました。");
            }
        } else {
            if (standType == StandType.KILLERQUEEN) {
                standUsers.put(playerName, new KillerQueen());
                sender.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + "に" + ChatColor.LIGHT_PURPLE + "キラークイーン" + ChatColor.WHITE + "のスタンドを付与しました。");
            }
            if (standType == StandType.CRAZYDIAMOND) {
                standUsers.put(playerName, new CrazyDiamond());
                sender.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + "に" + ChatColor.AQUA + "クレイジーダイヤモンド" + ChatColor.WHITE + "のスタンドを付与しました。");
            }
        }
        return;
    }

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
            sender.sendMessage(ChatColor.YELLOW + playerName + ChatColor.WHITE + "から" + ChatColor.AQUA + "クレイジーダイヤモンド" + ChatColor.WHITE + "のスタンドを削除しました。");
        }
        return;
    }
}