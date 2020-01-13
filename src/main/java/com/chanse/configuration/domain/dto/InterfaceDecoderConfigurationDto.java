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

    protected String platformName;

    @NotNull
    public String interfaceDecoderAsJson;

    public UUID interfaceDecoderConfigurationId;
}
