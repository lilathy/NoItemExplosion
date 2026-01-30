package com.noexplosion;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class NoExplosionPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("NoExplosion is up — dropped items won't get destroyed by explosions anymore.");
    }

    @Override
    public void onDisable() {
        getLogger().info("NoExplosion shut down.");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Item)) {
            return;
        }

        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || 
            event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
            
            Item item = (Item) event.getEntity();

            Vector currentVelocity = item.getVelocity();
            Vector velocity;
            
            if (currentVelocity.length() > 0.1) {
                velocity = currentVelocity.normalize().multiply(0.8);
            } else {
                velocity = new Vector(
                    (Math.random() - 0.5) * 0.45,
                    0.6,
                    (Math.random() - 0.5) * 0.45
                );
            }
            
            velocity.setY(Math.max(velocity.getY(), 0.3));
            if (getConfig().getBoolean("apply-velocity", true)) {
                item.setVelocity(velocity);
            }
            event.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Item)) {
            return;
        }

        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || 
            event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
            
            Item item = (Item) event.getEntity();
            Entity damager = event.getDamager();

            Location itemLoc = item.getLocation();
            Location explosionLoc = damager.getLocation();
            
            Vector direction = itemLoc.toVector().subtract(explosionLoc.toVector()).normalize();

            double knockbackStrength = 0.8;
            Vector velocity = direction.multiply(knockbackStrength);
            velocity.setY(velocity.getY() * 0.55 + 0.3);
            if (getConfig().getBoolean("apply-velocity", true)) {
                item.setVelocity(velocity);
            }
            event.setCancelled(true);
        }
    }
}
