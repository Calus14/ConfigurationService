package com.chanse.configuration.config;


import com.chanse.configuration.domain.dto.InterfaceDecoderConfigurationDto;
import com.chanse.configuration.domain.dto.MessageConfigurationDto;
import com.chanse.configuration.domain.dto.TransportConfigurationDto;
import com.chanse.configuration.repository.entities.InterfaceDecoderConfigurationEntity;
import com.chanse.configuration.repository.entities.MessageConfigurationEntity;
import com.chanse.configuration.repository.entities.TransportConfigurationEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ToDo Use MapperFactor or something
/**
 * Configuration class for mapping one class to another
 */
public class MapperConfig {

    public static MessageConfigurationDto getMessageDtoFromEntity(MessageConfigurationEntity entity){
        MessageConfigurationDto dto = new MessageConfigurationDto();
        dto.setMessageAsJson(entity.getMessageAsJson());
        dto.setPlatformName(entity.getPlatformName());
        dto.setProjectName(entity.getProjectName());
        dto.setMessageName(entity.getMessageName());
        return dto;
    }

    public static MessageConfigurationEntity getMessageEntityFromDto(MessageConfigurationDto dto){
        MessageConfigurationEntity entity = new MessageConfigurationEntity();
        entity.setMessageAsJson(dto.getMessageAsJson());
        entity.setMessageName(dto.getMessageName());
        entity.setPlatformName(dto.getPlatformName());
        entity.setProjectName(dto.getProjectName());
        return entity;
    }

    public static InterfaceDecoderConfigurationDto getInterfaceDtoFromEntity(InterfaceDecoderConfigurationEntity entity){
        InterfaceDecoderConfigurationDto dto = new InterfaceDecoderConfigurationDto();
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setInterfaceDecoderAsJson(entity.getInterfaceDecoderAsJson());
        dto.setPlatformName(entity.getPlatformName());
        dto.setProjectName(entity.getProjectName());
        dto.setConfigurationName(entity.getConfigurationName());
        dto.setInterfaceDecoderConfigurationId(entity.getConfigId());
        dto.setUserComments(entity.getUserComments());
        return dto;
    }

    public static InterfaceDecoderConfigurationEntity getInterfaceEntityFromDto(InterfaceDecoderConfigurationDto dto){
        InterfaceDecoderConfigurationEntity entity = new InterfaceDecoderConfigurationEntity();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setInterfaceDecoderAsJson(dto.getInterfaceDecoderAsJson());
        entity.setUserComments(dto.getUserComments());
        entity.setPlatformName(dto.getPlatformName());
        entity.setProjectName(dto.getProjectName());
        entity.setConfigurationName(dto.getConfigurationName());
        return entity;
    }

    public static TransportConfigurationDto getTransportDtoFromEntity(TransportConfigurationEntity entity){
        TransportConfigurationDto dto = new TransportConfigurationDto();
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setConfigurationName(entity.getConfigurationName());
        dto.setPlatformName(entity.getPlatformName());
        dto.setProjectName(entity.getProjectName());
        dto.setTransportAsJson(entity.getTransportAsJson());
        dto.setInterfaceDecoderId(entity.getInterfaceDecoderConfigurationEntity().getConfigId());
        return dto;
    }

    public static TransportConfigurationEntity getTransportEntityFromDto(TransportConfigurationDto dto){
        TransportConfigurationEntity entity = new TransportConfigurationEntity();
        entity.setConfigurationName(dto.getConfigurationName());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setTransportAsJson(dto.getTransportAsJson());
        entity.setUserComments(dto.getUserComments());
        entity.setPlatformName(dto.getPlatformName());
        entity.setProjectName(dto.getProjectName());
        // TODO set this manually from the serviceentity.setInterfaceDecoderConfigurationEntity(dto.getInterfaceDecoderId());
        return entity;
    }
}
