# pivotal-spring-demo

```
cf create-service p-identity demo sso
cf create-service p.gateway standard gateway -c '{ "host": "gateway", "sso": { "plan": "demo" } }'
cf create-service p.mysql db-small mysql-a
cf create-service p.mysql db-small mysql-b
cf create-service -c '{ "git": { "uri": "https://github.com/vchrisb/pivotal-spring-demo-config" }}' p.config-server standard config
cf create-service p.service-registry standard registry

cf bind-service service-a gateway -c '{"routes": [ {"path": "/service-a/**","sso-enabled": true, "token-relay": true, "filters": ["Scopes=cities.read"]} ] }'
cf bind-service service-c gateway -c '{"routes": [ {"path": "/service-c/**","sso-enabled": true, "token-relay": true, "filters": ["Scopes=info.read"]} ] }'
cf bind-service service-c sso -c '{"grant_types": ["client_credentials"], "authorities": ["cities.read", "cities.write", "weather.read"], "identity_providers": ["uaa"]}'

cf config-server-add-credhub-secret config service-a/cloud/master/mysecret '{"some.securevalue": "verysecure"}'
```
