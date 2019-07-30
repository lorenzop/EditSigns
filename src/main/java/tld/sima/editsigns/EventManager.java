package tld.sima.editsigns;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import net.md_5.bungee.api.ChatColor;
import tld.sima.editsigns.magic.SignMagic;
import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.PacketPlayOutOpenSignEditor;

public class EventManager implements Listener{
	
	Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!plugin.delay.contains(player.getUniqueId()) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Block block = event.getClickedBlock();
			if (event.getClickedBlock().getState() instanceof Sign) {
				SignMagic magic = new SignMagic();
				magic.doSomething(block.getLocation(), player, 57);
				PacketPlayOutOpenSignEditor ppoose = new PacketPlayOutOpenSignEditor(new BlockPosition(block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ()));
				((CraftPlayer) player).getHandle().playerConnection.sendPacket(ppoose);
			}
		}
	}
	
	@EventHandler
	public void onEditSign(SignChangeEvent event) {
		String[] lines = event.getLines();
		for (int i = 0 ; i < lines.length ; i++) {
			event.setLine(i, ChatColor.translateAlternateColorCodes('&', lines[i]));
		}
	}
}
