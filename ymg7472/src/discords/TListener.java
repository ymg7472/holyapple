package discords;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import schoolfood.MenuCrawl;
import java.io.IOException;
import java.util.List;
public class TListener extends ListenerAdapter{
	List<SentimentalDic> dicList;
	public TListener(List<SentimentalDic> dicList) {
		this.dicList = dicList;
	}
	@Override
	public void onMessageReceived (MessageReceivedEvent event) {
		User user = event.getAuthor();;
		MenuCrawl m = new MenuCrawl();
		TextChannel tc = event.getTextChannel(); 
		Message msg = event.getMessage();
		if(user.isBot()) return; 
		if(msg.getContentRaw().charAt(0) == '!') {
			String[] args = msg.getContentRaw().substring(1).split(" ");
			if(args.length <= 0) return;
			if(args[0].equalsIgnoreCase("급식")) {
				String date = "";
				date = args[1];
				tc.sendMessage(m.menu(date)).queue();
			}


		}
		for(int i=0; i<dicList.size(); i++) {
			if(dicList.get(i).getWord().equals(msg.getContentRaw()) || dicList.get(i).getWord_root().equals(msg.getContentRaw())) {
				int polarity = Integer.parseInt(dicList.get(i).getPolarity());
				if(polarity == 2) {
					tc.sendMessage(user.getName()+"의 기분은 환상적이군요!").queue();
				}else if(polarity == 1) {
					tc.sendMessage(user.getName()+"의 기분은 좋네요").queue();
				}else if(polarity == 0) {
					tc.sendMessage(user.getName()+"의 기분은 그냥 그렇네요").queue();
				}else if(polarity == -1) {
					tc.sendMessage(user.getName()+"의 기분은 조금 좋지 않군요").queue();
				}else if(polarity == -2) {
					tc.sendMessage(user.getName()+"의 기분은 최악이네요").queue();
				}
			}
		}
		
			
	}

}
