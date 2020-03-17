package com.chanse.configuration.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageConfigurationDto {

    @NotNull
    protected String projectName;

    protected String platformName;

    @NotNull
    protected String messageAsJson;

    @NotNull
    protected String messageName;

}
