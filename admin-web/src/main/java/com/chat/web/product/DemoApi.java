package com.chat.web.product;

import com.chat.web.product.third.DemoFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-08-07 17:19
 * @Version: 1.0
 */
@RestController
@RequestMapping("demo")
@Api(tags = "测试接口")
public class DemoApi {

    @Autowired
    private DemoFeign demoFeign;

    @ApiOperation("测试Feign调用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "value1", value = "传值一"),
            @ApiImplicitParam(name = "value2", value = "名称")
    })
    @RequestMapping(value = "test", method = RequestMethod.POST)
    public String test() {
        return demoFeign.hello();
    }
}
