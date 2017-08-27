gatlinginx
====

gatling(http://gatling.io/)の実行結果htmlをnginxで見れるようにしたいリポジトリ

## Description

## Usage

nginxコンテナの起動

```
$ docker-compose up -d nginx
```

gatlingの実行
BASE_URLのTARGET_PATHに対してUSERS人がSECONDS秒間でリクエストを実行します。

```
$ docker-compose run -e BASE_URL=[TARGET_URL] -e TARGET_PATH=[TARGET_PATH] -e USERS=[HOW_MANY_USERS] -e SECONDS=[HOW_LONG_SECONDS] gatling
GATLING_HOME is set to /opt/gatling
08:41:45.671 [WARN ] i.g.c.ZincCompiler$ - Pruning sources from previous analysis, due to incompatible CompileSetup.
samples.SampleSimulation is the only simulation, executing it.
Select simulation id (default is 'samplesimulation'). Accepted characters are a-z, A-Z, 0-9, - and _
[ENTER]
Select run description (optional)
[ENTER]
Simulation samples.SampleSimulation started...
...

Please open the following URL: [REPORT_URL]
```

完了後出力される[REPORT_URL]にアクセスするとレポートが確認できます。
