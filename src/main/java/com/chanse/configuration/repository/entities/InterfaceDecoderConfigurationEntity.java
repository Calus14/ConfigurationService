package com.chanse.configuration.repository.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="interfaceDecoderConfigurationTable",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"configuration_name", "platform_name", "project_name"})})
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Data
public class InterfaceDecoderConfigurationEntity extends BaseConfigurationEntity {

    @Type(type="jsonb")
    @Column(columnDefinition = "jsonb", name = "interface_decoder_as_json", nullable = false)
    private String interfaceDecoderAsJson;

    @Type(type="text")
    @Column(name="user_comments")
    private String userComments;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy="interfaceDecoderConfigurationEntity")
    @Column(name="transport_configuration_entity_list")
    List<TransportConfigurationEntity> transportConfigurationEntityList;
}
