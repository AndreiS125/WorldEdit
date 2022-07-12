package org.andreis.mc.worldedit.commands;


import org.andreis.mc.worldedit.WorldEdit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class rg implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            if(args[0]!=null) {
                if (args[0].equals("info")){
                    String rg=WorldEdit.rep.rgnameuin(Bukkit.getPlayer(sender.getName()).getLocation());
                    sender.sendMessage(ChatColor.GOLD+"Регион в котором вы находитесь называется "+rg+".");
                    sender.sendMessage(ChatColor.GOLD+"И его владелец - "+WorldEdit.rep.get("owner",rg)+".");
                }
                if (args[0].equals("list")){

                    sender.sendMessage(ChatColor.GOLD+"Спиcок ваших приватов:");
                    for(String name:WorldEdit.rep.getallmyrgs(sender.getName())){
                        sender.sendMessage(ChatColor.GOLD+name);
                    }
                }
                if (args[1] != null) {

                    //claim
                    switch (args[0]) {
                        case "claim":
                            if(WorldEdit.rep.getifowner(sender.getName(),args[1])){
                                if (WorldEdit.pos1map.get(sender.getName()) != null && WorldEdit.pos2map.get(sender.getName()) != null) {

                                    String pos1 = WorldEdit.pos1map.get(sender.getName());
                                    String pos2 = WorldEdit.pos2map.get(sender.getName());
                                    int square = (Integer.parseInt(pos1.split(":")[0]) - Integer.parseInt(pos2.split(":")[0]) * (Integer.parseInt(pos1.split(":")[1]) - Integer.parseInt(pos2.split(":")[1])));
                                    boolean can = false;
                                    Location loc1 = WorldEdit.loc1map.get(sender.getName());
                                    Location loc2 = WorldEdit.loc2map.get(sender.getName());
                                    Location loc3 = new Location(loc1.getWorld(), loc1.getX(), loc1.getY(), loc2.getZ(), loc1.getYaw(), loc1.getPitch());
                                    Location loc4 = new Location(loc1.getWorld(), loc2.getX(), loc1.getY(), loc1.getZ(), loc1.getYaw(), loc1.getPitch());
                                    if (!WorldEdit.rep.cantbreakblocks(loc1, "") && !WorldEdit.rep.cantbreakblocks(loc2, "") && !WorldEdit.rep.cantbreakblocks(loc3, "") && !WorldEdit.rep.cantbreakblocks(loc4, "")) {
                                        if (Math.abs(square) <= 12000 && !sender.getName().equals("Screed64")) {
                                            WorldEdit.rep.add(args[1], sender.getName(), pos1, pos2, "");
                                            sender.sendMessage(ChatColor.GREEN + "Вы успешно добавили новый приват.");
                                        } else if (sender.getName().equals("Screed64")) {
                                            WorldEdit.rep.add(args[1], sender.getName(), pos1, pos2, "");
                                            sender.sendMessage(ChatColor.GREEN + "Вы успешно добавили новый приват.");
                                        }
                                    } else {
                                        sender.sendMessage("Ваш регион заходит на чужие приваты.");
                                    }
                                }
                            }
                            break;

                        //add
                        case "add":
                            if (args[2] != null) {
                                if(WorldEdit.rep.getifowner(sender.getName(),args[1])) {

                                    WorldEdit.rep.update("friends", args[2], args[1]);
                                    sender.sendMessage(ChatColor.GREEN + "Вы успешно добавили игрока в приват.");
                                }
                            } else {
                                sender.sendMessage(ChatColor.RED + "Вы не указали имя игрока, котороого добавить.");
                            }
                            break;


                        //remove
                        case "remove":

                            if (args[1].equals("region")) {
                                if(WorldEdit.rep.getifowner(sender.getName(),args[2])) {
                                    if (args[2] != null) {

                                        WorldEdit.rep.remove(args[2]);
                                        sender.sendMessage(ChatColor.RED + "Вы успешно удалили приват.");
                                    } else {
                                        sender.sendMessage(ChatColor.RED + "Вы не указали имя региона, котороого удалить.");
                                    }
                                }
                            } else if (args[1].equals("player")) {
                                if (args[2] != null && args[3] != null) {
                                    if(WorldEdit.rep.getifowner(sender.getName(),args[2])) {
                                        String[] list = WorldEdit.rep.get("friends", args[2]).split(":");
                                        String exit = "";
                                        for (String name : list) {
                                            if (!name.equals(args[3])) {
                                                exit = exit + name + ":";
                                            }
                                        }
                                        WorldEdit.rep.update("friends", exit, args[2]);
                                        sender.sendMessage(ChatColor.GREEN + "Вы успешно удалили игрока из привата");
                                    }
                                } else {
                                    sender.sendMessage(ChatColor.RED + "Вы не указали имя региона или игрока, из/и котороого удалить.");
                                }
                            }
                            break;
                    }
                }
            }



        }
        catch (Exception e){

        }

        return false;
    }
}

