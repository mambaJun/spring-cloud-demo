package com.jun.demo.propertie;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: Jun
 * @date: 2019/10/8 21:15
 */
@Component
@ConfigurationProperties(prefix="jun")
@Data
public class OrderProerties {

    @Autowired
    private ItemProperties itemProperties;

}
