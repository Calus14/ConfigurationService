package com.chanse.configuration.domain.client;

import com.chanse.configuration.domain.api.ConfigurationServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="ConfigurationServiceClient", url="${configuration.server.ipAddress}:${configuration.server.port}/configurationService")
public interface ConfigurationServiceClient extends ConfigurationServiceApi {
}
