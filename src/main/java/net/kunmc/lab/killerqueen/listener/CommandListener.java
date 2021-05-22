package net.kunmc.lab.killerqueen.listener;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandListener implements Listener {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("stand")) {
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
            if(Bukkit.selectEntities(sender, args[2]) != null){

            }
        } else {
            if (Bukkit.getPlayer(args[2]) != null) {

            }
        }

        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
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