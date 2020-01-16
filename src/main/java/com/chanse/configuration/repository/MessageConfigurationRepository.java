package com.chanse.configuration.repository;

import com.chanse.configuration.repository.entities.InterfaceDecoderConfigurationEntity;
import com.chanse.configuration.repository.entities.MessageConfigurationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.UUID;

@Repository
public interface MessageConfigurationRepository extends CrudRepository<MessageConfigurationEntity, UUID> {

    MessageConfigurationEntity findFirstByMessageNameAndPlatformNameAndProjectName(
                                        String messageName, String platformName, String projectName);

    List<MessageConfigurationEntity> findByProjectName(
                                        String projectName);

    List<MessageConfigurationEntity> findByPlatformNameAndProjectName(
                                        String platformName, String projectName);



}

