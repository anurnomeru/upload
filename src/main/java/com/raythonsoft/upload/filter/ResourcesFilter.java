package com.raythonsoft.upload.filter;

import com.raythonsoft.upload.helper.NetworkHelper;
import com.raythonsoft.upload.pojo.model.Resources;
import com.raythonsoft.upload.util.AuthUtil;
import com.raythonsoft.upload.util.ParameterMapUtil;
import com.raythonsoft.upload.util.ResourcesUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

import static com.raythonsoft.upload.common.ProjectConstruct.*;

/**
 * Created by Anur IjuoKaruKas on 2017/11/13.
 * Description : 私有资源过滤器
 */
public class ResourcesFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 拦截url直接访问的请求
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        NetworkHelper.init(request);
        String requestUri = request.getRequestURI();

        if (Pattern.matches(FILTER_RULE, requestUri)) {
            boolean auth = false;
            try {
                String token = request.getHeader(TOKEN);
                String ip = NetworkHelper.getIpAddress();
                Resources resources = ResourcesUtil.findBy(requestUri.substring(1, requestUri.length()));

                if (AuthUtil.auth(token, ip, resources)) {
                    auth = true;
                    filterChain.doFilter(servletRequest, servletResponse);
                }

            } catch (Exception e) {
                // 发生错误也给跳转
            } finally {
                if (!auth) {
                    request.getRequestDispatcher(REFUSE_PATH).forward(servletRequest, servletResponse);
                }
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}

