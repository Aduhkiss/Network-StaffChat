package net.angusbeefgaming.sc;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;

import net.angusbeefgaming.sc.command.*;

public class StaffChat extends Plugin {
    public void onEnable() {
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new StaffChatCommand());
        BungeeCord.getInstance().registerChannel("StaffChat");
    }
}
