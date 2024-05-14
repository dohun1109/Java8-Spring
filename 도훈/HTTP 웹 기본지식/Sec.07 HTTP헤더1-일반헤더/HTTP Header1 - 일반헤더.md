
## HTTP 헤더 -용도 
- HTTP 필요한 모든 부가정보 
- 예) 메시지 바디의 내용, 메세지 바디의 크기, 압축, 인증, 요청 클라이언트, 서버정보, 캐시 관리 정보 ... 
- 표준 헤더가 너무 많음 
- 필요시 임의의 헤더 추가  기능 
	- helloworld: hihi
 
## HTTP  헤더 분류 - RFC2616(과거)
- General 헤더 : 메시지 전체에 적용되는 정보 
- Request 헤더 : 요청 정보 
- Response 헤더 : 응답 정보 
- Entity 헤더 : 엔티티 바디 정보 
## HTTP BODY - message body - RFC2616(과거)
- 메시지 본문 (message body)은 엔티티 본문(entity body) 을 전달하는데 사용 
- 엔티티 본문은 요청이나 응답에서 전달할 실제 데이터 
- **엔티티 헤더** 는 **엔티티 본문**의 데이터를 해석할 수 있는 정보 제공 
	- 데이터 유형 (html, json), 데이터 길이, 압축 정보 등등 

## RFC723X 변화 
- 엔티티(Entity) -> 표현(Representation)
- Representation = representation Metadata + Representation Data 
- 표현 = 표현 메타데이터 + 표현 데이터 
## HTTP BODY - message body - RFC7230(최신)
- 메시지 본문(message body)를 통해 표현 데이터 전달'
- 메시지 본문 = 페이로드 (payload)
- 표현은 요청이나 응답에서 전달할 실제 데이터 
- 표현 헤더는 표현 데이터를 해석할 수 있는 정보 제공 
	- 데이터 유형 ( html, json) , 데이터 길이 , 압축 정보 등등 
- 참고 : 표현 헤더는 표현 메타데이터와, 페이로드 메시지를 구분해야하지만,  여기서는 생략 

## 표현 
- Content - Type : 표현 데이터의 형식 
- Content - Encoding : 표현 데이터의 압축 방식 
- Content - Language : 표현 데이터의 자연 언어 
- Content - Length : 표현 데이터의 길이 

- 표현 헤더는 전송 , 응답 둘다 사용 

## 협상 (콘텐츠 네고시에이션) - 클라이언트가 선호하는 표현 요청
- Accept : 클라이언트가 선호하는 미디어 타입 전달
-  Accept - Charset : 클라이언트가 선호하는 문자 인코딩 
- Accept - Encoding : 클라이언트가 선호하는 압축 인코딩 
- Accept - Language : 클라이언트가 선호하는 자연언어 

- 협상 헤더는 요청시에만 사용

## 전송 방식 설명 
- 단순 전송 
- 압축 전송 
- 분할 전송 
- 범위 전송 

#### 단순 전송 - Content - Length 
- Content 에 대한 길이를 알수 있을 때 
#### 압축 전송 - Content-Encoding
#### 분할 전송 - Transfer - Encoding 
- 분할전송시 Content - Length X -> 처음에 길이가 예상이 안됨
#### 범위 전송 - Range, Content-Range


## 일반 정보 
- From : 유저 에이전트의 이메일 정보 
- Referer : 이전 웹 페이지 주소
- User-Agent : 유저 에이전트 애플리케이션 정보 
- Server : 요청을 처리하는 오리진 서버의 소프트웨어 정보
- Date : 메시지가 새성된 날짜
 
#### From - 유저 에이전트의 이메일 정보 
- 일반적으로 잘 사용되지 않음
- 검색 엔진 같은 곳에서, 주로 사용 
- 요청에서 사용 
#### Referer - 이전 웹 페이지 주소 
- 현재 요청된 페이지의 이전 웹 페이지 주소
- A -> B로 이동하는 경우 B를 요청할 때 Referer: A를 포함해서 요청 
- Referer를 사용해서 유입 경로 분석 가능
- 요청에서 사용 
- 참고 : referer는 단어 referer의 오타
#### User-Agent - 유저 에이전트 애플리케이션 정보 
- 클라이언트의 애플리케이션 정보 (웹 브라우저 정보, 등등)
- 통계정보 
- 어떤 종류의 브라우저에서 장애가 발생하는지 파악 간으 
- 요청에서 사용 
#### Server - 요청하는 ORIGIN 서버의 소프트웨어 정보 
##### Origin 서버 : 여러 Proxy서버나 캐시 서버를 거치게 되는데 실제 응답을 해주는 서버 
- Server : Apache/2.2.22(Debian)
- server : nginX
- 응답에서 사용 
#### Date - 메시지가 발생한 날짜와 시간 
- Date : Tue, 15 Nov 1994 08:12:31 GMT 
- 응답에서 사용

## 특별한 정보 
#### HOST - 요청한 호스트 정보(도메인) 
- 요청에서 사용
- 필수 
- 하나의 서버가 여러 도메인을 처리해야 할 때 
- 하나의 IP 주소에 여러 도메인이 적용되어 있을 때 

## 캐시 기본 동작 
#### 캐시가 없을 때 
- 데이터가 변경되지 않아도 계속 네트워크를 통해서 데이터를 다운로드 받아야 한다. 
- 인터넷 네트워크는 매우 느리고 비싸다.
- 브라우저 로딩 속도가 느리다. 
- 느린 사용자 경험


## 검증 헤더와 조건부 요청 
### 검증 헤더 
- 캐시 데이터와 서버 데이터가 같은지 검증하는 데이터 
- Last-Modified, ETag
### 조건부 요청 헤더 
- 검증 헤더로 조건에 따른 분기 
- If - Modified-Since : Last-Modified 사용 
- If - None - Match : ETag 사용 
- 조건이 만족하면 200OK
- 조건이 만족하지 않으면 304 Not Modified 




