# cource-catalog-service.kt

Spring Boot(Kotlin)によるRESTfulAPIサーバ構築の学習目的プロジェクト。  
実行後<http://localhost:8080>へアクセス。  

- /{name} (GET)
- /{name} (POST)
- /{name} (PUT)
- /{name} (DELETE)

その他はcontroller内のファイルを参照。  

## 実行方法

```shell
docker build -t cource-catalog .
docker run -p 8080:8080 -it --rm --name my-cource-catalog cource-catalog
```

## 参考文献

- <https://www.udemy.com/share/1066fY3@ro7cq5JM-ZUqurd02oBG_YiLpU4Ph4YgsiObsJzqHZ7fiv2Q9nGH8E0Mfs7yzUONlQ==/>
