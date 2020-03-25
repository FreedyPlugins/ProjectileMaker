package freedy.projectilemaker.commands;

import freedy.projectilemaker.ProjectileMaker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LimitedSpawner implements CommandExecutor {

    private ProjectileMaker plugin;

    public LimitedSpawner(ProjectileMaker plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 9)
            plugin.makeEntity(new Location(Bukkit.getWorld(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])), Integer.parseInt(args[4]), Integer.parseInt(args[5]), args[6], Integer.parseInt(args[7]), Double.parseDouble(args[8]));
        else
            sender.sendMessage("§c사용법: /makepro <월드> <X1> <Y1> <Z1> <X2> <Z2> <엔티티타입> <스폰수량> <스폰속도>");
        return true;
    }
}
