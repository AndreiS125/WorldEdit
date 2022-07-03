package org.andreis.mc.worldedit;

import io.papermc.paper.event.block.BlockBreakBlockEvent;
import io.papermc.paper.event.block.PlayerShearBlockEvent;
import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.SkullMeta;

public class EventListener implements Listener {

    @EventHandler
    public void move(BlockBreakEvent e) { //u cant move in lobby
        try {


            if(WorldEdit.rep.cantbreakblocks(e.getBlock().getLocation(),e.getPlayer().getName())){
                e.setCancelled(true);
            }
        }
        catch (Exception ex){
            e.getPlayer().sendMessage(ex.toString());
        }

    }
    @EventHandler
    public void explode(ExplosionPrimeEvent e) { //u cant move in lobby
        try {

            
            if(WorldEdit.rep.cantbreakblocks(e.getEntity().getLocation(), "explosion")){
                e.setCancelled(true);
            }
        }
        catch (Exception ex){
            for(Player pl:Bukkit.getOnlinePlayers()){
                pl.sendMessage("ВЗРЫВ");
            }
        }

    }
    @EventHandler
    public void place(BlockPlaceEvent e) { //u cant move in lobby
        try {


            if(WorldEdit.rep.cantbreakblocks(e.getBlock().getLocation(),e.getPlayer().getName())){
                e.setCancelled(true);
            }
        }
        catch (Exception ex){
            e.getPlayer().sendMessage(ex.toString());
        }

    }

    @EventHandler
    public void damage(BlockDamageEvent e) { //u cant move in lobby
        try {


            if(WorldEdit.rep.cantbreakblocks(e.getBlock().getLocation(),e.getPlayer().getName())){
                e.setCancelled(true);
            }
        }
        catch (Exception ex){
            e.getPlayer().sendMessage(ex.toString());
        }

    }
    @EventHandler
    public void burn(BlockBurnEvent e) { //u cant move in lobby
        try {


            if(WorldEdit.rep.cantbreakblocks(e.getBlock().getLocation(),"explosion")){
                e.setCancelled(true);
            }
        }
        catch (Exception ex){

        }

    }
    @EventHandler
    public void fade(BlockFadeEvent e) { //u cant move in lobby
        try {


            if(WorldEdit.rep.cantbreakblocks(e.getBlock().getLocation(),"explosion")){
                e.setCancelled(true);
            }
        }
        catch (Exception ex){

        }

    }

    @EventHandler
    public void harvest(PlayerHarvestBlockEvent e) { //u cant move in lobby
        try {


            if(WorldEdit.rep.cantbreakblocks(e.getHarvestedBlock().getLocation(),e.getPlayer().getName())){
                e.setCancelled(true);
            }
        }
        catch (Exception ex){
            e.getPlayer().sendMessage(ex.toString());
        }

    }
    @EventHandler
    public void shear(PlayerShearBlockEvent e) { //u cant move in lobby
        try {


            if(WorldEdit.rep.cantbreakblocks(e.getBlock().getLocation(),e.getPlayer().getName())){
                e.setCancelled(true);
            }
        }
        catch (Exception ex){
            e.getPlayer().sendMessage(ex.toString());
        }

    }

    @EventHandler
    public void frame(PlayerItemFrameChangeEvent e) {
        try {


            if(WorldEdit.rep.cantbreakblocks(e.getItemFrame().getLocation(),e.getPlayer().getName())){
                e.setCancelled(true);
            }
        }
        catch (Exception ex){
            e.getPlayer().sendMessage(ex.toString());
        }

    }
    @EventHandler
    public void explosion(EntityExplodeEvent e) {
        try {

            
            for(Block bl:e.blockList()) {
                if (WorldEdit.rep.cantbreakblocks(bl.getLocation(), "expl")) {
                    e.setCancelled(true);

                }
            }
        }
        catch (Exception ex){

        }

    }




    @EventHandler
    public void interact(PlayerInteractEvent e) { //u cant move in lobby
        try {


            if(WorldEdit.rep.cantbreakblocks(e.getClickedBlock().getLocation(),e.getPlayer().getName())){
                e.setCancelled(true);
                e.getPlayer().closeInventory();

            }
            else{
                if (e.getClickedBlock().getLocation().getWorld()== Bukkit.getWorld("world") && e.getPlayer().getInventory().getItemInMainHand().getLore()!=null) {
                    if (e.getPlayer().getInventory().getItemInMainHand().getType()== Material.WOODEN_AXE) {
                        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                            WorldEdit.pos2map.remove(e.getPlayer().getName());
                            WorldEdit.loc2map.remove(e.getPlayer().getName());
                            WorldEdit.loc2map.put(e.getPlayer().getName(),e.getClickedBlock().getLocation());
                            WorldEdit.pos2map.put(e.getPlayer().getName(), String.valueOf(e.getClickedBlock().getLocation().getBlockX()) + ":" + String.valueOf(e.getClickedBlock().getLocation().getBlockZ()));
                            e.getPlayer().sendMessage(ChatColor.GREEN+"ВТОРАЯ ТОЧКА: "+e.getClickedBlock().getLocation().toString());
                        }
                        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {

                            WorldEdit.pos1map.remove(e.getPlayer().getName());
                            WorldEdit.loc1map.remove(e.getPlayer().getName());
                            WorldEdit.pos1map.put(e.getPlayer().getName(), String.valueOf(e.getClickedBlock().getLocation().getBlockX()) + ":" + String.valueOf(e.getClickedBlock().getLocation().getBlockZ()));
                            WorldEdit.loc1map.put(e.getPlayer().getName(),e.getClickedBlock().getLocation());
                            e.getPlayer().sendMessage(ChatColor.GREEN+"ПЕРВАЯ ТОЧКА: "+e.getClickedBlock().getLocation().toString());
                        }
                    }
                }


            }
        }
        catch (Exception ex){

        }

    }







    @EventHandler
    public void s(PlayerChatEvent e) { //u cant fight in lobby
        try {
            if(WorldEdit.mutemap.get(e.getPlayer().getName()).equals("on")){
                e.setCancelled(true);

            }
        }
        catch (Exception ex){

        }

    }

}
