package net.angusbeefgaming.sc;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class StaffChat extends JavaPlugin implements PluginMessageListener {
  @Override
  public void onEnable() {
    // Register the Channel in the spigot plugin thingy
    Bukkit.getMessenger().registerIncomingPluginChannel(this, "StaffChat", this);
  }
  
  @Override
  public void onDisable() {
  
  }
  
  @Override
  public void onPluginMessageRecieved(String channel, Player player, byte[] message) {
    
    DataInputStream input = new DataInputStream(new ByteArrayInputStream(message));
    
    // Try statement because it could throw an IO Exception
    try {
      // Get the Subchannel name
      String subchannel = input.readUTF();
      
      // Check the Subchannel name
      if(subchannel.equals("sc")) {
        String messageSender = input.readUTF();
        String message = input.readUTF();
        for(Player p : Bukkit.getOnlinePlayers()) {
          p.sendMessage(ChatColor.GREEN + "[Staff Chat]:" + ChatColor.GOLD + " (" + messageSender + "): " + ChatColor.AQUA + message);
        }
      }
    }
    catch(Exception e) {
      System.out.println("ALERT! AN EXCEPTION HAS OCCURED!");
      e.printStackTrace();
    }
  }
}
