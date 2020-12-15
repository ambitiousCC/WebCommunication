package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
public class CharacterEncodingFilter implements Filter {
    private FilterConfig config;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException{
        //过滤器应该怎么返回到原来的页面去？
        request.setCharacterEncoding(config.getInitParameter("charset"));
        response.setCharacterEncoding(config.getInitParameter("charset"));
        try {
            chain.doFilter(request, response);
        } catch (ServletException e) {
            System.out.println("服务器出错，错误地址在字码过滤器");
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        System.out.println("字码过滤器被销毁");
    }

    @Override
    public void init(FilterConfig config){
        this.config = config;
    }
}
