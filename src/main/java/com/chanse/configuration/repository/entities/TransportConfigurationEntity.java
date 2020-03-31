package com.chanse.configuration.repository.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "transportConfigurationTable",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"configuration_name", "platform_name", "project_name"})})
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Data
public class TransportConfigurationEntity extends BaseConfigurationEntity {

    @Type(type="jsonb")
    @Column(columnDefinition = "jsonb", name="transport_as_json", nullable = false)
    private String transportAsJson;


    @Type(type="text")
    @Column(name="user_comments")
    private String userComments;

    @ManyToOne
    @JoinColumn(name="decoder_config_id")
    private InterfaceDecoderConfigurationEntity interfaceDecoderConfigurationEntity;

}
