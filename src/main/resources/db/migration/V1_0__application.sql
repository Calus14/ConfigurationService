create table interfaceDecoderConfigurationTable (
    config_id uuid NOT NULL PRIMARY KEY,
    configuration_name char varying(255),
    project_name char varying(255) NOT NULL,
    platform_name char varying(255),
    interface_decoder_as_json jsonb,
    created_by char varying(255) NOT NULL,
    user_comments char varying(255)
);

create table messageConfigurationTable (
    config_id uuid NOT NULL PRIMARY KEY,
    configuration_name char varying(255),
    project_name char varying(255) NOT NULL,
    message_name char varying(255) NOT NULL,
    message_as_json jsonb NOT NULL
};

create table transportConfigurationTable{
    config_id uuid NOT NULL PRIMARY KEY,
    configuration_name char varying(255),
    project_name char varying(255) NOT NULL,
    transport_as_json jsonb NOT NULL,
    created_by char varying(255) NOT NULL,
    user_comments char varying(255),
    CONSTRAINT fk_interface_decoder_configuration FOREIGN KEY (decoder_config_id)
              REFERENCES interfaceDecoderConfigurationTable (config_id)
};
