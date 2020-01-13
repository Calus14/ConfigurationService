package com.chanse.configuration.repository.entities;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "transportConfigurationTable",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"configuration_name", "platform_name", "project_name"})})
@Data
public class TransportConfigurationEntity extends BaseConfigurationEntity {

    @Type(type="jsonb")
    @Column(columnDefinition = "jsonb", name="transport_as_json", nullable = false)
    private String transportAsJson;

    @Column(name="created_by", nullable = false)
    private String createdBy;

    @Type(type="text")
    @Column(name="user_comments")
    private String userComments;

    @ManyToOne
    @JoinColumn(name="config_id")
    private InterfaceDecoderConfigurationEntity interfaceDecoderConfigurationEntity;

}
