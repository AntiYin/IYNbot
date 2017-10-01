package iyn.IYNbot.commands;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class FeedbackCommand extends Command {

	@Override
	public String getUsage() {
		return "```> feedback [feedback]\n"
				+ "    Send the creator of this bot, IYN#2576, some feedback!```";
	}
	
	@Override
	public String getCommand() {
		return "feedback";
	}

	@Override
	public String performCommand(MessageReceivedEvent e, User user, List<User> mentions, String[] args) {
		File folder = new File("./data/feedback");
		folder.mkdirs();
		File file = new File(folder, "feedback.txt");
		
		try {
			FileWriter fw = new FileWriter(file, true);
			String feedback = String.join(" ", args);
			fw.write(user.getId() + " (" + user.getName() + ") - " + feedback + System.getProperty("line.separator"));
			fw.close();
		} catch (Exception e1) {
			try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file, true);
				String feedback = String.join(" ", args);
				fw.write(user.getId() + " (" + user.getName() + ") - " + feedback + System.getProperty("line.separator"));
				fw.close();
			} catch (IOException e2) {
				return "Feedback not sent. Something bad happened. :(";
			}
		}
		
		return "Feedback sent!";
	}

}
