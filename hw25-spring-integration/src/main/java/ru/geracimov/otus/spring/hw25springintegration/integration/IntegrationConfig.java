package ru.geracimov.otus.spring.hw25springintegration.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import ru.geracimov.otus.spring.hw25springintegration.domain.Author;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedDelay(1000)
                      .get();
    }

    @Bean("allAuthorsChannelInput")
    public MessageChannel getAllAuthorChannel() {
        return MessageChannels.direct()
                              .get();
    }

    @Bean("allAuthorsChannelOutput")
    public PublishSubscribeChannel getAllAuthorOutputChannel() {
        return MessageChannels.publishSubscribe()
                              .datatype(List.class)
                              .get();
    }

    @Bean
    public IntegrationFlow allAuthorIntegrationFlow() {
        return IntegrationFlows.from("allAuthorsChannelInput")
                               .handle("authorService", "getAllAuthors")
                               .channel("allAuthorsChannelOutput")
                               .get();
    }

    /////////////////////////////////////////////////////////

    @Bean("simpleAuthorChannelInput")
    public MessageChannel getSimpleAuthorChannel() {
        return MessageChannels.direct()
                              .datatype(Long.class)
                              .get();
    }

    @Bean("simpleAuthorChannelOutput")
    public PublishSubscribeChannel getSimpleAuthorOutputChannel() {
        return MessageChannels.publishSubscribe()
                              .datatype(Optional.class)
                              .get();
    }

    @Bean
    public IntegrationFlow simpleAuthorIntegrationFlow() {
        return IntegrationFlows.from("simpleAuthorChannelInput")
                               .handle("authorService", "getAuthorById")
                               .channel("simpleAuthorChannelOutput")
                               .get();
    }

    /////////////////////////////////////////////////////////

    @Bean("deleteAuthorChannelInput")
    public MessageChannel getDeleteAuthorChannel() {
        return MessageChannels.direct()
                              .datatype(Author.class)
                              .get();
    }

    @Bean("deleteAuthorChannelOutput")
    public PublishSubscribeChannel getDeleteAuthorOutputChannel() {
        return MessageChannels.publishSubscribe()
                              .datatype(boolean.class)
                              .get();
    }

    @Bean
    public IntegrationFlow deleteAuthorIntegrationFlow() {
        return IntegrationFlows.from("deleteAuthorChannelInput")
                               .handle("authorService", "delete")
                               .channel("deleteAuthorChannelOutput")
                               .get();
    }
    /////////////////////////////////////////////////////////

    @Bean("addAuthorChannelInput")
    public MessageChannel getAddAuthorChannel() {
        return MessageChannels.direct()
                              .datatype(Author.class)
                              .get();
    }

    @Bean("addAuthorChannelOutput")
    public PublishSubscribeChannel getAddAuthorOutputChannel() {
        return MessageChannels.publishSubscribe()
                              .datatype(void.class)
                              .get();
    }

    @Bean
    public IntegrationFlow addAuthorIntegrationFlow() {
        return IntegrationFlows.from("addAuthorChannelInput")
                               .handle("authorService", "save")
                               .channel("addAuthorChannelOutput")
                               .get();
    }

}
