package com.chanse.configuration.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportConfigurationDto {

    @NotNull
    protected String projectName;

    @NotNull
    protected String createdBy;

    protected String platformName;

    @NotNull
    protected String configurationName;

    protected UUID transportConfigurationId;

    @NotNull
    protected String transportAsJson;

    protected String userComments;

    protected UUID interfaceDecoderId;

}
