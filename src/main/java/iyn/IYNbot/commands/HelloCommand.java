package iyn.IYNbot.commands;

import java.util.List;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelloCommand extends Command{

	@Override
	public String getUsage() {
		return "```> hello\n"
				+ "    Hi!```";
	}
	
	@Override
	public String getCommand() {
		return "hello";
	}

	@Override
	public String performCommand(MessageReceivedEvent e, User user, List<User> mentions, String[] args) {
		System.out.println(e.getGuild().getId());
		return "Hi there!";
	}
	
}
