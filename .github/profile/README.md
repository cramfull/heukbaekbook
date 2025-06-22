# 흑백문고(heukbaekbook)
> Since 2024.10.15


- 주제: 고객이 책을 검색하고 주문할 수 있는 인터넷 서점 개발

## 👨‍💻  구성원
<table>
  <tr>
    <td align="center">
      <a href="https://github.com/daemin-kim">
        <img src="https://avatars.githubusercontent.com/u/56399681?v=4" width="100px" alt="김대민"/><br>
        <b>김대민</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/dohyeon321">
        <img src="https://avatars.githubusercontent.com/u/127070191?v=4" width="100px" alt="김도현"/><br>
        <b>김도현</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/mrkfm">
        <img src="https://avatars.githubusercontent.com/u/173026488?v=4" width="100px" alt="김현수"/><br>
        <b>김현수</b>
      </a>
    </td>  
    <td align="center">
      <a href="https://github.com/juhabae">
        <img src="https://avatars.githubusercontent.com/u/141327091?v=4" width="100px" alt="배주하"/><br>
        <b>배주하</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/cramfull">
        <img src="https://avatars.githubusercontent.com/u/130737411?v=4" width="100px" alt="이승형"/><br>
        <b>이승형</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/banbakbulga">
        <img src="https://avatars.githubusercontent.com/u/126759912?v=4" width="100px" alt="양준혁"/><br>
        <b>양준혁</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/jd-hyun">
        <img src="https://avatars.githubusercontent.com/u/98350310?v=4" width="100px" alt="정동현"/><br>
        <b>정동현</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/wjdtjdgns">
        <img src="https://avatars.githubusercontent.com/u/105824163?v=4" width="100px" alt="정성훈"/><br>
        <b>정성훈</b>
      </a>
    </td>
  </tr>
</table>


---

## 📝 규칙

- `커밋 컨벤션`

    - Feat: 새로운 기능 추가
    - Fix: 버그 수정
    - Docs: 문서 수정
    - Style: 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
    - Refactor: 코드 리팩토링
    - Test: 테스트 코드, 리팩토링 테스트 코드 추가
    - Chore: 빌드 업무 수정, 패키지 매니저 수정
<br><br>
      
- `issue 규칙`
    - 참고: [https://velog.io/@junh0328/협업을-위한-깃허브-이슈-작성하기](https://velog.io/@junh0328/%ED%98%91%EC%97%85%EC%9D%84-%EC%9C%84%ED%95%9C-%EA%B9%83%ED%97%88%EB%B8%8C-%EC%9D%B4%EC%8A%88-%EC%9E%91%EC%84%B1%ED%95%98%EA%B8%B0)
    - 레이블 참고:
      [https://github.com/modolee/github-initial-settings](https://github.com/modolee/github-initial-settings)
    - 제목 참고: [https://doublesprogramming.tistory.com/256](https://doublesprogramming.tistory.com/256)
      <br><br>
    - 템플릿
        - issue 제목
            - 예시: **[Feat] 이슈 정리**
        - issue 템플릿

            ```markdown
            ## 📋 이슈 내용
            
            ## ✅ 체크리스트
            
            ## 📚 레퍼런스
            
            ```
        - 제목 예시
            - [Add] UI button 구현
    <br><br>
- `branch 규칙`
    - feature/ + 각자가 개발하고 있는 기능의 영어 이름을 딴 branch 명을 사용한다.
    - 예시: 
    ```
  git checkout -b <브랜치명>      
  git checkout -b feature/login
    ```
    
- `commit message 규칙`
    - 참고: [https://doublesprogramming.tistory.com/256](https://doublesprogramming.tistory.com/256)
    - [종류] 메시지 - #이슈번호
    - 예시
        - [Feat] todo-list 회원 API 엔티티 구현 - #2
        - [Fix] todo-list 회원 단건 조회 서비스 에러 수정 - #2
    <br><br>
- `PR 규칙`
    - PR 템플릿

        ```markdown
        ## 📋 이슈 번호
        
        ## 🛠 구현 사항
        
        ## 📚 기타
        
        ```

---

## 개발 환경

| 구분         | 세부내용                                     | 구분         | 세부내용                                |
|--------------|----------------------------------------------|--------------|-----------------------------------------|
| 개발 도구    | IntelliJ IDEA - Ultimate                    | 모델링 (ERD) | ERD Cloud                               |
| Language     | Java 21 LTS / Eclipse Temurin               | Message Queue | RabbitMQ                                |
| 빌드 도구    | Maven                                       | 협업 도구    | GitHub Project                          |
| 개발         | - Spring Framework (5.x)                   | CI/CD        | - Continuous Integration                |
|              | - Spring Boot (3.3.x)                      |              | - Continuous (Delivery, Deployment)    |
|              | - Spring Cloud                             |              |   - Github Action                       |
|              |   - Spring Cloud Gateway                   |              |   - Docker: NHN Container Registry      |
|              |   - Spring Cloud Netflix (eureka)          | UI           | Bootstrap                               |
|              |   - Spring Cloud Config                    |              |                                         |
|              |   - Spring Cloud OpenFeign                 |              |                                         |
|              | - JPA: Query                               |              |                                         |
| 테스트 환경  | - Junit 5 / AssertJ / Mockito / SonarQube   |              |                                         |
|              | - SonarQube Test Coverage 80% 이상         |              |                                         |
| 데이터베이스 | - MySQL (8.x) / Redis                      | NHN Cloud    | - Instance                              |
|              |                                            |              | - Secure Key Manager                    |
|              |                                            |              | - Image Manager                         |
|              |                                            |              | - Container Registry                    |
|              |                                            |              | - Load Balancer                         |
|              |                                            |              | - Log & Crash Search                    |
| 검색엔진     | Elastic Search (7.x)                       |              |                                         |

## System Architecture

![System Architecture](https://raw.githubusercontent.com/nhnacademy-be7-heukbaekbook/.github/refs/heads/main/profile/system_architecture.png)
