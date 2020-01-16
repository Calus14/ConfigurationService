package com.chanse.configuration.controller;

import com.chanse.configuration.domain.api.ConfigurationServiceApi;
import com.chanse.configuration.domain.dto.InterfaceDecoderConfigurationDto;
import com.chanse.configuration.domain.dto.MessageConfigurationDto;
import com.chanse.configuration.domain.dto.TransportConfigurationDto;
import com.chanse.configuration.domain.dto.UserCredentialsDto;
import com.chanse.configuration.service.ConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/configurationService")
public class ConfigurationServiceController implements ConfigurationServiceApi {

    @Autowired
    protected ConfigurationService configurationService;

    @Override
    public List<MessageConfigurationDto> getConfigurationMessages(String project, String platform) {
        return null;
    }

    @Override
    public MessageConfigurationDto getConfigurationMessage(String project, String platform, String messageName) {
        return null;
    }

    @Override
    public void putConfigurationMessage(MessageConfigurationDto dto) {

    }

    @Override
    public void updateConfigurationMessage(MessageConfigurationDto dto) {

    }

    @Override
    public void deleteConfigurationMessage(String project, String platform, String messageName) {

    }

    @Override
    public List<InterfaceDecoderConfigurationDto> getGetInterfaceDecoders(String project, String platform) {
        return null;
    }

    @Override
    public InterfaceDecoderConfigurationDto getInterfaceDecoder(String project, String platform, String configurationName) {
        return null;
    }

    @Override
    public void putInterfaceDecoder(InterfaceDecoderConfigurationDto dto) {

    }

    @Override
    public void updateInterfaceDecoder(InterfaceDecoderConfigurationDto dto) {

    }

    @Override
    public void deleteInterfaceDecoder(String project, String platform, String configurationName) {

    }

    @Override
    public List<TransportConfigurationDto> getTransportConfigurations(String project, String platform) {
        return null;
    }

    @Override
    public TransportConfigurationDto getTransportConfiguration(String project, String platform, String configurationName) {
        return null;
    }

    @Override
    public void putTransportConfiguration(TransportConfigurationDto dto) {

    }

    @Override
    public void updateTransportConfiguration(TransportConfigurationDto dto) {

    }

    @Override
    public void deleteTransportConfiguration(String project, String platform, String configurationName) {

    }

    @Override
    public UUID addProject(String projectName, UUID userId) {
        return null;
    }

    @Override
    public boolean addProjectUser(String projectName, UUID userId, UserCredentialsDto userDto) {
        return false;
    }

}
