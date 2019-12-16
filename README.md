# pivotal-spring-demo


##  Prepare

### Create Services
```
cf create-service p-identity demo sso
cf create-service p.gateway standard gateway -c '{ "host": "gateway", "sso": { "plan": "demo" } }'
cf create-service p.mysql db-small mysql-a
cf create-service -c '{ "git": { "uri": "https://github.com/vchrisb/pivotal-spring-demo-config" }}' p.config-server standard config
cf create-service p.service-registry standard registry
```

### Bind Gateway to create routes
```
cf bind-service service-a gateway -c '{"routes": [ {"path": "/service-a/**","sso-enabled": true, "token-relay": true, "filters": ["Scopes=cities.read,cities.write"]} ] }'
cf bind-service service-b gateway -c '{"routes": [ {"path": "/service-b/**","sso-enabled": true, "token-relay": true, "filters": ["Scopes=weather.read"]} ] }'
cf bind-service service-c gateway -c '{"routes": [ {"path": "/service-c/**","sso-enabled": true, "token-relay": true, "filters": ["Scopes=info.read,cities.read,cities.write"]} ] }'
```

### Bind SSO to service-c for communication with service-b

```
cf bind-service service-c sso -c '{"grant_types": ["client_credentials"], "authorities": ["cities.read", "cities.write", "weather.read"], "identity_providers": ["uaa"]}'
```

### Create secure value in credhub for service-a
```
cf config-server-add-credhub-secret config service-a/cloud/master/mysecret '{"some.securevalue": "verysecure"}'
```

## Endpoints

`<gateway-url>/service-c/info`: returning combined data from `service-a` and `service-b`  
`<gateway-url>/service-c/cities`: returning `cities` from `service-a` through `service-c`  
`<gateway-url>/service-c/weather/<city>`: returning `weather` for `<city>` from `service-b` through `service-c`  

`<gateway-url>/service-a/cities`: returning `cities` from `service-a`  
`<gateway-url>/service-b/weather/<city>`: returning `weather` for `<city>`  
