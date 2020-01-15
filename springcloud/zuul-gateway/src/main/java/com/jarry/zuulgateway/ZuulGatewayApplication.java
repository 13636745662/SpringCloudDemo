package com.jarry.zuulgateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
//@EnableSwagger2Doc
public class ZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class, args);
    }

//    /**
//     * 配置Swagger
//     */
//    @Component
//    @Primary
//    class DocumentationConfig implements SwaggerResourcesProvider {
//        @Override
//        public List<SwaggerResource> get() {
//            List resource=new ArrayList<>();
//            //name可以随便写，location前缀要与zuul配置的path一致。zuul开了token验证,要加上token,否则不用加?token=1
//            resource.add(swaggerResource("myapp-item","/item-service/v2/api-docs?token=1","2.0"));
//            resource.add(swaggerResource("myapp-order","/order-service/v2/api-docs?token=1","2.0"));
//            return resource;
//        }
//
//        //name可以随便写，location前缀要与zuul配置的path一致
//        private SwaggerResource swaggerResource(String name,String location,String version){
//            SwaggerResource swaggerResource=new SwaggerResource();
//            swaggerResource.setName(name);
//            swaggerResource.setLocation(location);
//            swaggerResource.setSwaggerVersion(version);
//            return swaggerResource;
//        }
//    }

}
