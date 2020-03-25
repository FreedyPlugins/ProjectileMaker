package freedy.projectilemaker.commands;

import freedy.projectilemaker.ProjectileMaker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class SaveProjectile implements CommandExecutor {

    private ProjectileMaker plugin;

    public SaveProjectile(ProjectileMaker plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            //Location  playerloc = player.getLocation();
            if (args.length > 0) {
                plugin.savePro(player, args[0], Double.parseDouble(args[1]), args[2]);
            }else
                player.sendMessage("§c사용법: /savepro <발사체타입> <발사체속도> <발사체이름>\n발사체타입 목록: https://neolumia.github.io/spigot-docs/1.12.2/org/bukkit/entity/EntityType.html");
        }
        else
            System.out.println("§cYou can excute this command as player");

        return true;
    }
}
