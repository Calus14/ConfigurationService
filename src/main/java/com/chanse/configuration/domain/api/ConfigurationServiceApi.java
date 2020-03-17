package com.chanse.configuration.domain.api;

import com.chanse.configuration.domain.dto.InterfaceDecoderConfigurationDto;
import com.chanse.configuration.domain.dto.MessageConfigurationDto;
import com.chanse.configuration.domain.dto.TransportConfigurationDto;
import com.chanse.configuration.domain.dto.UserCredentialsDto;
import com.chanse.configuration.repository.entities.InterfaceDecoderConfigurationEntity;
import com.chanse.messaging.msginterface.InterfaceDecoder;
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
    List<MessageConfigurationDto> getConfigurationMessages(@RequestParam("project") String project,
                                                           @RequestParam("platform") String platform);

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
    MessageConfigurationDto getConfigurationMessage(@RequestParam("project") String project,
                                                    @RequestParam("platform") String platform,
                                                    @RequestParam("messageName") String messageName);

    /**
     * Attempts to create and place a messageConfiguration entity into the database if no message currently
     * exists with the correct unqiueness
     * @param dto the new DTO that wishes to be added
     * @return the UUID of the entity cretaed or updated
     */
    @RequestMapping(value = "/configurationMessage",
            method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID putConfigurationMessage(@RequestBody() MessageConfigurationDto dto);

    /**
     * Attempts to find a messageConfiguration entity in the database if no message currently
     * exists returns. Once the message is found all data is replaced with the sent down dto
     * @param dto The DTO that represents the message configuration we wish to replace
     */
    @RequestMapping(value = "/updateConfigurationMessage",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID updateConfigurationMessage(@RequestBody() MessageConfigurationDto dto);

    /**
     * Attempts to create and place a messageConfiguration entity into the database if no message currently
     * exists with the correct unqiueness
     */
    @RequestMapping(value = "/deleteConfigurationMessage",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteConfigurationMessage(@RequestParam("project") String project,
                                    @RequestParam("platform") String platform,
                                    @RequestParam("messageName") String messageName);


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
    @RequestMapping(value = "/interfaceDecoders",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    List<InterfaceDecoderConfigurationDto> getGetInterfaceDecoders(@RequestParam("project") String project,
                                                                   @RequestParam("platform") String platform);

    /**
     * Returns the messageConfiguration tied to a given project and its platform and message name.
     * @param project The name of the project you wish to query
     * @param platform The name of the platform in the project you wish to query
     * @param project message you are seeking
     * @return A List of DTO's representing all messages for the given parameters
     */
    @RequestMapping(value = "/interfaceDecoder",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    InterfaceDecoderConfigurationDto getInterfaceDecoder(@RequestParam("project") String project,
                                                         @RequestParam("platform") String platform,
                                                         @RequestParam("configurationName") String configurationName);

    /**
     * Attempts to create and place a messageConfiguration entity into the database if no message currently
     * exists with the correct unqiueness
     * @param dto the new DTO that wishes to be added
     * @return the UUID of the entity cretaed or updated
     */
    @RequestMapping(value = "/interfaceDecoder",
            method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID putInterfaceDecoder(@RequestBody() InterfaceDecoderConfigurationDto dto);

    /**
     * Attempts to find a messageConfiguration entity in the database if no message currently
     * exists returns. Once the message is found all data is replaced with the sent down dto
     * @param dto The DTO that represents the message configuration we wish to replace
     */
    @RequestMapping(value = "/updateInterfaceDecoder",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID updateInterfaceDecoder(@RequestBody() InterfaceDecoderConfigurationDto dto);

    /**
     * Attempts to create and place a messageConfiguration entity into the database if no message currently
     * exists with the correct unqiueness
     */
    @RequestMapping(value = "/deleteInterfaceDecoder",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteInterfaceDecoder(@RequestParam("project") String project,
                                @RequestParam("platform") String platform,
                                @RequestParam("confdigurationName") String configurationName);

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
    @RequestMapping(value = "/transportConfigurations",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    List<TransportConfigurationDto> getTransportConfigurations(@RequestParam("project") String project,
                                                               @RequestParam("platform") String platform);

    /**
     * Returns the TransportConfigurationDto tied to a given project and its platform and message name.
     * @param project The name of the project you wish to query
     * @param platform The name of the platform in the project you wish to query
     * @param project message you are seeking
     * @return A List of DTO's representing all messages for the given parameters
     */
    @RequestMapping(value = "/transportConfiguration",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    TransportConfigurationDto getTransportConfiguration(@RequestParam("project") String project,
                                                        @RequestParam("platform") String platform,
                                                        @RequestParam("configurationName") String configurationName);

    /**
     * Attempts to create and place a TransportConfigurationDto entity into the database if no message currently
     * exists with the correct unqiueness
     * @param dto the new DTO that wishes to be added
     * @return the UUID of the entity cretaed or updated
     */
    @RequestMapping(value = "/transportConfiguration",
            method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID putTransportConfiguration(@RequestBody() TransportConfigurationDto dto);

    /**
     * Attempts to find a TransportConfigurationDto entity in the database if no message currently
     * exists returns. Once the message is found all data is replaced with the sent down dto
     * @param dto The DTO that represents the message configuration we wish to replace
     */
    @RequestMapping(value = "/updateTransportConfiguration",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID updateTransportConfiguration(@RequestBody() TransportConfigurationDto dto);

    /**
     * Attempts to create and place a TransportConfigurationDto entity into the database if no message currently
     * exists with the correct unqiueness
     */
    @RequestMapping(value = "/deleteTransportConfiguration",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteTransportConfiguration(@RequestParam("project") String project,
                                      @RequestParam("platform") String platform,
                                      @RequestParam("configurationName") String configurationName);



    /********************************************************************************************************
     * TODO Create Project with users etc. User Creation/Project authentications below
     ********************************************************************************************************/
    @RequestMapping(value = "/addProject",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID addProject(@RequestParam("projectName") String projectName,
                    @RequestParam("userId") UUID userId);


    @RequestMapping(value = "/addProjectUser",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    boolean addProjectUser(@RequestParam("projectName") String projectName,
                           @RequestParam("userId") UUID userId,
                           @RequestBody() UserCredentialsDto userDto);
}
