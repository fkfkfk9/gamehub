# gamehub

## Spring프로젝트 게임 쇼핑몰 gamehub 입니다. 
### 개발 환경

>서버 : AWS EC2 서버
>WAS : tomcat8
>개발환경 : spring framework
>DB : 오라클 11g XE

### 진행된 작업

> - 회원 : 회원가입, 회원 로그인, 회원정보 수정, 회원탈퇴
> - 상품(관리자) : 상품 추가, 상품 목록, 상품 수정, 상품 삭제
> - 상품(회원) : 카테고리 별 상품 리스트, 상품 바둑판식 리스트식 정렬 변경가능, 상품 상세 페이지
> - 장바구니 : 상품 리스트, 상세 페이지에서 장바구니 추가, 추가시 중복 데이터 업데이트, 장바구니 페이지에서 추가,수정,삭제

### 주요 기능

> - id중복 체크 등에서 사용된 ajax로 중복 데이터 체크 및 jquery로 결과 표시
> - 회원가입, 로그인, 회원정보 수정 등 BCrypt와 SHA256을 이용한 패스워드 암호화 작업을 통해 보안성 강화 
> - 상품 등록에서 사용한 여러개의 이미지 파일을 CK에디터 업로드 및 드레그로 업로드 한 후 DB에 저장하여 관리
> - 관리자가 좀 더 편하게 상품 관리할 수 있도록 5가지 옵션의 검색기능을 제공
> - 관리자 상품목록과 장바구니 등 체크박스 선택을 통해 특정 데이터만 DB에서 처리
> - 장바구니에서 선택된 상품을 구매페이지로 전송, 상품 상세 페이지에서 구매 페이지로 전송
> - 상품 구매 기능
> - 주문내역 페이지와 주문 상세 내역 추가
> - 인터셉터를 추가하여 비로그인시 접근 불가한 페이지 설정하기