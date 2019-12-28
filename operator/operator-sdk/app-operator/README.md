# Operator SDK
- https://github.com/operator-framework/operator-sdk

# Install Guide
- https://github.com/operator-framework/operator-sdk/blob/master/doc/user/install-operator-sdk.md

## Getting started

### App operator

1. Setup new project 

```
# operator-sdk new app-operator

INFO[0000] Creating new Go operator 'app-operator'.     
INFO[0000] Created go.mod                               
INFO[0000] Created tools.go                             
INFO[0000] Created cmd/manager/main.go                  
INFO[0000] Created build/Dockerfile                     
INFO[0000] Created build/bin/entrypoint                 
INFO[0000] Created build/bin/user_setup                 
INFO[0000] Created deploy/service_account.yaml          
INFO[0000] Created deploy/role.yaml                     
INFO[0000] Created deploy/role_binding.yaml             
INFO[0000] Created deploy/operator.yaml                 
INFO[0000] Created pkg/apis/apis.go                     
INFO[0000] Created pkg/controller/controller.go         
INFO[0000] Created version/version.go                   
INFO[0000] Created .gitignore                           
INFO[0000] Validating project                           
INFO[0003] Project validation successful.               
INFO[0003] Project creation complete.

$ ls -al app-operator

-rw-r--r-- .gitignore
drwxr-x--- build
drwxr-x--- cmd
drwxr-x--- deploy
-rw-r--r-- go.mod
-rw-r--r-- go.sum
drwxr-x--- pkg
-rw-r--r-- tools.go
drwxr-x--- version
```

2. Add new api

```
# operator-sdk add api --api-version=app.example.com/v1alpha1 --kind=AppService

INFO[0000] Generating api version app.example.com/v1alpha1 for kind AppService. 
INFO[0000] Created pkg/apis/app/group.go                
INFO[0026] Created pkg/apis/app/v1alpha1/appservice_types.go 
INFO[0026] Created pkg/apis/addtoscheme_app_v1alpha1.go 
INFO[0026] Created pkg/apis/app/v1alpha1/register.go    
INFO[0026] Created pkg/apis/app/v1alpha1/doc.go         
INFO[0026] Created deploy/crds/app.example.com_v1alpha1_appservice_cr.yaml 
INFO[0030] Created deploy/crds/app.example.com_appservices_crd.yaml 
INFO[0030] Running deepcopy code-generation for Custom Resource group versions: [app:[v1alpha1], ] 
INFO[0037] Code-generation complete.                    
INFO[0037] Running CRD generation for Custom Resource group versions: [app:[v1alpha1], ] 
INFO[0037] Created deploy/crds/app.example.com_appservices_crd.yaml 
INFO[0037] CRD generation complete.                     
INFO[0037] API generation complete.                     
```

3. Add new controller with AppService

```
# operator-sdk add controller --api-version=app.example.com/v1alpha1 --kind=AppService

INFO[0000] Generating controller version app.example.com/v1alpha1 for kind AppService.
INFO[0000] Created pkg/controller/appservice/appservice_controller.go
INFO[0000] Created pkg/controller/add_appservice.go
INFO[0000] Controller generation complete.
```

4. Build & push

```
# operator-sdk build <registry>/app-operator

# docker push <registry>/app-operator
```

5. Replace k8s manifest
```
# sed -i "" 's|REPLACE_IMAGE|<registry>/app-operator|g' deploy/operator.yaml
```

6. Deploy operator
```
cd deploy
$ kubectl create -f service_account.yaml
$ kubectl create -f role.yaml
$ kubectl create -f role_binding.yaml
$ kubectl create -f crds/app.example.com_appservices_crd.yaml
$ kubectl create -f operator.yaml
```

7. Using CRD
```
$ kubectl create -f crds/app.example.com_v1alpha1_appservice_cr.yaml

$ kubectl get pods

NAME                            READY   STATUS              RESTARTS   AGE
app-operator-875fb5689-lvbdj   1/1     Running             0          7m4s
example-appservice-pod         0/1     ContainerCreating   0          3s
```
