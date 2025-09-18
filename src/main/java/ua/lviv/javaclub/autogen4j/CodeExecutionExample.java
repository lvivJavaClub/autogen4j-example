package ua.lviv.javaclub.autogen4j;

import com.hw.autogen4j.agent.AssistantAgent;
import com.hw.autogen4j.agent.UserProxyAgent;
import com.hw.autogen4j.entity.CodeExecutionConfig;

import static com.hw.autogen4j.entity.HumanInputMode.NEVER;

public class CodeExecutionExample {
    public static void main(String[] args) {
        CodeExecutionConfig codeExecutionConfig = CodeExecutionConfig.builder()
                .workDir("/tmp/data/coding")
//                .docker("python:3.11-sli")
                .timeout(100)
                .build();

        UserProxyAgent userProxy = UserProxyAgent.builder()
                .name("user_proxy")
                .humanInputMode(NEVER)
                .maxConsecutiveAutoReply(10)
                .isTerminationMsg(e -> e.getContent().strip().endsWith("TERMINATE"))
                .codeExecutionConfig(codeExecutionConfig)
                .build();

        AssistantAgent assistant = AssistantAgent.builder()
                .name("assistant")
                .build();


        userProxy.initiateChat(assistant,
                "What date is today?");

        userProxy.send(assistant,
                "How meny days to new year?");
    }
}