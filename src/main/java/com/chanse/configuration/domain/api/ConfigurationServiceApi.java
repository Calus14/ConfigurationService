package com.chanse.configuration.domain.api;

import com.chanse.configuration.domain.dto.InterfaceDecoderConfigurationDto;
import com.chanse.configuration.domain.dto.MessageConfigurationDto;
import com.chanse.configuration.domain.dto.TransportConfigurationDto;
import com.chanse.configuration.domain.dto.UserCredentialsDto;
import com.chanse.configuration.repository.entities.InterfaceDecoderConfigurationEntity;
import com.chanse.messaging.msginterface.InterfaceDecoder;
import jdk.internal.jline.internal.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface ConfigurationServiceApi {

    /**
     * NOTES:
     * A Project is a subsection of a company, If this is meant to be set up and run on ansible clusters the cluster
     * will be a company and thus not specificied
     * Each project/team can hold multiple platforms/version.
     *
     * THIS IS THE GUI ENTRY AND EXIT POINT ESSENTIALLY. The user will make changes to messages and setup here. then press save
     * Running will just call into here and fire off the actual com sim service.
     *
     * TODO Figure out a basic way of storing a user credentias/security. For now it will be open but this will have to
     * be planed for the future
     */


    /********************************************************************************************************
     * Message EndPoints
     ********************************************************************************************************/

    /**
     * Returns all messageConfigurations tied to a given project and its platform. If platform is left empty or null
     * will return all messageConfigurations for a given project.
     * @param project The name of the project you wish to query
     * @param platform NULLABLE the name of the platform in the project you wish to query
     * @return A List of DTO's representing all messages for the given parameters
     */
    @RequestMapping(value = "/configurationMessages",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    List<MessageConfigurationDto> getConfigurationMessages(String project, @Nullable String platform);

    /**
     * Returns the messageConfiguration tied to a given project and its platform and message name.
     * @param project The name of the project you wish to query
     * @param platform The name of the platform in the project you wish to query
     * @param project message you are seeking
     * @return A List of DTO's representing all messages for the given parameters
     */
    @RequestMapping(value = "/configurationMessage",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    MessageConfigurationDto getConfigurationMessage(String project, String platform, String messageName);

    /**
     * Attempts to create and place a messageConfiguration entity into the database if no message currently
     * exists with the correct unqiueness
     * @param dto the new DTO that wishes to be added
     */
    @RequestMapping(value = "/configurationMessage",
            method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void putConfigurationMessage(MessageConfigurationDto dto);

    /**
     * Attempts to find a messageConfiguration entity in the database if no message currently
     * exists returns. Once the message is found all data is replaced with the sent down dto
     * @param dto The DTO that represents the message configuration we wish to replace
     */
    @RequestMapping(value = "/updateConfigurationMessage",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateConfigurationMessage(MessageConfigurationDto dto);

    /**
     * Attempts to create and place a messageConfiguration entity into the database if no message currently
     * exists with the correct unqiueness
     */
    @RequestMapping(value = "/deleteConfigurationMessage",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteConfigurationMessage(String project, String platform, String messageName);


    /********************************************************************************************************
     * InterfaceDecoder EndPoints
     ********************************************************************************************************/

    /**
     * Returns all InterfaceDecoder tied to a given project and its platform. If platform is left empty or null
     * will return all messageConfigurations for a given project.
     * @param project The name of the project you wish to query
     * @param platform NULLABLE the name of the platform in the project you wish to query
     * @return A List of DTO's representing all messages for the given parameters
     */
    @RequestMapping(value = "/configurationMessages",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    List<InterfaceDecoderConfigurationDto> getGetInterfaceDecoders(String project, @Nullable String platform);

    /**
     * Returns the messageConfiguration tied to a given project and its platform and message name.
     * @param project The name of the project you wish to query
     * @param platform The name of the platform in the project you wish to query
     * @param project message you are seeking
     * @return A List of DTO's representing all messages for the given parameters
     */
    @RequestMapping(value = "/configurationMessage",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    InterfaceDecoderConfigurationDto getInterfaceDecoder(String project, String platform, String configurationName);

    /**
     * Attempts to create and place a messageConfiguration entity into the database if no message currently
     * exists with the correct unqiueness
     * @param dto the new DTO that wishes to be added
     */
    @RequestMapping(value = "/configurationMessage",
            method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void putInterfaceDecoder(InterfaceDecoderConfigurationDto dto);

    /**
     * Attempts to find a messageConfiguration entity in the database if no message currently
     * exists returns. Once the message is found all data is replaced with the sent down dto
     * @param dto The DTO that represents the message configuration we wish to replace
     */
    @RequestMapping(value = "/updateConfigurationMessage",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateInterfaceDecoder(InterfaceDecoderConfigurationDto dto);

    /**
     * Attempts to create and place a messageConfiguration entity into the database if no message currently
     * exists with the correct unqiueness
     */
    @RequestMapping(value = "/deleteConfigurationMessage",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteInterfaceDecoder(String project, String platform, String configurationName);

    /********************************************************************************************************
     * TransportConfiguration EndPoints
     ********************************************************************************************************/

    /**
     * Returns all transportConfigurations tied to a given project and its platform. If platform is left empty or null
     * will return all messageConfigurations for a given project.
     * @param project The name of the project you wish to query
     * @param platform NULLABLE the name of the platform in the project you wish to query
     * @return A List of DTO's representing all messages for the given parameters
     */
    @RequestMapping(value = "/configurationMessages",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    List<TransportConfigurationDto> getGetInterfaceDecoders(String project, @Nullable String platform);

    /**
     * Returns the messageConfiguration tied to a given project and its platform and message name.
     * @param project The name of the project you wish to query
     * @param platform The name of the platform in the project you wish to query
     * @param project message you are seeking
     * @return A List of DTO's representing all messages for the given parameters
     */
    @RequestMapping(value = "/configurationMessage",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    InterfaceDecoderConfigurationDto getInterfaceDecoder(String project, String platform, String configurationName);

    /**
     * Attempts to create and place a messageConfiguration entity into the database if no message currently
     * exists with the correct unqiueness
     * @param dto the new DTO that wishes to be added
     */
    @RequestMapping(value = "/configurationMessage",
            method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void putInterfaceDecoder(InterfaceDecoderConfigurationDto dto);

    /**
     * Attempts to find a messageConfiguration entity in the database if no message currently
     * exists returns. Once the message is found all data is replaced with the sent down dto
     * @param dto The DTO that represents the message configuration we wish to replace
     */
    @RequestMapping(value = "/updateConfigurationMessage",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateInterfaceDecoder(InterfaceDecoderConfigurationDto dto);

    /**
     * Attempts to create and place a messageConfiguration entity into the database if no message currently
     * exists with the correct unqiueness
     */
    @RequestMapping(value = "/deleteConfigurationMessage",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteInterfaceDecoder(String project, String platform, String configurationName);



    /********************************************************************************************************
     * TODO Create Project with users etc. User Creation/Project authentications below
     ********************************************************************************************************/
    UUID addProject(String projectName, UUID userId);

    boolean addProjectUser(String projectName, UUID userId, UserCredentialsDto userDto);
}
