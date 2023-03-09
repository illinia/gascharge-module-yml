package com.gascharge.taemin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ClassPathResourceTest {

    private PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

    @Test
    @DisplayName("ClassPathResource 클래스로 클래스 패스에 위치한 application.properties 리소스 가져오기")
    void resource1() {
        ClassPathResource classPathResource = new ClassPathResource("application.properties");
        log.info("classPathResource : {}", classPathResource.getFilename());
        assertThat(classPathResource.getFilename()).isEqualTo("application.properties");

        Resource resource = pathMatchingResourcePatternResolver.getResource("application.properties");
        log.info("pathMatchingResourcePatternResolver : {}", resource.getFilename());
        assertThat(resource.getFilename()).isEqualTo("application.properties");
    }

    @Test
    @DisplayName("config 디렉토리 하위에 있는 모든 리소스 가져오기")
    void resource2() throws IOException {
        Resource[] resources = pathMatchingResourcePatternResolver.getResources("config/*.yml");
        String[] fileNames = Arrays.stream(resources).map(Resource::getFilename).toArray(String[]::new);

        Arrays.stream(fileNames).forEach(f -> log.info("fileNames {}", f));
        assertThat(fileNames).contains("a.yml", "b.yml");
    }

    @Test
    @DisplayName("정규식 패턴 이용해서 config 디렉토리 모든 하위 디렉토리 yml 리소스 가져오기")
    void resource3() throws IOException {
        Resource[] resources = pathMatchingResourcePatternResolver.getResources("config/**/*.yml");
        String[] fileNames = Arrays.stream(resources).map(r -> {
            try {
                String path = r.getFile().getPath();

                String str = "/gascharge-";
                int length = str.length();

                path = path.substring(path.indexOf(str) + length);
                path = path.substring(0, path.indexOf(str));

                return path;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toArray(String[]::new);

        Arrays.stream(fileNames).forEach(f -> log.info("fileNames {}", f));

        assertThat(fileNames).contains("a.yml", "b.yml", "c.yml");
    }

    @Test
    @DisplayName("t 로 시작하는 디렉토리의 모든 yml 리소스 가져오기")
    void resource4() throws IOException {
        Resource[] resources = pathMatchingResourcePatternResolver.getResources("config/t*/*.yml");
        String[] fileNames = Arrays.stream(resources).map(Resource::getFilename).toArray(String[]::new);

        Arrays.stream(fileNames).forEach(f -> log.info("fileNames {}", f));

        assertThat(fileNames).contains("c.yml");
    }
}
