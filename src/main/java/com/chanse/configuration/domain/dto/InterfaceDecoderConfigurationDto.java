package com.chanse.configuration.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterfaceDecoderConfigurationDto {

    @NotNull
    protected String projectName;

    @NotNull
    protected String createdBy;

    protected String platformName;

    @NotNull
    protected String configurationName;

    @NotNull
    protected String interfaceDecoderAsJson;

    protected UUID interfaceDecoderConfigurationId;

    protected String userComments;
}
