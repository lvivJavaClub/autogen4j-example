package ua.lviv.javaclub.autogen4j;

import com.hw.autogen4j.agent.AssistantAgent;
import com.hw.autogen4j.agent.UserProxyAgent;
import com.hw.autogen4j.agent.group.GroupChat;
import com.hw.autogen4j.agent.group.GroupChatManager;

import java.io.IOException;
import java.util.List;


public class GroupChatExample {

    public static void main(String[] args) throws IOException {
        UserProxyAgent user = UserProxyAgent.builder()
                .name("user")
                .systemMessage("A human who delegates tasks.")
                .isTerminationMsg(msg -> {
                    String c = msg.getContent();
                    return c != null && c.strip().endsWith("TERMINATE");
                })
                .maxConsecutiveAutoReply(3)
                .build();

        AssistantAgent coder = AssistantAgent.builder()
                .name("coder")
                .systemMessage("You are an associate Java programmer. Write correct and efficient Java code. You just return code. Avoid to return explanations.")
                .build();

        AssistantAgent reviewer = AssistantAgent.builder()
                .name("reviewer")
                .systemMessage("You are a strict senior engineer. Review code, suggest improvements. Avoid to show return snippets.")
                .build();

        GroupChat groupChat = GroupChat.builder()
                .agents(List.of(user, coder, reviewer))
                .maxRound(12)
                .build();

        GroupChatManager manager = GroupChatManager.builder()
                .groupChat(groupChat)
                .build();

        user.initiateChat(manager,
                "Hello coder, can you write a Java function that reverses a string? After that, reviewer should provide feedback on it.");

    }
}
