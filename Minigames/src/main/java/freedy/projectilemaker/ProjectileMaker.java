package freedy.projectilemaker;

import freedy.projectilemaker.commands.MakeProjectile;
import freedy.projectilemaker.commands.RemoveProjectile;
import freedy.projectilemaker.commands.SaveProjectile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class ProjectileMaker extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("savepro").setExecutor(new SaveProjectile(this));
        getCommand("makepro").setExecutor(new MakeProjectile(this));
        getCommand("removepro").setExecutor(new RemoveProjectile(this));
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    public void savepro(Player player, String type, Double speed, String name) {
        final Location loc = player.getLocation();
        World world = loc.getWorld();
        Double x = loc.getX();
        Double y = loc.getY();
        Double z = loc.getZ();
        Float yaw = loc.getYaw();
        Float pitch = loc.getPitch();
        EntityType entity = EntityType.valueOf(type);
        Projectile pro = (Projectile) loc.getWorld().spawnEntity(new Location(world, x, y, z, yaw, pitch), entity);
        pro.setVelocity(loc.getDirection().multiply(speed));
        this.getConfig().set("data."+name+".world", world.getName());
        this.getConfig().set("data."+name+".x", x);
        this.getConfig().set("data."+name+".y", y);
        this.getConfig().set("data."+name+".z", z);
        this.getConfig().set("data."+name+".yaw", yaw);
        this.getConfig().set("data."+name+".pitch", pitch);
        this.getConfig().set("data."+name+".entity", type);
        this.getConfig().set("data."+name+".speed", speed);

        this.saveConfig();
    }
    public void makepro(String name) {
        World world = Bukkit.getServer().getWorld(this.getConfig().get("data."+name+".world").toString());
        Double x = (Double) this.getConfig().get("data."+name+".x");
        Double y = (Double) this.getConfig().get("data."+name+".y");
        Double z = (Double) this.getConfig().get("data."+name+".z");
        Float yaw = (Float) this.getConfig().get("data."+name+".yaw");
        Float pitch = (Float) this.getConfig().get("data."+name+".pitch");
        Location loc = new Location(world, x, y, z, yaw, pitch);
        EntityType entity = EntityType.valueOf(this.getConfig().get("data."+name+".entity").toString());
        Double speed = Double.parseDouble(this.getConfig().get("data."+name+".speed").toString());
        Projectile pro = (Projectile) world.spawnEntity(new Location(world, x, y, z, yaw, pitch), entity);
        pro.setVelocity(loc.getDirection().multiply(speed));
    }
    public void rempro(String name) {
        this.getConfig().set("data."+name, null);
    }

}
