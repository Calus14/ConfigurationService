package com.chanse.configuration.service;

import com.chanse.comsim.domain.client.ComSimClient;
import com.chanse.comsim.domain.dto.SendMessageDto;
import com.chanse.comsim.domain.dto.TransportServiceDto;
import com.chanse.configuration.config.MapperConfig;
import com.chanse.configuration.domain.dto.InterfaceDecoderConfigurationDto;
import com.chanse.configuration.domain.dto.MessageConfigurationDto;
import com.chanse.configuration.domain.dto.TransportConfigurationDto;
import com.chanse.configuration.repository.InterfaceDecoderConfigurationRepository;
import com.chanse.configuration.repository.MessageConfigurationRepository;
import com.chanse.configuration.repository.TransportConfigurationRepository;
import com.chanse.configuration.repository.entities.InterfaceDecoderConfigurationEntity;
import com.chanse.configuration.repository.entities.MessageConfigurationEntity;
import com.chanse.configuration.repository.entities.TransportConfigurationEntity;
import com.chanse.messaging.fields.IntegerDataField;
import com.chanse.messaging.fields.InterfaceDataField;
import com.chanse.messaging.fields.StaticDataField;
import com.chanse.messaging.messages.StandardMessage;
import com.chanse.messaging.msginterface.StaticIdDecoder;
import com.chanse.messaging.transport.TCPClientTransportationService;
import com.chanse.messaging.transport.TCPServerTransportationService;
import com.chanse.messaging.utils.SaveLoadUtils;
import com.chanse.messaging.words.StandardDataWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ConfigurationServiceImpl implements ConfigurationService{

    @Autowired
    public ComSimClient myComSimClient;

    @Autowired
    public InterfaceDecoderConfigurationRepository interfaceDecoderConfigurationRepository;

    @Autowired
    public MessageConfigurationRepository messageConfigurationRepository;

    @Autowired
    public TransportConfigurationRepository transportConfigurationRepository;

    @Override
    public List<MessageConfigurationDto> getMessages(String projectName) {
        List<MessageConfigurationDto> messageConfigDtos = new  ArrayList();
        List<MessageConfigurationEntity> messageConfigEntities = messageConfigurationRepository.findByProjectName(projectName);
        messageConfigEntities.stream().forEach( entity -> {
            messageConfigDtos.add(MapperConfig.getMessageDtoFromEntity(entity));
        });

        return messageConfigDtos;
    }

    @Override
    public List<MessageConfigurationDto> getMessages(String projectName, String platformName) {
        List<MessageConfigurationDto> messageConfigDtos = new ArrayList();
        List<MessageConfigurationEntity> messageConfigurationEntities = messageConfigurationRepository.findByPlatformNameAndProjectName(platformName, projectName);
        messageConfigurationEntities.stream().forEach( entity -> {
            messageConfigDtos.add(MapperConfig.getMessageDtoFromEntity(entity));
        });

        return messageConfigDtos;
    }

    @Override
    public MessageConfigurationDto getMessage(String projectName, String platformName, String messageName) {
        MessageConfigurationEntity entity =  messageConfigurationRepository.
                                findFirstByConfigurationNameAndPlatformNameAndProjectName(messageName, platformName, projectName);
        MessageConfigurationDto dto = entity == null ? null : MapperConfig.getMessageDtoFromEntity(entity);
        return dto;
    }

    @Override
    public MessageConfigurationDto getMessage(UUID specificId) {
        MessageConfigurationEntity entity =  messageConfigurationRepository.findById(specificId).get();
        MessageConfigurationDto dto = entity == null ? null : MapperConfig.getMessageDtoFromEntity(entity);
        return dto;
    }

    @Override
    public UUID editMessage(MessageConfigurationDto messageChanges) {
        MessageConfigurationEntity entity = messageConfigurationRepository.
                findFirstByConfigurationNameAndPlatformNameAndProjectName(messageChanges.getConfigurationName(),
                                                                    messageChanges.getPlatformName(),
                                                                    messageChanges.getProjectName() );
        if( entity == null ){
            // TODO handle a bad edit message
            System.out.println("Error, no known message for of name "+messageChanges.getConfigurationName() + " in project:"
                                + messageChanges.getProjectName() + " with platform " + messageChanges.getPlatformName() + " found");
            return null;
        }

        entity.setMessageAsJson(messageChanges.getMessageAsJson());
        messageConfigurationRepository.save(entity);
        return entity.getConfigId();
    }

    @Override
    public boolean deleteMessage(String projectName, String platformName, String messageName) {
        MessageConfigurationEntity entity = messageConfigurationRepository.
                findFirstByConfigurationNameAndPlatformNameAndProjectName(messageName,
                        platformName,
                        projectName );
        if( entity == null ){
            // TODO handle a bad edit message
            System.out.println("Error, no known message for of name "+ messageName + " in project:"
                    + projectName + " with platform " + platformName + " found");
            return false;
        }

        messageConfigurationRepository.delete(entity);
        return true;
    }

    @Override
    public UUID addMessage(MessageConfigurationDto message) {
        MessageConfigurationEntity entity = messageConfigurationRepository.
                findFirstByConfigurationNameAndPlatformNameAndProjectName(message.getConfigurationName(),
                        message.getPlatformName(),
                        message.getProjectName() );
        if( entity != null ){
            // TODO handle a bad add message
            System.out.println("Error, trying to add a message which already exists");
            return null;
        }
        entity = MapperConfig.getMessageEntityFromDto(message);
        messageConfigurationRepository.save(entity);
        return entity.getConfigId();
    }

    @Override
    public List<InterfaceDecoderConfigurationDto> getInterfaceDecoders(String projectName) {
        List<InterfaceDecoderConfigurationDto> interfaceDecoderConfigDtos = new  ArrayList();
        List<InterfaceDecoderConfigurationEntity> interfaceDecoderConfigEntities =
                interfaceDecoderConfigurationRepository.findByProjectName(projectName);
        interfaceDecoderConfigEntities.stream().forEach( entity -> {
            interfaceDecoderConfigDtos.add(MapperConfig.getInterfaceDtoFromEntity(entity));
        });

        return interfaceDecoderConfigDtos;
    }

    @Override
    public List<InterfaceDecoderConfigurationDto> getInterfaceDecoders(String projectName, String platformName) {
        List<InterfaceDecoderConfigurationDto> interfaceDecoderConfigDtos = new  ArrayList();
        List<InterfaceDecoderConfigurationEntity> interfaceDecoderConfigEntities =
                interfaceDecoderConfigurationRepository.findByPlatformNameAndProjectName(platformName, projectName);
        interfaceDecoderConfigEntities.stream().forEach( entity -> {
            interfaceDecoderConfigDtos.add(MapperConfig.getInterfaceDtoFromEntity(entity));
        });

        return interfaceDecoderConfigDtos;
    }

    @Override
    public InterfaceDecoderConfigurationDto getInterfaceDecoder(String projectName, String platformName, String configurationName) {
        InterfaceDecoderConfigurationEntity entity = interfaceDecoderConfigurationRepository
                .findFirstByConfigurationNameAndPlatformNameAndProjectName(configurationName, platformName, projectName);
        InterfaceDecoderConfigurationDto dto = entity == null ? null : MapperConfig.getInterfaceDtoFromEntity(entity);
        return dto;
    }

    @Override
    public InterfaceDecoderConfigurationDto getInterfaceDecoder(UUID specificId) {
        InterfaceDecoderConfigurationEntity entity = interfaceDecoderConfigurationRepository.findById(specificId).get();
        InterfaceDecoderConfigurationDto dto = entity == null ? null : MapperConfig.getInterfaceDtoFromEntity(entity);
        return dto;
    }

    @Override
    public UUID editInterfaceDecoder(InterfaceDecoderConfigurationDto interfaceDecoder) {
        InterfaceDecoderConfigurationEntity entity = interfaceDecoderConfigurationRepository.
                findFirstByConfigurationNameAndPlatformNameAndProjectName(interfaceDecoder.getConfigurationName(),
                        interfaceDecoder.getPlatformName(),
                        interfaceDecoder.getProjectName() );
        if( entity == null ){
            // TODO handle a bad edit message
            System.out.println("Error, no known interface decoder for of name "+interfaceDecoder.getConfigurationName() + " in project:"
                    + interfaceDecoder.getProjectName() + " with platform " + interfaceDecoder.getPlatformName() + " found");
            return null;
        }

        entity.setUserComments(interfaceDecoder.getUserComments());
        entity.setInterfaceDecoderAsJson(interfaceDecoder.getInterfaceDecoderAsJson());

        interfaceDecoderConfigurationRepository.save(entity);
        return entity.getConfigId();
    }

    @Override
    public boolean deleteInterfaceDecoder(String projectName, String platformName, String configurationName) {
        InterfaceDecoderConfigurationEntity entity = interfaceDecoderConfigurationRepository.
                findFirstByConfigurationNameAndPlatformNameAndProjectName(configurationName,
                        platformName,
                        projectName );
        if( entity == null ){
            // TODO handle a bad edit message
            System.out.println("Error, no known interface decoder for of name "+configurationName + " in project:"
                    + projectName + " with platform " + platformName + " found");
            return false;
        }

        interfaceDecoderConfigurationRepository.delete(entity);
        return true;
    }

    @Override
    public UUID addInterfaceDecoder(InterfaceDecoderConfigurationDto interfaceDecoder) {
        InterfaceDecoderConfigurationEntity entity = interfaceDecoderConfigurationRepository.
                findFirstByConfigurationNameAndPlatformNameAndProjectName(interfaceDecoder.getConfigurationName(),
                        interfaceDecoder.getPlatformName(),
                        interfaceDecoder.getProjectName() );
        if( entity != null ){
            System.out.println("Error, trying to add a interfacedecoder which already exists");
            return null;
        }

        entity = MapperConfig.getInterfaceEntityFromDto(interfaceDecoder);
        interfaceDecoderConfigurationRepository.save(entity);
        return entity.getConfigId();
    }

    @Override
    public List<TransportConfigurationDto> getTransportConfigurations(String projectName) {
        List<TransportConfigurationDto> transportConfigDtos = new  ArrayList();
        List<TransportConfigurationEntity> transportConfigEntities =
                transportConfigurationRepository.findByProjectName(projectName);
        transportConfigEntities.stream().forEach( entity -> {
            transportConfigDtos.add(MapperConfig.getTransportDtoFromEntity(entity));
        });

        return transportConfigDtos;
    }

    @Override
    public List<TransportConfigurationDto> getTransportConfigurations(String projectName, String platformName) {
        List<TransportConfigurationDto> transportConfigDtos = new  ArrayList();
        List<TransportConfigurationEntity> transportConfigEntities =
                transportConfigurationRepository.findByPlatformNameAndProjectName(platformName, projectName);
        transportConfigEntities.stream().forEach( entity -> {
            transportConfigDtos.add(MapperConfig.getTransportDtoFromEntity(entity));
        });

        return transportConfigDtos;
    }

    @Override
    public TransportConfigurationDto getTransportConfiguration(String projectName, String platformName, String configurationName) {
        TransportConfigurationEntity entity =
                transportConfigurationRepository.findFirstByConfigurationNameAndPlatformNameAndProjectName(configurationName, platformName, projectName);
        TransportConfigurationDto dto = entity == null ? null : MapperConfig.getTransportDtoFromEntity(entity);
        return dto;
    }

    @Override
    public TransportConfigurationDto getTransportConfiguration(UUID specificId) {
        TransportConfigurationEntity entity = transportConfigurationRepository.findById(specificId).get();
        TransportConfigurationDto dto = entity == null ? null : MapperConfig.getTransportDtoFromEntity(entity);
        return dto;
    }

    @Override
    public UUID editTransportConfiguration(TransportConfigurationDto transport) {
        TransportConfigurationEntity entity = transportConfigurationRepository.
                findFirstByConfigurationNameAndPlatformNameAndProjectName(transport.getConfigurationName(),
                        transport.getPlatformName(),
                        transport.getProjectName() );
        if( entity == null ){
            // TODO handle a bad edit message
            System.out.println("Error, no known transportConfiguration for of name "+transport.getConfigurationName() + " in project:"
                    + transport.getProjectName() + " with platform " + transport.getPlatformName() + " found");
            return null;
        }

        InterfaceDecoderConfigurationEntity decoderEntity = interfaceDecoderConfigurationRepository.findById(transport.getInterfaceDecoderId()).get();
        if( decoderEntity == null){
            // TODO handle a bad edit message
            System.out.println("Error, no known InterfaceDecoderConfiguration the given InterfaceDecoder Id of "+transport.getInterfaceDecoderId() + " in project:"
                    + transport.getProjectName() + " with platform " + transport.getPlatformName() + " found");
            return null;
        }

        entity.setInterfaceDecoderConfigurationEntity(decoderEntity);
        entity.setUserComments(transport.getUserComments());
        entity.setTransportAsJson(transport.getTransportAsJson());

        transportConfigurationRepository.save(entity);
        return entity.getConfigId();
    }

    @Override
    public boolean deleteTransportConfiguration(String projectName, String platformName, String configurationName) {
        TransportConfigurationEntity entity = transportConfigurationRepository.
                findFirstByConfigurationNameAndPlatformNameAndProjectName(configurationName,
                        platformName,
                        projectName );
        if( entity == null ){
            // TODO handle a bad edit message
            System.out.println("Error, no known transportConfiguration for of name "+configurationName + " in project:"
                    + projectName + " with platform " + platformName + " found");
            return false;
        }

        transportConfigurationRepository.delete(entity);
        return true;
    }

    @Override
    public UUID addTransportConfiguration(TransportConfigurationDto transport) {

        TransportConfigurationEntity entity = transportConfigurationRepository.
            findFirstByConfigurationNameAndPlatformNameAndProjectName(transport.getConfigurationName(),
                    transport.getPlatformName(),
                    transport.getProjectName() );
        if( entity != null ){
            System.out.println("Error, trying to add a Transport Config which already exists");
            return null;
        }

        InterfaceDecoderConfigurationEntity decoderEntity = interfaceDecoderConfigurationRepository.findById(transport.getInterfaceDecoderId()).get();
        if( decoderEntity == null){
            // TODO handle a bad edit message
            System.out.println("Error, no known InterfaceDecoderConfiguration the given InterfaceDecoder Id of "+transport.getInterfaceDecoderId() + " in project:"
                    + transport.getProjectName() + " with platform " + transport.getPlatformName() + " found");
            return null;
        }

        entity = MapperConfig.getTransportEntityFromDto(transport);
        entity.setInterfaceDecoderConfigurationEntity(decoderEntity);
        transportConfigurationRepository.save(entity);
        return entity.getConfigId();
    }

}
