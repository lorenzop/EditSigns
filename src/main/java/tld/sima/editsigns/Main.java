package tld.sima.editsigns;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin
{
	
	public Set<UUID> delay = new HashSet<UUID>();
	private Set<UUID> sign = new HashSet<UUID>();
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new EventManager(), this);
			
		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "EditSign Plugin initilized");
	}
	
	@Override
	public void onDisable() {
		delay.clear();
	}
	
	public Set<UUID> returnSignUUID(){
		return sign;
	}
}