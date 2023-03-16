package com.gascharge.taemin.yml.util;

import com.gascharge.taemin.yml.enums.Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class PathEnumGenerator {
    public static Module getModuleEnum(Resource r) {
        String projectName = getProjectName(r);

        String regex = "gascharge-(.+?)-.+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(projectName);
        String result = null;
        if (matcher.find()) {
            result = matcher.group(1);
        }

        return Module.from(result);
    }

    public static String getProjectName(Resource r) {
        String input = null;
        try {
            input = r.getURL().getPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String regex = "gascharge-[a-zA-Z]+-[a-zA-Z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        String result = null;
        if (matcher.find()) {
            result = matcher.group(); // 정규식에 맞는 첫 번째 그룹 추출
        }

        return result;
    }
}
