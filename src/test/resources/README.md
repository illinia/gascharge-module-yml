## classpath
빌드시 컴파일된 class 파일들의 위치 경로이다.
모든 컴파일된 클래스와 자원들은 classpath 하단에 생성되고 경로를 찾아야한다.

## resource 자원의 경로
java 패키지 내에서 클래스가 아닌 자원들에 접근하기 위해서
1. 접두사 사용
   * "file: src/main/resources/config.yml"
   * "classpath: config.yml"

classpath 는 기본 경로가 src/main/java, src/main/resources 이고, 두 경로의 하위를 탐색한다.
file 은 기본경로가 현재 프로젝트의 폴더이다.

## test 시 resource 자원의 경로
src/test/java 하위에 테스트 클래스가 위치해야 한다.
src/main 에 있는 파일을 탐색하려면 "file:" 접두사를 사용하거나
src/test 하위에 resources 폴더를 생성하여 해당 폴더 하위에 기존 resource 파일을 위치시켜 참조할 수 있다.

## ResourceLoader
리소스를 읽어오는 기능을 제공하는 인터페이스. ApplicationContext 인터페이스는 해당 것을 구현한다.

## ResourcePatternResolver
해당 인터페이스를 구현한 PathMatchingResourcePatternResolver 에서
CLASSPATH_ALL_URL_PREFIX = "classpath*:" 로 해당 prefix 로 구성된 문자열일 경우에 모든 리소스들을 검색한다.

### PathMatchingResourcePatternResolver
해당 클래스 생성자 파라미터가 빈값이면 DefaultResourceLoader 를 loader 로 주입해준다.