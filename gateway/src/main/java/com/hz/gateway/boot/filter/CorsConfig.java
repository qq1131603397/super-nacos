package com.hz.gateway.boot.filter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
//@Component
//@PropertySource("classpath:application.yml")
//@WebFilter(urlPatterns = "/filter", filterName = "corsConfig")
@Order(0)
public class CorsConfig{
	/**
	 * 使用注解的形式，配置org.springframework.web.filter.CorsFilter，通常springboot项目多数使用这种方式
	 * 
	 * 解决跨域问题springboot所需配置
	 * @return
	 */
    @Bean
    public CorsWebFilter corsFilter() {
    	//添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");// 允许任何方法(post、get等)
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");// 允许任何的头信息
        config.setAllowCredentials(true);//是否发送Cookie信息
       // config.setMaxAge(3600*24L);//配置有效时长

        //添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

//	@Value("${bpm.fiter.domain}")
//	private String allowDomains;
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//		HttpServletResponse response = (HttpServletResponse) res;
//		HttpServletRequest reqs = (HttpServletRequest) req;
//		System.out.println("222222222222222222222222222222222222222");
//		// 设置允许多个域名请求
//		String[] allowDomain = allowDomains.split(",");
//		Set allowOrigins = new HashSet(Arrays.asList(allowDomain));
//
//		String curOrigin = reqs.getHeader("Origin");
//
//		if(allowOrigins.contains(curOrigin) || null == curOrigin){
//			//设置允许跨域的配置
//			// 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
//			response.setHeader("Access-Control-Allow-Origin", curOrigin);
//			response.setHeader("Access-Control-Allow-Credentials", "true");
//			//response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
//
//			response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH");
//
//			response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token");
//			chain.doFilter(reqs, response);
//
//		}else{
//            throw new IOException(ResultUtils.doFilter().toString());
//		}
//
//	}
//
//	@Override
//	public void init(FilterConfig filterConfig) {
//		System.out.println(123321321);
//	}
//
//	@Override
//	public void destroy() {}
}