# 1. 작성자
* [minseok](https://github.com/alstjr9396)

---
<br>

# 2. 공부 내용
* ### 멀티 모듈 환경 설정
  * subprojects     
    * settings.gradle에 포함된 모듈을 공통적으로 관리하는 항목      
    * api, common, core 세 개의 모듈 모두 자바와 스프링부트 의존성을 가짐      
    * 추가로 lombok과 테스트 관련 의존성 추가
      
  * project()       
    * 프로젝트간의 의존성을 관리하는 항목     

  * 실행될 필요가 없는 모듈에 추가            
    * bootJar.enabled false       
    * jar.enabled true
    

* ###멀티 모듈 구성
  * API
  * Common
  * Core