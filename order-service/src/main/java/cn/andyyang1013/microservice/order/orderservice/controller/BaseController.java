package cn.andyyang1013.microservice.order.orderservice.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
public class BaseController {
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @GetMapping
    public String provider(@RequestParam("consumer") String consumer) {
        InstanceInfo userService = eurekaClient.getNextServerFromEureka("user-service", false);
        String userServiceBaseApi = userService.getHomePageUrl() + "base?consumer=order-service";
        String userServiceRsp = restTemplateBuilder.build().getForObject(userServiceBaseApi, String.class);

        InstanceInfo productService = eurekaClient.getNextServerFromEureka("product-service", false);
        String productServiceBaseApi = productService.getHomePageUrl() + "base?consumer=order-service";
        String productServiceRsp = restTemplateBuilder.build().getForObject(productServiceBaseApi, String.class);
        return String.format("%s gets order service which invokes api about user service and product service. <br/> user service rsp: %s <br/> product service rsp: %s", consumer, userServiceRsp, productServiceRsp);
    }
}
