package iyn.IYNbot.IYNbot;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import iyn.IYNbot.commands.Command;

public class MessageListener extends net.dv8tion.jda.core.hooks.ListenerAdapter {
	private static boolean MAINTAINANCE_MODE = true;

	public MessageListener() {
	}

	public void onMessageReceived(MessageReceivedEvent event) {
		Message message = event.getMessage();
		if (message.getContent().startsWith("> ")) {
			if (MAINTAINANCE_MODE && !event.getAuthor().getId().equals("135607086330544128")) {
				message.getTextChannel()
						.sendMessage("```Sorry, but the bot is being updated. Please try again later!```").queue();
				return;
			}
			String[] words = message.getRawContent().split(" ");
			String commandStr;
			String[] args;
			if (words.length == 1) {
				return;
			} else if (words.length == 2) {
				commandStr = words[1];
				args = new String[] {};
			} else {
				commandStr = words[1];
				args = new String[words.length - 2];
				System.arraycopy(words, 2, args, 0, args.length);
			}
			for (Command command : Command.commands) {
				if (command.isAcceptable(commandStr)) {
					String reply = "";
					try {
						reply = command.performCommand(event, message.getAuthor(), message.getMentionedUsers(), args);
					} catch (Exception e) {
						reply = "An error occurred when performing this command!";
						e.printStackTrace();
					}
					if ((reply != null) && (!reply.equals(""))) {
						message.getTextChannel().sendMessage(reply).queue();
					}
				}
			}
		}
	}
}
