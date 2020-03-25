package freedy.projectilemaker;

import freedy.projectilemaker.commands.LimitedSpawner;
import freedy.projectilemaker.commands.MakeProjectile;
import freedy.projectilemaker.commands.RemoveProjectile;
import freedy.projectilemaker.commands.SaveProjectile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class ProjectileMaker extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("savepro").setExecutor(new SaveProjectile(this));
        getCommand("makepro").setExecutor(new MakeProjectile(this));
        getCommand("removepro").setExecutor(new RemoveProjectile(this));
        getCommand("makeentity").setExecutor(new LimitedSpawner(this));
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    public void savePro(Player player, String type, Double speed, String name) {
        final Location loc = player.getLocation();
        World world = loc.getWorld();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        float yaw = loc.getYaw();
        float pitch = loc.getPitch();
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
    public void makePro(String name) {
        World world = Bukkit.getServer().getWorld(this.getConfig().get("data."+name+".world").toString());
        Double x = (Double) this.getConfig().get("data."+name+".x");
        Double y = (Double) this.getConfig().get("data."+name+".y");
        Double z = (Double) this.getConfig().get("data."+name+".z");
        Float yaw = (Float) this.getConfig().get("data."+name+".yaw");
        Float pitch = (Float) this.getConfig().get("data."+name+".pitch");
        Location loc = new Location(world, x, y, z, yaw, pitch);
        EntityType entity = EntityType.valueOf(this.getConfig().get("data."+name+".entity").toString());
        double speed = Double.parseDouble(this.getConfig().get("data."+name+".speed").toString());
        Projectile pro = (Projectile) world.spawnEntity(new Location(world, x, y, z, yaw, pitch), entity);
        pro.setVelocity(loc.getDirection().multiply(speed));
    }
    public void remPro(String name) {
        this.getConfig().set("data."+name, null);
    }

    public void makeEntity(Location loc, Integer maxX, Integer maxZ, String name, Integer amount, Double speed) {
        for (int i = 1; i <= amount; i++) {
            Bukkit.getScheduler().runTaskLater(this, () -> loc.getWorld().spawnEntity(loc.add(new Random().nextInt(maxX), 0, new Random().nextInt(maxZ)), EntityType.valueOf(name)), ((long) (i * speed)));
        }
    }

}
