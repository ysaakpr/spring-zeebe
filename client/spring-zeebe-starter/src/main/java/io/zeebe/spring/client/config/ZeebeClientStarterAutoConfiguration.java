package io.zeebe.spring.client.config;

import io.zeebe.client.ZeebeClientBuilder;
import io.zeebe.client.impl.ZeebeClientBuilderImpl;
import io.zeebe.spring.client.properties.ZeebeClientConfigurationProperties;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@EnableConfigurationProperties(ZeebeClientConfigurationProperties.class)
@Configuration
@RequiredArgsConstructor
public class ZeebeClientStarterAutoConfiguration {

  private final ZeebeClientConfigurationProperties configurationProperties;
  
  @Bean
  @Primary
  public ZeebeClientBuilder builder() {
    final ZeebeClientBuilderImpl builder = new ZeebeClientBuilderImpl();

    builder.brokerContactPoint(configurationProperties.getBrokerContactPoint());
    builder.defaultJobPollInterval(configurationProperties.getDefaultJobPollInterval());
    builder.defaultJobTimeout(configurationProperties.getDefaultJobTimeout());
    builder.defaultJobWorkerMaxJobsActive(configurationProperties.getDefaultJobWorkerMaxJobsActive());
    builder.defaultJobWorkerName(configurationProperties.getDefaultJobWorkerName());
    builder.defaultMessageTimeToLive(configurationProperties.getDefaultMessageTimeToLive());
    
    return builder;
  }
}
