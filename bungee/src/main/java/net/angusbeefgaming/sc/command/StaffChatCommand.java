package net.angusbeefgaming.sc.command;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;

public class StaffChatCommand extends Command {
  
  public StaffChatCommand() {
    super("staffchat", "angusbeef.command.staffchat");
  }
  
  @Override
  public void execute(CommandSender sender, String[] args) {
    if(!(args.length >= 1)) {
      sender.sendMessage(new TextComponent(ChatColor.RED + "Usage: /staffchat <Message>"));
      return;
    }
    
    StringBuilder msg = new StringBuilder();
       
    for (int i = 0; i < args.length; i++) {
        msg = msg.append(args[i] + " ");
    }
    
    Map<String, ServerInfo> servers = BungeeCord.getInstance().getServers();
    
    for (Entry<String, ServerInfo> en : servers.entrySet()) {
        String name = en.getKey();
        ServerInfo all = BungeeCord.getInstance().getServerInfo(name);
        // Make sure the message only gets sent to servers with players on them.
        if(all.getInfo().getPlayers().size() >= 1) {
          sendToBukkit("sc", msg.toString(), all);
        }
    }
    
    private void sendToBukkit(String channel, String message, ServerInfo server) {
      ByteArrayOutputStream stream = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(stream);
      try {
          out.writeUTF(channel);
          out.writeUTF(message);
      } catch (IOException e) {
          e.printStackTrace();
      }
      server.sendData("StaffChat", stream.toByteArray());
  }
    
  }
}
