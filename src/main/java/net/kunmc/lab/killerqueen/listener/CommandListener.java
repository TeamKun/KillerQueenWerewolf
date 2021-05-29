package net.kunmc.lab.killerqueen.listener;

import net.kunmc.lab.killerqueen.util.StandType;
import net.kunmc.lab.killerqueen.util.StandUserManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandListener implements CommandExecutor, TabCompleter {

    StandUserManager standUserManager = StandUserManager.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String commandLabel, @NotNull String[] args) {
        if (!cmd.getName().equalsIgnoreCase("killerqueenw")) {
            return false;
        }
        if (args.length < 1) {
            sender.sendMessage("引数が足りません");
            return false;
        } else if (args.length > 3) {
            sender.sendMessage("引数が多すぎます");
            return false;
        }

        if (!(args[0].equalsIgnoreCase("killerqueen") || args[0].equalsIgnoreCase("crazydiamond"))) {
            sender.sendMessage("第1引数にはスタンド名を入力してください");
            return true;
        }

        if (!(args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("remove"))) {
            sender.sendMessage("第2引数にはadd/removeを入力してください");
            return true;
        }
        if (args[2].startsWith("@")) {
            if (Bukkit.selectEntities(sender, args[2]) == null) {
                sender.sendMessage("指定されたセレクタに該当するプレイヤーは存在しません。");
                return true;
            }
            StandType standType = StandType.NONE;
            if (args[0].equalsIgnoreCase("KillerQueen")) {
                standType = StandType.KILLERQUEEN;
            }
            if (args[0].equalsIgnoreCase("CrazyDiamond")) {
                standType = StandType.CRAZYDIAMOND;
            }
            if (args[1].equalsIgnoreCase("add")) {
                for (Entity entity : Bukkit.selectEntities(sender, args[2])) {
                    Player player = (Player) entity;
                    standUserManager.setStandUsers(sender, player.getPlayerProfile().getName(), standType);
                }
            }
            if (args[1].equalsIgnoreCase("remove")) {
                for (Entity entity : Bukkit.selectEntities(sender, args[2])) {
                    Player player = (Player) entity;
                    standUserManager.removeStandUsers(sender, player.getPlayerProfile().getName(), standType);
                }
            }
        } else {
            if (Bukkit.getPlayer(args[2]) == null) {
                sender.sendMessage("指定されたプレイヤー名は存在しません。");
                return true;
            }
            StandType standType = StandType.NONE;
            if (args[0].equalsIgnoreCase("KillerQueen")) {
                standType = StandType.KILLERQUEEN;
            } else if (args[0].equalsIgnoreCase("CrazyDiamond")) {
                standType = StandType.CRAZYDIAMOND;

            }
            if (args[1].equalsIgnoreCase("add")) {
                standUserManager.setStandUsers(sender, args[2], standType);
            }
            if (args[1].equalsIgnoreCase("remove")) {
                standUserManager.removeStandUsers(sender, args[2], standType);
            }
        }
        return true;

    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        switch (args.length) {
            case 1:
                return Stream.of("killerqueen", "crazydiamond")
                        .filter(e -> e.startsWith(args[0]))
                        .collect(Collectors.toList());
            case 2:
                return Stream.of("add", "remove")
                        .filter(e -> e.startsWith(args[1]))
                        .collect(Collectors.toList());
            case 3:
                return Stream.concat(Bukkit.getOnlinePlayers()
                        .stream().map(Player::getName), Stream.of("@a"))
                        .filter(x -> x.startsWith(args[2])).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}