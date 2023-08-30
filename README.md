# backend

# 🖥️ 기본 정보

## 📍 버전

- JAVA 17
- JDK 20
- org.springframework.boot' version '3.1.3'





## 📍 Database ERD

![image](https://github.com/1st-webserviceproject/backend/assets/128140195/87d56017-eb6c-4396-b1a3-122499ce1161)



# 👨‍👨‍👧‍👦 협업 규칙
## 📍 Commit Naming Convention

커밋을 작성할 때 필히 다음 태그를 이름 앞에 붙여주세요.

붙이지 않으면, pull request가 거절될 수 있습니다.

- [INITIAL] — repository를 생성하고 최초에 파일을 업로드 할 때
- [ADD] — 신규 파일 추가
- [UPDATE] — 코드 변경이 일어날때
- [REFACTOR] — 코드를 리팩토링 했을때
- [FIX] — 잘못된 링크 정보 변경, 필요한 모듈 추가 및 삭제
- [REMOVE] — 파일 제거
- [STYLE] — 디자인 관련 변경사항
  
## 📍 Branch 전략
- main 브랜치는 함부로 push 하지 마세요.(branch protection 적용은 되어있지만)

- Branch는 Git Flow를 따라 개발이 진행 됩니다. (상세 내용은 검색)

- 기본 개발은 dev 브랜치에서 진행이 되며, main 브랜치는 모든 개발이 끝난 후에만 push가 가능합니다.

- feature 브랜치의 naming은 "feature/*"와 같은 형식을 지켜주세요.

- ex) dev -> new branch -> feature/signup-page

- 협업 예시 : 우선 협업을 위해서는 dev branch를 fork하고, 자신의 레파지토리에서 브랜치를 나누어 작업 한 후 이것을 우리가 협업하는 이 원본 레파지토리의 dev branch로 pull request를 보내세요.

## 📍 코드 리뷰

- 서로를 위해 코드리뷰를 활성화 해주세요.

- dev branch의 경우 한 개 이상의 approve가 필요합니다.

## 📍 이슈 남기기
- 추후 추가 예정
- 이슈에 대한 pull requst를 보낼 때는 closes 키워드를 활용하여 이슈와 requst를 묶으면, pull request가 merge되면 이슈가 자동으로 close가 됩니다.

## 📍 프로젝트 탭
추후 추가 예정
- 개발 목록
- 개발 진행 중
- 테스트 완료
- 최종 개발 완료

개발자가 개발을 시작한면 해당 목록을 개발진행중 컬럼으로 이동시킨다.

개발이 완료되면 개발완료 컬럼으로 목록을 이동하고, 테스트 담당자는 개발완료 컬럼에 있는 화면에 대해서 테스트를 진행하게 된다. 테스트가 완료되면 Bug 목록을 이슈에 등록하고, 개발목록를 테스트완료 컬럼으로 이동시키고, bug가 작성된 이슈를 맵핑한다.

개발자는 테스트완료 컬럼에서 bug가 등록되어져 있는 개발목록은 다시 개발진행중 컬럼으로 이동시키고 bug를 수정하게 되고, 이 사이클을 bug가 없을때 까지 실행하게 된다.

모든 bug가 완료되면 최종개발완료 컬럼으로 개발목록이 이동되게 되고, 실 사용자의 UAT전까지는 최종개발완료 상태로 남게 된다.
