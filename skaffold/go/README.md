# Skaffold
- https://github.com/GoogleContainerTools/skaffold

# Quickstart
- https://skaffold.dev/docs/quickstart/


## Pre-requirement
- Dockerfile
```
FROM golang:1.12.9-alpine as builder
COPY main.go .
RUN go build -o /app main.go

FROM alpine:3.10
CMD ["./app"]
COPY --from=builder /app .
```

- K8s manifests
```
apiVersion: v1
kind: Pod
metadata:
  name: go
spec:
  containers:
  - name: hello
    image: test/hello-world
```

container image는 `registry`/`image name` 형태로 작성한다.

k8s에서 conatiner 생성을 하기 위해서는 registry가 필수이다.

## init

``` bash
# skafflod init

# ls -al
-rw-r--r-- Dockerfile
-rw-r--r-- main.go
-rw-r--r-- pod.yaml
-rw-r--r-- skaffold.yaml
```

`skaffold init` 명령을 입력하면 `skaffold.yaml` 파일을 생성해낸다.


``` 
apiVersion: skaffold/v2alpha1
kind: Config
metadata:
  name: go
build:
  artifacts:
  - image: test/hello-world
deploy:
  kubectl:
    manifests:
    - pod.yaml
```

--- 

## running on dev

``` bash
# skaffold dev
```

`skaffold dev` 명령을 입력하면 자동 다음 과정을 순서대로 진행한다.

1. docker build
2. docker push
3. kubectl apply
4. watch the change
- 변경사항이 감지되면 1 부터 반복한다.

--- 