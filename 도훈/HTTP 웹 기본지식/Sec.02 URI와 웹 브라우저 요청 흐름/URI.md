
#URI #URL #URN
# URI (Uniform Resource Identifier)

## "URI는 로케이터(Locator), 이름(name) 또는 둘다 추가로 분류될 수 있다."

## URI 단어 뜻 
 - **U**niform : 리소스 식별하는 통일된 방식
 - **R**esource : 자원, URI로 식별할  수 있는 모든 것(제한 없음)
 - **I**dentifier: 다른 항목과 구분하는데 필요한 정보

- URL : Uniform Resource Locator
- URN : Uniform Resource Name 

## URL, URN 
- URL - Locator  : 리소스가 있는 위치를 지정
- URN - Name : 리소스에 이름을 부여
- 위치는 변할 수 있지만, 이름은 변하지 않는다. 
- urn:isbn:8960777332 ( 어떤 책의 isbn URN)
- URN 이름만으로 실제 리소스를 찾을수 있는 방법이 보편화 되지 않음 

# URL 전체 문법 

- scheme://[userinfo@]host[:port][/path][?query][#fagment]
- https://www.google.com:443/search?q=hello&hl=ko

- 프로토콜(https)
- 호스트명(www.google.com)
- 포트 번호(443)
- 패스(/search)
- 쿼리 파라미터(q=hello&hl=ko)
  
  ## URL scheme 
- scheme://[userinfo@]host[:port][/path][?query][#fragment]
- https://www.google.com:443/search?q=hello%hl=ko

- 주로 프로토콜 사용 
- 프로토콜 : 어떤 방식으로 자원에 접근할 것인가 하는 약속 규칙 
	- 예) http(80), http(443), ftp(21)등등
- http는 80포트, https는 443 포트를 주로사용, 포트는 생략가능
- https 는 http에 보안 추가(HTTP Secure)

