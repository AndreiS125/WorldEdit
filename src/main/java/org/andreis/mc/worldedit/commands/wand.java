package org.andreis.mc.worldedit.commands;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class wand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {



        Player pl  = Bukkit.getPlayer(sender.getName());
        ItemStack stack=new ItemStack(Material.WOODEN_AXE);
        List<String> lore = new java.util.ArrayList<>();
        lore.add("wand");
        stack.setLore(lore);
        if(pl.getInventory().getItemInMainHand().getType()!=Material.AIR){
            pl.getLocation().getWorld().dropItem(pl.getLocation(), pl.getInventory().getItemInMainHand());

        }
        pl.getInventory().setItemInHand(stack);

        return false;
    }
}

