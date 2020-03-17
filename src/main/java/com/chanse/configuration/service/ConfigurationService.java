package com.chanse.configuration.service;

import com.chanse.configuration.domain.dto.InterfaceDecoderConfigurationDto;
import com.chanse.configuration.domain.dto.MessageConfigurationDto;
import com.chanse.configuration.domain.dto.TransportConfigurationDto;

import java.util.List;
import java.util.UUID;

public interface ConfigurationService {

    /**************************************************************************************
     * Messages
     **************************************************************************************/
    List<MessageConfigurationDto> getMessages(String projectName);
    List<MessageConfigurationDto> getMessages(String projectName, String platformName);
    MessageConfigurationDto getMessage(String projectName, String platformName, String messageName);
    MessageConfigurationDto getMessage(UUID specificId);

    UUID editMessage(MessageConfigurationDto messageChanges);

    boolean deleteMessage(String projectName, String platformName, String messageName);

    UUID addMessage(MessageConfigurationDto message);

    /**************************************************************************************
     * InterfaceDecoderConfiguration
     **************************************************************************************/
    List<InterfaceDecoderConfigurationDto> getInterfaceDecoders(String projectName);
    List<InterfaceDecoderConfigurationDto> getInterfaceDecoders(String projectName, String platformName);
    InterfaceDecoderConfigurationDto getInterfaceDecoder(String projectName, String platformName, String configurationName);
    InterfaceDecoderConfigurationDto getInterfaceDecoder(UUID specificId);

    UUID editInterfaceDecoder(InterfaceDecoderConfigurationDto interfaceDecoder);

    boolean deleteInterfaceDecoder(String projectName, String platformName, String configurationName);

    UUID addInterfaceDecoder(InterfaceDecoderConfigurationDto interfaceDecoder);


    /**************************************************************************************
     * TransportConfiguration
     **************************************************************************************/
    List<TransportConfigurationDto> getTransportConfigurations(String projectName);
    List<TransportConfigurationDto> getTransportConfigurations(String projectName, String platformName);
    TransportConfigurationDto getTransportConfiguration(String projectName, String platformName, String configurationName);
    TransportConfigurationDto getTransportConfiguration(UUID specificId);

    UUID editTransportConfiguration(TransportConfigurationDto transport);

    boolean deleteTransportConfiguration(String projectName, String platformName, String configurationName);

    UUID addTransportConfiguration(TransportConfigurationDto transport);
}
