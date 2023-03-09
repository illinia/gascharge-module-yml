## gascharge-module-yml

### 사용법
1. spring.factories

    /src/main/resources 디렉토리에 META-INF/spring.factories 파일 생성 후
    ```properties
    org.springframework.boot.env.EnvironmentPostProcessor=com.gascharge.taemin.config.YmlEnvironmentPostProcessor
    ```
    해당 속성 입력해준다.


2. resources/application.yml
    application.yml 파일을 생성하여 원하는 속성을 입력해준다.# gascharge-module-yml
