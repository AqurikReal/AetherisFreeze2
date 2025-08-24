package fun.aqurik.aetherisFreeze;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.awt.*;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public final class AetherisFreeze extends JavaPlugin implements Listener {
    Logger logger = Bukkit.getLogger();
    public static final Set<String> plohie = ConcurrentHashMap.newKeySet(); // я эту хуйню 30 минут делал блять
    public static final HashMap<UUID, UUID> follow = new HashMap<>();

    // 1 Uuid в follow - FIB
    // второй педораз это нарушитель


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("freeze").setExecutor(new FreezeCMD(this));
        getCommand("unfreeze").setExecutor(new UnFreezeCMD(this));
        getCommand("follow").setExecutor(new FollowCMD(this));
        getCommand("unfollow").setExecutor(new UnFollowCMD(this));
        logger.info("Запускаюсь");


    }

    @Override
    public void onDisable() {
        logger.info("Выключаюсь");
    }

    @EventHandler
    public void move(PlayerMoveEvent event){
        if (plohie.contains(event.getPlayer().getName())){
            event.setCancelled(true);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_GLASS_BREAK, 3, -12);
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 10, true, false));
        }



        if (follow.containsKey(event.getPlayer().getUniqueId())) {
            Player pidoraz = getServer().getPlayer(follow.get(event.getPlayer().getUniqueId()));

            if (pidoraz == null || !pidoraz.isOnline()) {
                return;
            }

            Location sinpidora = event.getPlayer().getLocation().clone();
            sinpidora.add(event.getPlayer().getLocation().getDirection().normalize().multiply(-2));
            sinpidora.setYaw(event.getPlayer().getYaw());
            sinpidora.setPitch(event.getPlayer().getPitch());

            sinpidora.setY(event.getPlayer().getY());
            pidoraz.teleport(sinpidora);
        }



        if (follow.containsValue(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }





    }

    @EventHandler
    public void move2(PlayerDropItemEvent event){
        if (plohie.contains(event.getPlayer().getName())){
            event.setCancelled(true);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_GLASS_BREAK, 3, -12);
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 10, true, false));
        }


    }

    @EventHandler
    public void move3(EntityDamageByEntityEvent event){

        if (event.getDamager() instanceof Player) {
            if (follow.containsValue(event.getDamager().getUniqueId())){
                event.setCancelled(true);
            }
        }


    }








}
