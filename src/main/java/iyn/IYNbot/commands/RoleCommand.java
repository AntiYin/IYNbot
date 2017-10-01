package iyn.IYNbot.commands;

import java.util.Arrays;
import java.util.List;
import java.awt.Color;

import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildController;
import net.dv8tion.jda.core.managers.RoleManager;
import iyn.IYNbot.data.RoleData;

public class RoleCommand extends Command {

	@Override
	public String getCommand() {
		return "myrole";
	}
	
	@Override
	public String getUsage() {
		return "```> myrole\n"
				+ "    Makes or changes your role to a specified name and color.\n"
				+ "> myrole make [name] [color]\n"
				+ "    Makes your role.\n"
				+ "> myrole edit name [name]\n"
				+ "    Changes the name of your role.\n"
				+ "> myrole edit color [color]\n"
				+ "    Changes the color of your role.\n\n"
				+ "Note: [color] should be specified in hexadecimal notation, like 24A7B4.```";
	}
	
	@Override
	public String performCommand(MessageReceivedEvent e, User user, List<User> mentions, String[] args) {
		
		if (!e.getGuild().getMembersWithRoles(e.getGuild().getRoleById("362371181824966666")).contains(e.getGuild().getMember(user))) {
			return "You must be a Friendo on this server to make your own role!";
		}
		
		if (args.length == 0) {
			return getUsage();
		}
		
		GuildController gc = new GuildController(e.getGuild());
		
		RoleData data = RoleData.getData(user.getId());
		
		if (args[0].equals("make")) {
			if (data.roleID == null) {
				if (args.length < 3) {
					return getUsage();
				}
				String colorString = args[3].toUpperCase();
				if (colorString.length() != 6) {
					return getUsage();
				}
				for (int i = 0; i < colorString.length(); i++){
				    char c = colorString.charAt(i);
				    if (!"1234567890ABCDEF".contains(Character.toString(c))) {
				    	return getUsage();
				    }
				}
				Color clr = new Color(
			            Integer.valueOf( colorString.substring( 0, 2 ), 16 ),
			            Integer.valueOf( colorString.substring( 2, 4 ), 16 ),
			            Integer.valueOf( colorString.substring( 4, 6 ), 16 ) );
				
				Role r = gc.createRole().setName(args[1]).setColor(clr).complete();
				
				gc.addSingleRoleToMember(e.getGuild().getMember(user), r).queue();
				
				data.roleID = r.getId();
				
				data.save();
				
				return "Role made and assigned successfully!";
			} else {
				System.out.println("Already Have Role.");
				return "You already have a role! Use `> myrole edit name|color [arg]` to modify it instead!";
			}
		} else if (args[1].equals("edit")) {
			if (data.roleID != null) {
				if (args.length < 3) {
					return getUsage();
				}
				
				RoleManager rm = new RoleManager(e.getGuild().getRoleById(data.roleID));
				
				if (args[2].equals("name")) {
					rm.setName(args[2]).queue();
					
					return "Name set successfully!";
				} else if (args[2].equals("color")) {
					for (int i = 0; i < args[2].length(); i++){
					    char c = args[3].toUpperCase().charAt(i);
					    if (!"1234567890ABCDEF".contains(Character.toString(c))) {
					    	return getUsage();
					    }
					}
					Color c = new Color(
			            Integer.valueOf( args[2].substring( 0, 2 ), 16 ),
			            Integer.valueOf( args[2].substring( 2, 4 ), 16 ),
			            Integer.valueOf( args[2].substring( 4, 6 ), 16 ) );
					
					rm.setColor(c).queue();
					
					return "Color set successfully!";
				} else {
					return getUsage();
				}
			} else {
				return "You don't have a role yet! Make one using `> myrole make [name] [color]`!";
			}
		}
		
		return getUsage();
	}

}
