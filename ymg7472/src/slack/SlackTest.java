package slack;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;

public class SlackTest {

	public static void main(String[] args) {
		SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
		SlackAttachment attach = new SlackAttachment();
		attach.setTitle("TEST");
		attach.setColor("#323330");
		attach.setFallback("점검이 필요합니다.");
		attach.setTimestamp(new java.util.Date());
		attach.setText("text message");
		attach.setImageUrl("이미지경로");
		api.call(new SlackMessage("Test Title").addAttachments(attach));

	}

}
