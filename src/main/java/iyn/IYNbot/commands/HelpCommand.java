package iyn.IYNbot.commands;

import java.util.List;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand extends Command {

	@Override
	public String getUsage() {
		return "```> help\n"
				+ "    Displays this menu.```";
	}
	
	@Override
	public String getCommand() {
		return "help";
	}

	@Override
	public String performCommand(MessageReceivedEvent e, User user, List<User> mentions, String[] args) {
		String s = "";
		for (Command c : commands) {
			s += c.getUsage();
		}
		return s;
	}

}
