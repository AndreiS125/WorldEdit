package org.andreis.mc.worldedit;

import org.andreis.mc.worldedit.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;

public final class WorldEdit extends JavaPlugin {
    public static HashMap<String, String> mutemap = new HashMap<>();
    public static HashMap<String, String> pos1map = new HashMap<>();
    public static HashMap<String, String> pos2map = new HashMap<>();
    public static HashMap<String, Location> loc1map = new HashMap<>();
    public static HashMap<String, Location> loc2map = new HashMap<>();
    public static repository rep;
    @Override
    public void onEnable() {
        try {
            rep=new repository();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        getCommand("become").setExecutor(new become());
        getCommand("sayfor").setExecutor(new sayfor());
        getCommand("mute").setExecutor(new mute());
        getCommand("unmute").setExecutor(new unmute());
        getCommand("invsee").setExecutor(new invsee());
        getCommand("rg").setExecutor(new rg());
        getCommand("/wand").setExecutor(new wand());
    }

    @Override
    public void onDisable() {
        try {
            rep.c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
