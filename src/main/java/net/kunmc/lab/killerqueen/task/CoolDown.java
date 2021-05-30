package net.kunmc.lab.killerqueen.task;

import net.kunmc.lab.killerqueen.KillerQueenWerewolf;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class CoolDown {

    private final static CoolDown INSTANCE = new CoolDown();
    private final Map<String, Boolean> onCoolDownMap = new HashMap<>();

    private CoolDown() {
    }

    public static CoolDown getInstance() {
        return INSTANCE;
    }

    public boolean getCoolDownStatus(String playerName){
        if(onCoolDownMap.containsKey(playerName)){
            return onCoolDownMap.get(playerName);
        }else{
            return false;
        }
    }

    public void putIfAbsent(String playerName,boolean isCoolDown){
        onCoolDownMap.putIfAbsent(playerName, isCoolDown);
    }

    public void put(String playerName, boolean isCoolDown){
        onCoolDownMap.put(playerName, isCoolDown);
    }

    public void startTask(String playerName){
        new CoolDownTask(playerName).runTaskLater(KillerQueenWerewolf.plugin, 8);
    }

    private class CoolDownTask extends BukkitRunnable {
        String playerName;

        CoolDownTask(String playerName) {
            this.playerName = playerName;
        }

        @Override
        public void run() {
            onCoolDownMap.put(playerName, false);
        }
    }
}
