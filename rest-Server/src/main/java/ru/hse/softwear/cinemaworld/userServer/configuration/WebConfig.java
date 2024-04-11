package ru.hse.softwear.cinemaworld.userServer.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.hse.softwear.cinemaworld.userServer.converter.AgeCategoryToStringConverter;
import ru.hse.softwear.cinemaworld.userServer.converter.RoleToStringConverter;
import ru.hse.softwear.cinemaworld.userServer.converter.StringToAgeCategoryConverter;
import ru.hse.softwear.cinemaworld.userServer.converter.StringToRoleConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new AgeCategoryToStringConverter());
        registry.addConverter(new StringToAgeCategoryConverter());

        registry.addConverter(new RoleToStringConverter());
        registry.addConverter(new StringToRoleConverter());
    }
}
