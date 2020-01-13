package com.chanse.configuration.repository;

import com.chanse.configuration.repository.entities.InterfaceDecoderConfigurationEntity;
import com.chanse.configuration.repository.entities.MessageConfigurationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InterfaceDecoderConfigurationRepository  extends CrudRepository<InterfaceDecoderConfigurationEntity, UUID>{

    InterfaceDecoderConfigurationEntity findFirstByConfigurationNameAndPlatformNameAndProjectName(
            String configurationName, String platformName, String projectName);

    List<InterfaceDecoderConfigurationEntity> findByProjectName(
            String projectName);

    List<InterfaceDecoderConfigurationEntity> findByPlatformNameAndProjectName(
            String platformName, String projectName);

}
