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

### logout API

ログアウトすることで、他のAPIが使用できなくなります。

```text
> curl -i -b cookie.txt -X POST "http://localhost:8080/logout"
```