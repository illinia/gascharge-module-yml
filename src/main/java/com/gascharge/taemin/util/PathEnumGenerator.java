package com.gascharge.taemin.util;

import com.gascharge.taemin.enums.Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.gascharge.taemin.enums.Module.from;

@Slf4j
public class PathEnumGenerator {
    private static final String PROJECT_PREFIX = "gascharge-";
    public static Module getModuleEnum(Resource r) {
        File file = getFile(r);
        String path = file.getPath();

        path = path.substring(path.indexOf(PROJECT_PREFIX) + PROJECT_PREFIX.length());
        path = path.substring(0, path.indexOf(PROJECT_PREFIX) - 1);

        return from(path);
    }

    public static String getProjectName(Resource r) {
        File file = getFile(r);

        String path = file.getPath();
        path = path.substring(path.lastIndexOf(PROJECT_PREFIX));
        path = path.substring(0, path.indexOf("/"));

        return path;
    }

    public static File getFile(Resource r) {
        try {
            return r.getFile();
        } catch (IOException e) {
            log.error(r.getFilename() + " 이름의 파일이 실제 존재하지 않습니다. {}", e);
            throw new RuntimeException(e);
        }
    }
}
