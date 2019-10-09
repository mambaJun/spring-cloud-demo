package com.jun.demo.servie;

import com.jun.demo.entity.Item;
import com.jun.demo.feign.ItemFeignClient;
import com.jun.demo.propertie.OrderProerties;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Jun
 * @date: 2019/10/8 21:00
 */

@Service
public class ItemService {

    private final static String HOST_NAME = "jun-microservice-item";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EurekaClient discoveryClient;
    @Autowired
    private OrderProerties orderProerties;
    @Autowired
    private ItemFeignClient itemFeignClient;

    @Value("${jun.item.url}")
    private String junItemUrl;

    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById(Long id) {
        return itemFeignClient.queryItemById(id);
    }

//    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
//    public Item queryItemById(Long id) {
//        System.out.println("http://" + HOST_NAME + "item/");
//        return restTemplate.getForObject("http://" + HOST_NAME + "/item/" + id, Item.class);
//    }

    public Item queryItemByIdFallbackMethod(Long id) {
        return new Item(id, "查询商品信息出错!", null, null, null);
    }

//    public Item queryItemById(Long id) {
//
//        InstanceInfo instance = discoveryClient.getNextServerFromEureka(HOST_NAME, false);
//        if (instance == null) {
//            return null;
//        }
//
//        System.out.println(instance.getHostName());
//        System.out.println(instance.getPort());
//        System.out.println(instance.getId());
//        System.out.println(instance.getActionType());
//        System.out.println(instance.getAppGroupName());
//        System.out.println(instance.getAppName());
//        System.out.println(instance.getASGName());
//        System.out.println(instance.getDataCenterInfo());
//        System.out.println(instance.getHealthCheckUrl());
//        System.out.println(instance.getHomePageUrl());
//        System.out.println(instance.getIPAddr());
//        System.out.println(instance.getInstanceId());
//        System.out.println("##################");
//        System.out.println(instance.getHomePageUrl() + "item/" + id);
//
//        return restTemplate.getForObject(instance.getHomePageUrl() + "item/" + id, Item.class);
//    }

/*    public Item queryItemById(Long id) {
        return this.restTemplate.getForObject(orderProerties.getItemProperties().getUrl()
                + id, Item.class);
    }*/

/*    public Item queryItemById(Long id) {
        return this.restTemplate.getForObject("http://127.0.0.1:8081/item/"
                + id, Item.class);
    }*/

}
