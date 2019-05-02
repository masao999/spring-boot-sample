# spring-boot-sample

## API

### login API

ログインすることで、他のAPIが使用できるようになります。

```text
> curl -i -c cookie.txt -X POST "http://localhost:8080/login" -d "username=username" -d "password=password"
```

cookie.txtからCSRFトークンを確認します。  
以下の例だと"68387842-dd10-4b1d-9568-a7cd2c0ed92d"です。  
POSTメソッドのAPIを使用する時に、ヘッダに設定する必要があります。
```text
# Netscape HTTP Cookie File
# https://curl.haxx.se/docs/http-cookies.html
# This file was generated by libcurl! Edit at your own risk.

#HttpOnly_localhost	FALSE	/	FALSE	0	XSRF-TOKEN	68387842-dd10-4b1d-9568-a7cd2c0ed92d
#HttpOnly_localhost	FALSE	/	FALSE	0	JSESSIONID	36B1A04D2109D6CBE404119E59BB207B
```

### hello API

文字列"hello"を返します。

```text
> curl -i -b cookie.txt "http://localhost:8080/hello"
hello
```

### list API

directoryテーブルに格納されるnameを全てを返します。

```text
> curl -i -b cookie.txt "http://localhost:8080/list"
{"response":["one","two","three"]}
```

idを付けると対応するnameのみ返します。

```text
> curl -i -b cookie.txt "http://localhost:8080/list/1"
{"response":"one"}
```

directoryテーブルにnameを追加します。

```text
curl -i -b cookie.txt -H 'Content-Type:application/json' -H 'X-XSRF-TOKEN:68387842-dd10-4b1d-9568-a7cd2c0ed92d' -X POST "http://localhost:8080/list" -d '{"name":"four"}'
```

directoryテーブルのidで指定した行のnameを更新します。

```text
curl -i -b cookie.txt -H 'Content-Type:application/json' -H 'X-XSRF-TOKEN:68387842-dd10-4b1d-9568-a7cd2c0ed92d' -X PUT "http://localhost:8080/list" -d '{"id":3,"name":"hoge"}'
```

### logout API

ログアウトすることで、他のAPIが使用できなくなります。

```text
> curl -i -b cookie.txt -H "X-XSRF-TOKEN:68387842-dd10-4b1d-9568-a7cd2c0ed92d" -X POST "http://localhost:8080/logout"
```