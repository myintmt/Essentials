package me.Henry.listeners;

import me.Henry.config.UserConfig;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BleedEffect implements Listener {

    private JavaPlugin plugin;
    private UserConfig userConfig;

    public BleedEffect(JavaPlugin plugin, UserConfig userConfig) {
        this.plugin = plugin;
        this.userConfig = userConfig;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof LivingEntity))
            return;
        if (!userConfig.getConfig().getBoolean("Settings.Particles.Bleed"))
            return;
        event.getEntity().getWorld().spawnParticle(Particle.BLOCK_CRACK, event.getEntity().getLocation().add(0, 2, 0), 15, Material.REDSTONE_BLOCK.createBlockData());
    }

}
