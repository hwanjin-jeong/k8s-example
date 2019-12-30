# Operator SDK
- https://github.com/operator-framework/operator-sdk

# User Guide with Helm
- https://github.com/operator-framework/operator-sdk/blob/master/doc/helm/user-guide.md

## Getting started

1. Init Project
```bash
$ operator-sdk new nginx-operator --api-version=example.com/v1alpha1 --kind=Nginx --type=helm
$ cd nginx-operator

$ ls 
drwxr-xr-x .
drwxr-xr-x ..
drwxr-x--- build
drwxr-x--- deploy
drwxr-xr-x helm-charts
-rw-r--r-- watches.yaml
```
[helm_project_layout]:https://github.com/operator-framework/operator-sdk/blob/master/doc/helm/project_layout.md

2. Build

```bash
$ operator-sdk build <registry>/nginx-operator:v0.0.1

$ docker push <registry>/nginx-operator:v0.0.1

$ sed -i "" 's|REPLACE_IMAGE|<registry>/nginx-operator:v0.0.1|g' deploy/operator.yaml
```

3. Deploy

```bash
$ kubectl create -f deploy/service_account.yaml
$ kubectl create -f deploy/role.yaml
$ kubectl create -f deploy/role_binding.yaml
$ kubectl create -f deploy/operator.yaml
```

```bash
$ kubectl get deployment
NAME             READY   UP-TO-DATE   AVAILABLE   AGE
nginx-operator   1/1     1            1           30s
```

4. Deploy Nginx CR

```bash
$ kubectl apply -f example.com_nginxes_crd.yaml

$ kubectl apply -f deploy/crds/example.com_v1alpha1_nginx_cr.yaml
nginx.example.com/example-nginx created

$ kubectl get deployment
NAME             READY   UP-TO-DATE   AVAILABLE   AGE
example-nginx    1/1     1            1           90s
nginx-operator   1/1     1            1           119s

$ kubectl get pods
NAME                             READY   STATUS    RESTARTS   AGE
example-nginx-79f5d6d599-p5x26   1/1     Running   0          32s
nginx-operator-8bbf9b84b-ncvxr   1/1     Running   0          61s

$ kubectl get svc
NAME                     TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)             AGE
example-nginx            ClusterIP   10.109.226.50   <none>        80/TCP              70s
```
