package iyn.IYNbot.IYNbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game.GameType;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import iyn.IYNbot.commands.Command;

public class App implements EventListener {
	
	public static JDA jda;

	public App() {

	}

	public static void main(String[] args) {
		try {
			Command.RegisterCommands();
			BufferedReader reader = new BufferedReader(new FileReader(new File("./token.secret")));
			jda = new JDABuilder(AccountType.BOT).setToken(reader.readLine()).addEventListener(new App()).buildBlocking();
			reader.close();
			jda.addEventListener(new MessageListener());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onEvent(Event event) {
		if ((event instanceof ReadyEvent)) {
			ReadyEvent ready = (ReadyEvent) event;
			System.out.println("API is ready!");
		}
	}
}
