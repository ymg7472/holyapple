package discord_sentimental;

import java.util.List;

import discords.SentimentalDic;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class TListener1 extends ListenerAdapter{
	List<SentimentalDic> dicList;
	
	public TListener1(List<SentimentalDic> dicList) {
		this.dicList = dicList;
	}
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		User user = event.getAuthor();
		TextChannel tc = event.getTextChannel();
		Message msg = event.getMessage();
		if(user.isBot()) return;
		if(msg.getContentRaw().charAt(0) == '!') {
			String[] args = msg.getContentRaw().substring(1).split(" ");
			if(args.length < 0) return;
			if(args.length == 2 && args[0].equalsIgnoreCase("botstart")) {
				
			}
		}
		if(msg.getContentRaw().charAt(0) != '!') {
			
		}
	}

}