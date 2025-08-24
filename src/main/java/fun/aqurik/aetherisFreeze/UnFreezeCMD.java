package fun.aqurik.aetherisFreeze;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UnFreezeCMD implements CommandExecutor {
    private final AetherisFreeze plugin;
    public UnFreezeCMD(AetherisFreeze plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {
        Component пеструн = Component.text("❄ Вас Разморозили ❄").color(TextColor.fromHexString("#27F5C5"));
        Component пеструн2 = Component.text("").color(TextColor.fromHexString("#27F5C5")); // можно тут тоже чото написать
        if(sender instanceof ConsoleCommandSender){
            if (args.length < 1) {
                Bukkit.getServer().getLogger().info("§7[Freeze] §cИспользование: /unfreeze <Игрок>");
                return true;
            }


            String name = args[0];
            Player badplr = Bukkit.getPlayer(name);
            if (badplr == null){
                Bukkit.getServer().getLogger().info("§7[Freeze] §cИгрок не найден.");
                return true;
            }

            AetherisFreeze.plohie.remove(badplr.getName());
            badplr.showTitle(Title.title(пеструн, пеструн2));


            return true;

        }


        Player p = (Player) sender;


        if (args.length < 1) {
            p.sendMessage("§7[Freeze] §cИспользование: /unfreeze <Игрок>");
            return true;
        }


        String name = args[0];
        Player badplr = Bukkit.getPlayer(name);
        if (badplr == null){
            p.sendMessage("§7[Freeze] §cИгрок не найден.");
            return true;
        }
        AetherisFreeze.plohie.remove(badplr.getName());
        badplr.showTitle(Title.title(пеструн, пеструн2));
        return true;

    }

}