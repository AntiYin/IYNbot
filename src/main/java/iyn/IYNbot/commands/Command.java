package iyn.IYNbot.commands;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Command {

	public static void RegisterCommands() {
		commands.add(new HelpCommand());
		commands.add(new HelloCommand());
		commands.add(new SayCommand());
		commands.add(new PingCommand());
		commands.add(new RoleCommand());
		commands.add(new FeedbackCommand());
	}

	public abstract String getCommand();

	public static List<Command> commands = new ArrayList<Command>();

	public abstract String performCommand(MessageReceivedEvent e, User user, List<User> mentions, String[] args);

	public String getUsage() {
		return "> " + getCommand();
	}
	
	public boolean isAcceptable(String command) {
		return command.equals(getCommand());
	}

	public boolean isHidden() {
		return false;
	}
}
