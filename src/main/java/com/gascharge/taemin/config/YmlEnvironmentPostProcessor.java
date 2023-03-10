package com.gascharge.taemin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.gascharge.taemin.util.PathEnumGenerator.getProjectName;

@Slf4j
public class YmlEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources;
        try {
            resources = Arrays.stream(resourcePatternResolver.getResources("classpath*:application.yml"))
                    .sorted(new ModuleComparator()).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Resource resource : resources) {
            Optional<PropertySource<?>> propertySource = loadYaml(resource);
            if (propertySource.isPresent()) {
                PropertySource<?> source = propertySource.get();
                log.info("YmlEnvironmentPostProcessor ConfigurableEnvironment 에 {} 이 저장됨", source.getName());
                environment.getPropertySources().addLast(source);
            }
        }
    }

    private Optional<PropertySource<?>> loadYaml(Resource path) {
        Assert.isTrue(path.exists(), () -> "Resource " + path + " does not exist");
        try {
            List<PropertySource<?>> load = this.loader.load(getProjectName(path), path);
            if (load.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(load.get(0));
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to load yaml configuration from " + path, ex);
        }
    }
}













