经常需要在过滤器（Filter）中对POST请求的Body内容进行操作，如日志记录、签名验证和权限验证等。
然而，由于Request的Body只能读取一次，如果直接在过滤器中读取而不进行封装，可能导致后
续服务无法获取数据。


实际上，Spring Cloud Gateway已经内置了AdaptCachedBodyGlobalFilter过滤器，
它在Exchange中巧妙地缓存了Request的Body，避免了直接读取导致的一系列问题

在需要获取Body的地方，我们只需要通过以下方法即可：
```java
DataBuffer body = exchange.getAttributeOrDefault("cachedRequestBody", null);
String bodyStr = body.toString(StandardCharsets.UTF_8);
```
只不过通过源码可以看出，缓存RequestBody需要路由被标记为需要缓存，也就是this.routesToCache.containsKey(rouceId)方法必须返回true。

AdaptCachedBodyGlobalFilter会监听EnableBodyCachingEvent事件，当发布该事件时就将RouteId放入routesToCache中。为了方便使用，我们可以编写一个配置类，在初始化时发布EnableBodyCachingEvent事件，将所有路由都启用缓存功能。
```java

@Configuration(proxyBeanMethods = false)
@Slf4j
public class EnableCachedBodyConfiguration {

    @Resource
    private ApplicationEventPublisher publisher;
    
    @Resource
    private GatewayProperties gatewayProperties;
    
    @PostConstruct
    public void init() {
        gatewayProperties.getRoutes().forEach(routeDefinition -> {
            // 对 spring cloud gateway 路由配置中的每个路由都启用 AdaptCachedBodyGlobalFilter
            EnableBodyCachingEvent enableBodyCachingEvent = new EnableBodyCachingEvent(new Object(), routeDefinition.getId());
            publisher.publishEvent(enableBodyCachingEvent);
        });
    }
}
```
通过这种方式，我们可以更加方便地处理POST请求的Body内容，避免了一些常见的问题。在实际应用中，考虑到稳定性和性能，这种解决方案提供了一种更为可靠的选择。
