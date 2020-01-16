package com.chanse.configuration.repository.entities;


import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Entity that will hold a "Configuration" set up
 */
@MappedSuperclass
@Data
public abstract class BaseConfigurationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="config_id")
    private UUID configId ;

    @Column(name="configuration_name")
    private String configurationName;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "platform_name")
    private String platformName;
}
