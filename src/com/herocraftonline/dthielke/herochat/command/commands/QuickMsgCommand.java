/**
 * Copyright (C) 2011 DThielke <dave.thielke@gmail.com>
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/ or send a letter to
 * Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 **/

package com.herocraftonline.dthielke.herochat.command.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.herocraftonline.dthielke.herochat.HeroChat;
import com.herocraftonline.dthielke.herochat.channels.Channel;
import com.herocraftonline.dthielke.herochat.command.BaseCommand;

public class QuickMsgCommand extends BaseCommand {

    public QuickMsgCommand(HeroChat plugin) {
        super(plugin);
        name = "Quick Message";
        description = "Sends a message to a channel without changing focus";
        usage = "/§9<channel> §9<msg>";
        minArgs = 2;
        maxArgs = 1000;
        identifiers.add("qm");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String name = player.getName();
            Channel c = plugin.getChannelManager().getChannel(args[0]);
            if (c != null) {
                if (c.getPlayers().contains(name)) {
                    String msg = "";
                    for (int i = 1; i < args.length; i++) {
                        msg += args[i] + " ";
                    }
                    c.sendMessage(name, msg.trim());
                } else {
                    sender.sendMessage(plugin.getTag() + "You are not in " + c.getCName());
                }
            } else {
                sender.sendMessage(plugin.getTag() + "Channel not found");
            }
        } else {
            sender.sendMessage(plugin.getTag() + "You must be a player to use this command");
        }
    }

}
