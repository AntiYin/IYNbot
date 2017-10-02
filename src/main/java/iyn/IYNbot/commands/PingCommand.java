package iyn.IYNbot.commands;

import java.util.List;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PingCommand extends Command {

	@Override
	public String getUsage() {
		return "```> ping\n"
				+ "    Pings Niegil. He's a cuck.```";
	}
	
	@Override
	public String getCommand() {
		return "ping";
	}

	@Override
	public String performCommand(MessageReceivedEvent e, User user, List<User> mentions, String[] args) {
		String s = "<@199264285367468032>";
		for (int i = 0; i < 5; i++) {
			s += s;
		}
		
		return s;
	}

}
