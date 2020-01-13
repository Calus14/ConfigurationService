package chans.procrastinationbot.controller;


import chans.procrastinationbot.domain.api.ProcrastinationBotApi;
import chans.procrastinationbot.domain.dto.UserInfoDto;
import chans.procrastinationbot.service.ProcrastinationBotService;
import com.chanse.configuration.domain.api.ConfigurationServiceApi;
import com.chanse.configuration.service.ConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/configurationService")
public class ConfigurationServiceController implements ConfigurationServiceApi {

    @Autowired
    protected ConfigurationService configurationService;

}
