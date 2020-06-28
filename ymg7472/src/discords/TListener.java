package discords;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import crawling.CrawlUtill;
public class TListener extends ListenerAdapter{
	List<SentimentalDic> dicList;
	public TListener(List<SentimentalDic> dicList) {
		this.dicList = dicList;
	}

	@Override
	public void onMessageReceived (MessageReceivedEvent event) {
		User user = event.getAuthor();;
		EmbedBuilder result = new EmbedBuilder();
		CrawlUtill cr = new CrawlUtill();
		TextChannel tc = event.getTextChannel(); 
		Message msg = event.getMessage();
		if(user.isBot()) return; 
		if(msg.getContentRaw().charAt(0) == '!') {
			String[] args = msg.getContentRaw().substring(1).split(" ");
			if(args.length <= 0) return;
			if(args[0].equalsIgnoreCase("급식")) {
				String date = "";
				date = args[1];
				if(!cr.menu(date).equals(null)) {
					try {
						tc.sendMessage(cr.menu(date)).queue();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					tc.sendMessage("찾을 수 없습니다.").queue();
				}
			}else if(args[0].equalsIgnoreCase("코로나")){
				tc.sendMessage(cr.korona()).queue();
			}else if(args[0].equalsIgnoreCase("명령어")){
				tc.sendMessage("!급식 + 날짜 : 그날의 급식을 출력합니다. 예) !급식 20200527" + "\n" + "!코로나 : 국내 코로나 현황을 출력합니다.").queue();
			}else if(args[0].equalsIgnoreCase("엄준식")){
				result.setTitle("엄");
		        result.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR-Fd_f90rUmrvT_VCxDsdfQ51LtlVHyNykO-FgMjpcLv6O8u1J&usqp=CAU");
		        event.getChannel().sendMessage(result.build()).queue();
			}


		}
//		for(int i=0; i<dicList.size(); i++) {
//			if(dicList.get(i).getWord().equals(msg.getContentRaw()) || dicList.get(i).getWord_root().equals(msg.getContentRaw())) {
//				int polarity = Integer.parseInt(dicList.get(i).getPolarity());
//				if(polarity == 2) {
//					tc.sendMessage(user.getName()+"의 기분은 환상적이군요!").queue();
//				}else if(polarity == 1) {
//					tc.sendMessage(user.getName()+"의 기분은 좋네요").queue();
//				}else if(polarity == 0) {
//					tc.sendMessage(user.getName()+"의 기분은 그냥 그렇네요").queue();
//				}else if(polarity == -1) {
//					tc.sendMessage(user.getName()+"의 기분은 조금 좋지 않군요").queue();
//				}else if(polarity == -2) {
//					tc.sendMessage(user.getName()+"의 기분은 최악이네요").queue();
//				}
//			}
//		}
		
			
	}

}
