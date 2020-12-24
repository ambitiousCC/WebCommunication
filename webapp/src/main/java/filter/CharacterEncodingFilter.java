package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class CharacterEncodingFilter implements Filter {
    private String encoding;
    private String contentType;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException{
        //过滤器应该怎么返回到原来的页面
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding(encoding);
        resp.setCharacterEncoding(encoding);
        try {
            System.out.println(new Date().getTime());
            chain.doFilter(req,resp);
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
        this.encoding = config.getInitParameter("encoding");
        this.contentType = config.getInitParameter("contentType");
    }
}
