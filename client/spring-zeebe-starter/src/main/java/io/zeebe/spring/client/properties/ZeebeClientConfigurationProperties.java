package io.zeebe.spring.client.properties;

import static io.zeebe.spring.client.config.ZeebeClientSpringConfiguration.DEFAULT;

import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@ConfigurationProperties(prefix = "zeebe.client")
public class ZeebeClientConfigurationProperties implements ZeebeClientProperties {

  @NestedConfigurationProperty
  private Broker broker = new Broker();

  @NestedConfigurationProperty
  private Worker worker = new Worker();

  @NestedConfigurationProperty
  private Message message = new Message();

  @Data
  public static class Broker {

    private String contactPoint = DEFAULT.getBrokerContactPoint();
    private Duration requestTimeout = DEFAULT.getDefaultRequestTimeout();
  }

  @Data
  public static class Worker {
    private String name = DEFAULT.getDefaultJobWorkerName();
    private Duration timeout = DEFAULT.getDefaultJobTimeout();
    private Integer maxJobsActive = DEFAULT.getDefaultJobWorkerMaxJobsActive();
    private Duration pollInterval = DEFAULT.getDefaultJobPollInterval();
    private Integer threads = DEFAULT.getNumJobWorkerExecutionThreads();
  }

  @Data
  public static class Message {
    private Duration timeToLive = DEFAULT.getDefaultMessageTimeToLive();
  }

  @Override
  public String getBrokerContactPoint() {
    return broker.getContactPoint();
  }

  @Override
  public Duration getDefaultRequestTimeout() {
    return broker.getRequestTimeout();
  }

  @Override
  public int getNumJobWorkerExecutionThreads() {
    return worker.getThreads();
  }

  @Override
  public int getDefaultJobWorkerMaxJobsActive() {
    return worker.getMaxJobsActive();
  }

  @Override
  public String getDefaultJobWorkerName() {
    return worker.getName();
  }

  @Override
  public Duration getDefaultJobTimeout() {
    return worker.getTimeout();
  }

  @Override
  public Duration getDefaultJobPollInterval() {
    return worker.getPollInterval();
  }

  @Override
  public Duration getDefaultMessageTimeToLive() {
    return message.getTimeToLive();
  }
  
}
