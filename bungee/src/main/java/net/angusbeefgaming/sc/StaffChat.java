import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;

public class StaffChat extends Plugin {
    public void onEnable() {
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new StaffChatCommand());
        BungeeCord.getInstance().registerChannel("StaffChat");
    }
}
