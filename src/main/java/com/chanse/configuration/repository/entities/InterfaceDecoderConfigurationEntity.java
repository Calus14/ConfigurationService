package com.chanse.configuration.repository.entities;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="interfaceDecoderConfigurationTable",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"configuration_name", "platform_name", "project_name"})})
@Data
public class InterfaceDecoderConfigurationEntity extends BaseConfigurationEntity {

    @Type(type="jsonb")
    @Column(columnDefinition = "jsonb", name = "interface_decoder_as_json")
    private String interfaceDecoderAsJson;

    @Column(name="created_by", nullable = false)
    private String createdBy;

    @Type(type="text")
    @Column(name="user_comments")
    private String userComments;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="interfaceDecoderConfigurationEntity")
    @Column(name="transport_configuration_entity_list")
    List<TransportConfigurationEntity> transportConfigurationEntityList;
}
