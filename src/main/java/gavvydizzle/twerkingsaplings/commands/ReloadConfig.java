package gavvydizzle.twerkingsaplings.commands;

import gavvydizzle.twerkingsaplings.TwerkingSaplings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfig implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1 && args[0].equals("reload")) {
            TwerkingSaplings.instance.reloadConfig();
            return true;
        }

        return false;
    }
}
