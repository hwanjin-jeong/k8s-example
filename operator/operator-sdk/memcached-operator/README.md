# Operator SDK
- https://github.com/operator-framework/operator-sdk

## Memcached operator

1. Setup new project
```bash
# operator-sdk new memcached-operator
```

2. Add new api
```bash
# operator-sdk add api --api-version=cache.example.com/v1alpha1 --kind=Memcached
```

3. Modify Memcached Spec & Status

4. Add new controller with 
```bash
operator-sdk add controller --api-version=cache.example.com/v1alpha1 --kind=Memcached
```

4. Apply crd
```
$ kubectl create -f deploy/crds/cache.example.com_memcacheds_crd.yaml
```

5. Build & push
```bash 
# operator-sdk build registry/memcached-operator

# docker push registry/memcached-operator
```

6. Replace k8s manifest
```bash
# sed -i "" 's|REPLACE_IMAGE|<registry>/memcached-operator|g' deploy/operator.yaml
```

7. Deploy operator
```bash
$ kubectl create -f service_account.yaml
$ kubectl create -f role.yaml
$ kubectl create -f role_binding.yaml
$ kubectl create -f operator.yaml
```

8. Create memcached CR
```bash
$ cat deploy/crds/cache.example.com_v1alpha1_memcached_cr.yaml
apiVersion: "cache.example.com/v1alpha1"
kind: "Memcached"
metadata:
  name: "example-memcached"
spec:
  size: 3

$ kubectl apply -f deploy/crds/cache.example.com_v1alpha1_memcached_cr.yaml
```
