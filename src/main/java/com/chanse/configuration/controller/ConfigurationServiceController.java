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
        List<MessageConfigurationDto> messages = configurationService.getMessages(project, platform);
        // TODO add in the ability to throw a unique 400 message if something goes wrong
        return messages;
    }

    @Override
    public MessageConfigurationDto getConfigurationMessage(String project, String platform, String messageName) {
        MessageConfigurationDto message = configurationService.getMessage(project, platform, messageName);
        return message;
    }

    @Override
    public UUID putConfigurationMessage(MessageConfigurationDto dto) {
        UUID entityId = configurationService.addMessage(dto);
        if(entityId == null){
            //TODO change it to be a throw/catch and then throw a specific type 400 error here
            System.out.println("Error tried to add a message that already exists");
        }
        return entityId;
    }

    @Override
    public UUID updateConfigurationMessage(MessageConfigurationDto dto) {
        UUID entityId = configurationService.editMessage(dto);
        if(entityId == null){
            //TODO change it to be a throw/catch and then throw a specific type 400 error here
            System.out.println("Error tried to add a message that already exists");
        }
        return entityId;
    }

    @Override
    public void deleteConfigurationMessage(String project, String platform, String messageName) {
        boolean msgDeleted = configurationService.deleteMessage(project, platform, messageName);
    }

    @Override
    public List<InterfaceDecoderConfigurationDto> getGetInterfaceDecoders(String project, String platform) {
        List<InterfaceDecoderConfigurationDto> interfaceConfigs = configurationService.getInterfaceDecoders(project, platform);
        //TODO allow for us to call to the service and send a 400 custom message if something goes wrong
        return interfaceConfigs;
    }

    @Override
    public InterfaceDecoderConfigurationDto getInterfaceDecoder(String project, String platform, String configurationName) {
        InterfaceDecoderConfigurationDto dto = configurationService.getInterfaceDecoder(project, platform, configurationName);
        //TODO allow for us to call to the service and send a 400 custom message if something goes wrong
        return dto;
    }

    @Override
    public UUID putInterfaceDecoder(InterfaceDecoderConfigurationDto dto) {
        UUID entityId = configurationService.addInterfaceDecoder(dto);
        if(entityId == null){
            //TODO change it to be a throw/catch and then throw a specific type 400 error here
            System.out.println("Error tried to add a interfaceDecoder that already exists");
        }
        return entityId;
    }

    @Override
    public UUID updateInterfaceDecoder(InterfaceDecoderConfigurationDto dto) {
        UUID entityId = configurationService.editInterfaceDecoder(dto);
        if(entityId == null){
            //TODO change it to be a throw/catch and then throw a specific type 400 error here
            System.out.println("Error tried to edit a interfaceDecoder that already exists");
        }
        return entityId;
    }

    @Override
    public void deleteInterfaceDecoder(String project, String platform, String configurationName) {
        boolean deleted = configurationService.deleteInterfaceDecoder(project, platform, configurationName);
        if(!deleted){
            //TODO change it to be a throw/catch and then throw a specific type 400 error here
            System.out.println("Error tried to edit a interfaceDecoder that already exists");
        }
    }

    @Override
    public List<TransportConfigurationDto> getTransportConfigurations(String project, String platform) {
        List<TransportConfigurationDto> transportConfigs = configurationService.getTransportConfigurations(project, platform);
        //TODO catch and throw a custom 400
        return transportConfigs;
    }

    @Override
    public TransportConfigurationDto getTransportConfiguration(String project, String platform, String configurationName) {
        TransportConfigurationDto transportConfig = configurationService.getTransportConfiguration(project, platform, configurationName);
        //TODO catch and throw a custom 400 if you need to
        return transportConfig;
    }

    @Override
    public UUID putTransportConfiguration(TransportConfigurationDto dto) {
        UUID entityId = configurationService.addTransportConfiguration(dto);
        if(entityId == null){
            //TODO change it to be a throw/catch and then throw a specific 400 error here
            System.out.println("Error tried to add a transport Configuration that already exists");
        }
        return entityId;
    }

    @Override
    public UUID updateTransportConfiguration(TransportConfigurationDto dto) {
        UUID entityId = configurationService.editTransportConfiguration(dto);
        if(entityId == null){
            //TODO change it to be a throw/catch and then throw a specific 400 error here
            System.out.println("Error tried to edit a transport Configuration that already exists");
        }
        return entityId;
    }

    @Override
    public void deleteTransportConfiguration(String project, String platform, String configurationName) {
        boolean deleted = configurationService.deleteTransportConfiguration(project, platform, configurationName);
        if(!deleted){
            //TODO
            System.out.println("Error trying to delete the transport Configuration");
        }
    }

    @Override
    public UUID addProject(String projectName, UUID userId) {
        // TODO
        return null;
    }

    @Override
    public boolean addProjectUser(String projectName, UUID userId, UserCredentialsDto userDto) {
        // TODO
        return false;
    }

}
