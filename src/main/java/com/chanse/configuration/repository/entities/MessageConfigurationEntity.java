package com.chanse.configuration.repository.entities;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The smallest configuration unit that can be shared accross an entire project.
 * Any edit to this one message should "trickle down" to other configurations CONFIGURATIONS ARE NOT HISTORICAL
 * THEY ARE DESCRIPTIVE OF THE FUTURE. This will be a problem later but for now this seems reasponable.
 */
@Entity
@Table(name="messageConfigurationTable",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"message_name", "platform_name", "project_name"})})
@Data
public class MessageConfigurationEntity extends BaseConfigurationEntity {

    @Column(name="message_name")
    private String messageName;

    @Type(type="jsonb")
    @Column(columnDefinition = "jsonb", name="message_as_json", nullable = false)
    private String messageAsJson;
}
