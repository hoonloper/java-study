package kephispring.config;


import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 후보로 등록한다
        List<String> autoConfigs = new ArrayList<>();

        // 최신 방식
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

        // 이전 방식
        // for(String candidate: ImportCandidates.load(MyAutoConfiguration.class, classLoader)) {
        //     autoConfigs.add(candidate);
        // }

        return autoConfigs.toArray(new String[0]); // 빈 어레이를 넣어주는 방식, 옛날 방식
        // return autoConfigs.stream().toArray(String[]::new); // Java8, 타입 세이프한 방식
        // return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class); // 타입 세이프한 방식


        // 구식 코드
        // Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        // return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);

        // 정적 입력
        // return new String[] {
        //    "kephispring.config.autoconfig.DispatcherServletConfig",
        //    "kephispring.config.autoconfig.TomcatWebServerConfig"
        // };
    }
}
