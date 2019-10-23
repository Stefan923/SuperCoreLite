package me.Stefan923.SuperCoreLite.Commands.Type;

import me.Stefan923.SuperCoreLite.Commands.AbstractCommand;
import me.Stefan923.SuperCoreLite.Main;
import me.Stefan923.SuperCoreLite.Utils.MessageUtils;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class CommandReload extends AbstractCommand implements MessageUtils {

    public CommandReload(AbstractCommand abstractCommand) {
        super(abstractCommand, false, "reload");
    }

    @Override
    protected AbstractCommand.ReturnType runCommand(Main instance, CommandSender sender, String... args) {
        if (args.length != 2)
            return ReturnType.SYNTAX_ERROR;

        Main plugin = Main.instance;

        if (args[1].equalsIgnoreCase("all")) {
            plugin.getSettingsManager().reload();
            plugin.getLanguageManager().reload();
            sender.sendMessage(formatAll("&8「&3SuperCore&8」 &fYou have successfully reloaded &ball &fmodules!"));
            return ReturnType.SUCCESS;
        }

        if (args[1].equalsIgnoreCase("settings")) {
            plugin.getSettingsManager().reload();
            sender.sendMessage(formatAll("&8「&3SuperCore&8」 &fYou have successfully reloaded &bsettings &fmodule!"));
            return ReturnType.SUCCESS;
        }

        if (args[1].equalsIgnoreCase("languages")) {
            plugin.getLanguageManager().reload();
            sender.sendMessage(formatAll("&8「&3SuperCore&8」 &fYou have successfully reloaded &blanguages &fmodule!"));
            return ReturnType.SUCCESS;
        }

        return ReturnType.SYNTAX_ERROR;
    }

    @Override
    protected List<String> onTab(Main instance, CommandSender sender, String... args) {
        if (sender.hasPermission("supercore.admin"))
            return Arrays.asList("settings", "languages", "all");
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "supercore.admin";
    }

    @Override
    public String getSyntax() {
        return "/core reload";
    }

    @Override
    public String getDescription() {
        return "Reloads plugin settings.";
    }

}
