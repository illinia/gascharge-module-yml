## gascharge-module-yml

jar 파일에 있는 yml 파일들을 우선순위에 맞게 스프링 Environment 에 추가해주는 모듈

* 해당 모듈을 사용하는 프로젝트에 라이브러리들의 classpath 에 있는 application.yml 이름의 파일을 전부 읽음
* 우선순위에 맞게 Resource 클래스 -> PropertySource 리스트 반환하여 스프링 Environment 에 추가

*독립적으로 실행 불가능*

사용법
1. spring.factories

   /src/main/resources 디렉토리에 META-INF/spring.factories 파일 생성 후
    ```properties
    org.springframework.boot.env.EnvironmentPostProcessor=com.gascharge.taemin.yml.config.YmlEnvironmentPostProcessor
    ```
   해당 속성 입력해준다.


2. resources/application.yml
   application.yml 파일을 생성하여 원하는 속성을 입력해준다.

로컬, 원격 메이븐 레포지토리
```groovy
implementation 'com.gascharge.taemin:gascharge-module-yml:0.0.1-SNAPSHOT'
```

멀티 모듈 프로젝트
```groovy
// settings.gradle
includeProject("yml", "module")
```
```groovy
// build.gradle
implementation project(":gascharge-module-yml")
```