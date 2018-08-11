package com.ds.sample.eureka;

import java.net.URI;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.event.InstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.CloudEurekaInstanceConfig;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.appinfo.ApplicationInfoManager;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRetry
@Slf4j
public class EurekaClientApp {

    @Value("${random.uuid}")
    private String token;

    @Autowired
    private ApplicationInfoManager aim;

    private RestTemplate restTemplate = new RestTemplateBuilder().build();

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApp.class, args);
    }

    /**
     * Set token in metadata at application startup
     */
    @PostConstruct
    public void init() {
        aim.getInfo()
           .getMetadata()
           .put("token", token);
    }

    /**
     * Listen to {@link InstanceRegisteredEvent} which is triggered when the
     * instance re-registers with Eureka on <code>/refresh</code>, and then update
     * Eureka instance
     * 
     * @param event
     */
    @EventListener
    @Retryable(backoff = @Backoff(delay = 5000))
    public void onInstanceRegistered(InstanceRegisteredEvent<CloudEurekaInstanceConfig> event) {
        updateEureka(event.getConfig()
                          .getAppname(), event.getConfig()
                                              .getInstanceId());
    }

    /**
     * @param appname
     * @param instanceId
     */
    private void updateEureka(String appName, String instanceId) {
        URI updateInstanceMetadataUri = UriComponentsBuilder.fromHttpUrl(EurekaClientConfigBean.DEFAULT_URL)
                                                            .path("/apps/")
                                                            .path(appName)
                                                            .path("/")
                                                            .path(instanceId)
                                                            .path("/metadata")
                                                            .queryParam("token", token)
                                                            .build()
                                                            .toUri();
        try {
            restTemplate.put(updateInstanceMetadataUri, null);
            log.info("Successfully updated metadata for App [{}] and InstanceId [{}]", appName, instanceId);
        } catch (HttpServerErrorException e) {
            log.error("Unable to update metadata for App [{}] and InstanceId [{}]", appName, instanceId, e);
            throw e;
        }

    }

}
