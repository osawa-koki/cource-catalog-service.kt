# cource-catalog-service.kt

Spring Boot(Kotlin)によるRESTfulAPIサーバ構築の学習目的プロジェクト。  
実行後<http://localhost:80>へアクセス。  

- /{name} (GET)
- /{name} (POST)
- /{name} (PUT)
- /{name} (DELETE)

---

```shell
# コース一覧の取得
curl -X GET http://localhost:80/v1/courses

# コースの登録
curl -X POST -H "Content-Type: application/json" -d '{"name":"財務会計論", "category":"会計学"}' http://localhost:80/v1/courses

# コースの変更
curl -X PUT -H "Content-Type: application/json" -d '{"name":"租税総論", "category":"租税学"}' http://localhost:80/v1/courses/1

# コースの削除
curl -X DELETE -H http://localhost:80/v1/courses/1
```

## 実行方法

```shell
docker build -t cource-catalog .
docker run -p 80:80 -it --rm --name my-cource-catalog cource-catalog

# 一行で書くなら、、、
docker build -t cource-catalog . && docker run -p 80:80 -it --rm --name my-cource-catalog cource-catalog
```

## デプロイ設定(Render.com)

| キー | バリュー |
| ---- | ---- |
| Name | cource-catalog.kt |
| Region | Oregon(US West) |
| Branch | main |
| Root Directory |  |
| Environment | Docker |
| Dockerfile Path | ./Dockerfile |
| Docker Build Context Directory |  |
| Docker Command |  |

## 参考文献

- <https://www.udemy.com/share/1066fY3@ro7cq5JM-ZUqurd02oBG_YiLpU4Ph4YgsiObsJzqHZ7fiv2Q9nGH8E0Mfs7yzUONlQ==/>
