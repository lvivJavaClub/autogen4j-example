package ua.lviv.javaclub.autogen4j;

import com.hw.autogen4j.agent.AssistantAgent;
import com.hw.autogen4j.agent.UserProxyAgent;
import com.hw.autogen4j.agent.group.GroupChat;
import com.hw.autogen4j.agent.group.GroupChatManager;

import java.util.List;

public class AssistantAgentExample {
    public static void main(String[] args) {
        UserProxyAgent user = UserProxyAgent.builder()
                .name("user")
                .systemMessage("A human who delegates tasks.")
                .maxConsecutiveAutoReply(8)
                .build();

        AssistantAgent coder = AssistantAgent.builder()
                .name("coder")
                .systemMessage(
                        "You are an associate Java programmer. Write correct and efficient Java code. You just return code. Avoid to return explanations.")
                .build();

        GroupChat groupChat = GroupChat.builder()
                .adminName("user")
                .agents(List.of(user, coder))
                .maxRound(1)
                .build();

        GroupChatManager manager = GroupChatManager.builder()
                .name("group name")
                .groupChat(groupChat)
                .build();

        user.initiateChat(manager,
                "Hello coder, can you write a Java function that reverses a string? After that, reviewer should provide feedback on it.");

        user.send(coder, "improve code");
//        user.initiateChat(manager, "improve code");
    }
}
