package com.gascharge.taemin.yml.config;

import com.gascharge.taemin.yml.enums.Module;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.util.Comparator;

import static com.gascharge.taemin.yml.util.PathEnumGenerator.getModuleEnum;

/**
 * {@link YmlEnvironmentPostProcessor} 에서 {@link ConfigurableEnvironment} 에 propertySources 에서
 * 리스트 뒤에 위치한 값이 앞에 위치한 값을 덮어쓰기 때문에
 * 가장 기본 값들을 앞에 위치시키고 가장 중요한 값들은 뒤에 위치시키기 위한 Comparator
 */
public class ModuleComparator implements Comparator<Resource> {
    /**
     * 모듈 오름차순 정렬을 위한 메서드
     * {@link Module} enum 의 order 값이 낮을 수록 propertySources 앞에 정렬된다.
     */
    @Override
    public int compare(Resource o1, Resource o2) {
        try {
            int o1Order = getModuleEnum(o1).getOrder();
            int o2Order = getModuleEnum(o2).getOrder();

            return o1Order - o2Order;
        } catch (Exception e) {
            return -100;
        }
    }
}
