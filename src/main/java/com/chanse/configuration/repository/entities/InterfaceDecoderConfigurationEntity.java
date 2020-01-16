package com.chanse.configuration.repository.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
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

    @Column(name="created_by", nullable = false)
    private String createdBy;

    @Type(type="text")
    @Column(name="user_comments")
    private String userComments;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="interfaceDecoderConfigurationEntity")
    @Column(name="transport_configuration_entity_list")
    List<TransportConfigurationEntity> transportConfigurationEntityList;
}
