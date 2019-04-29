# spring-boot-sample

## API

### login API

ログインすることで、他のAPIが使用できるようになります。

```text
> curl -i -c cookie.txt -X POST "http://localhost:8080/login" -d "username=username" -d "password=password"
```

### hello API

文字列"hello"を返します。

```text
> curl -i -b cookie.txt "http://localhost:8080/hello"
hello
```

### list API

sampleテーブルに格納されるnameを全てを返します。

```text
> curl -i -b cookie.txt "http://localhost:8080/list"
{"response":["hoge"]}
```

### logout API

ログアウトすることで、他のAPIが使用できなくなります。

```text
> curl -i -b cookie.txt -X POST "http://localhost:8080/logout"
```