package com.chanse.configuration.repository;

import com.chanse.configuration.repository.entities.InterfaceDecoderConfigurationEntity;
import com.chanse.configuration.repository.entities.MessageConfigurationEntity;
import com.chanse.configuration.repository.entities.TransportConfigurationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransportConfigurationRepository extends CrudRepository<TransportConfigurationEntity, UUID> {

    /*
    @Query(nativeQuery = true, value = "SELECT EXISTS ( SELECT * from userCredentials where user_unique_id = :userUniqueId)")
    boolean userCredentialsExistById(@Param("userUniqueId") UUID userUniqueId);
    */

    TransportConfigurationEntity findFirstByConfigurationNameAndPlatformNameAndProjectName(
            String configurationName, String platformName, String projectName);

    List<TransportConfigurationEntity> findByProjectName(
            String projectName);

    List<TransportConfigurationEntity> findByPlatformNameAndProjectName(
            String platformName, String projectName);

}

