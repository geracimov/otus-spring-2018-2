package ru.geracimov.otus.spring.hw25springintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Objects;

@SpringBootApplication
@IntegrationComponentScan
public class SpringIntegrationApp {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringIntegrationApp.class, args);

        PollableChannel channel1 = ctx.getBean("channel1", PollableChannel.class);
        DirectChannel channel2 = ctx.getBean("channel2", DirectChannel.class);

//        channel1.subscribe((message) -> channel2.send(message));
        channel2.subscribe(System.out::println);

        Runnable r = () -> System.out.println("sdsd");

        new Thread(() -> {
            while (true) {
                channel2.send(Objects.requireNonNull(channel1.receive()));
            }
        }).start();

        new Thread(r).start();

        channel1.send(MessageBuilder.withPayload("Hello")
                                    .build());
        channel1.send(MessageBuilder.withPayload("Hello2")
                                    .build());


        Thread.sleep(2000);

        channel1.send(MessageBuilder.withPayload("Hello3")
                                    .build());

        Thread.sleep(100000);
    }

    @Bean
    public PollableChannel channel1() {
        return new QueueChannel(100);
    }

    @Bean
    public DirectChannel channel2() {
        return MessageChannels.direct("channel2")
                              .get();
    }
}
