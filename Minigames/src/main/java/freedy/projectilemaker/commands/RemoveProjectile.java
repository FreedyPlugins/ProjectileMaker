package freedy.projectilemaker.commands;

import freedy.projectilemaker.ProjectileMaker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RemoveProjectile implements CommandExecutor {

    private ProjectileMaker plugin;

    public RemoveProjectile(ProjectileMaker plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.remPro(args[0]);
        return true;
    }
}
