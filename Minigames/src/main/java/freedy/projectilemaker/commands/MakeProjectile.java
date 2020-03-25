package freedy.projectilemaker.commands;

import freedy.projectilemaker.ProjectileMaker;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class MakeProjectile implements CommandExecutor {

    ProjectileMaker plugin;

    public MakeProjectile(ProjectileMaker plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.makepro(args[0]);
        return true;
    }
}
