package com.jun.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 需求：通过编写 过滤器实现用户是否登录的检查
 * 实现：通过判断请求中是否有 token，
 * 如果有就是认为已经登录，如果没有就认为是非法请求，响应 401
 *
 * @author: Jun
 * @date: 2019/10/9 15:30
 */
@Component
public class UserLoginZuulFilter extends ZuulFilter {
    /**
     * 返回字符串代表过滤器的类型
     * <p>
     * pre：请求在被路由之前执行
     * routing：在路由请时调用
     * post：在 routing 和 error 过滤器之后调用
     * error：处理请求时发生错误调用
     *
     * @return
     */
    @Override
    public String filterType() {
        String[] types = {"pre", "routing", "post", "error"};
        return types[0];
    }

    /**
     * 通过返回的 int 值 来定义 过滤器的执行顺序，数字越小优先级越高
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个 boolean 值，判断该过滤器是否需要执行。返回true执行，返回false不执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体业务逻辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            // 过滤该请求，不对其进行路由
            requestContext.setSendZuulResponse(false);
            // 设置响应状态码
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
        return null;
    }
}
