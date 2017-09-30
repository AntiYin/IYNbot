package iyn.IYNbot.commands;

import java.util.List;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SayCommand extends Command {

	@Override
	public String getCommand() {
		return "say";
	}

	@Override
	public String performCommand(MessageReceivedEvent e, User user, List<User> mentions, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
