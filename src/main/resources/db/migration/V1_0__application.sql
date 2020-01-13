create table interfaceDecoderConfigurationTable (
    config_id uuid NOT NULL PRIMARY KEY,
    configuration_name char varying(255),
    project_name char varying(255) NOT NULL,
    platform_name char varying(255),
    interface_decoder_as_json jsonb,
    created_by char varying(255) NOT NULL,
    user_comments char varying(255)
);


    CONSTRAINT fk_carrier_submission FOREIGN KEY (submission_id)
              REFERENCES submission (id),
