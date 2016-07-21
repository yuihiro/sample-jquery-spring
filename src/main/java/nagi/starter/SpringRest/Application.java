package nagi.starter.SpringRest;

import java.nio.charset.Charset;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// @EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
@PropertySource("config.properties")
//@ActiveProfiles({"dev","local"})
@EnableAspectJAutoProxy
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("메인");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.out.println("컨피그");
		return application.sources(Application.class);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("스타트업");
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		//ctx.register(AppWebConfig.class);
		ctx.setServletContext(servletContext);
		Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);
	}

	@Bean
	public HttpMessageConverter<String> stringHttpMessageConverter() {
		return new StringHttpMessageConverter(Charset.forName("UTF-8"));
	}

	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("5120MB");
		factory.setMaxRequestSize("5120MB");
		return factory.createMultipartConfig();
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setContentType("text/html;charset=utf-8");
		viewResolver.setOrder(2);
		return viewResolver;
	}

	/*
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "WEB-INF/views/layout/tiles.xml" });
		configurer.setCheckRefresh(true);
		return configurer;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		resolver.setOrder(1);
		return resolver;
	}
	*/

	/*
	 * @Bean public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) { ServletRegistrationBean registration = new
	 * ServletRegistrationBean(dispatcherServlet); registration.addUrlMappings("/"); return registration; }
	 */

	@Bean
	@Profile("web")
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {

			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				if (container instanceof TomcatEmbeddedServletContainerFactory) {
					customizeTomcat((TomcatEmbeddedServletContainerFactory) container);
				}

				/*
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/static/401.html");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/static/404.html");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/static/500.html");
				
				container.addErrorPages(error401Page, error404Page, error500Page);
				*/
			}

			private void customizeTomcat(TomcatEmbeddedServletContainerFactory tomcatFactory) {

				tomcatFactory.addConnectorCustomizers(new TomcatConnectorCustomizer() {

					@Override
					public void customize(Connector connector) {
						/*
						connector.setPort(8443);
						connector.setSecure(true);
						connector.setScheme("https");
						
						Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
						proto.setSSLEnabled(true);
						proto.setKeystoreFile(absoluteKeystoreFile);
						proto.setKeystorePass(keystorePass);
						proto.setKeystoreType("PKCS12");
						proto.setKeyAlias("tomcat");
						*/
					}
				});

				tomcatFactory.addContextCustomizers(new TomcatContextCustomizer() {

					@Override
					public void customize(Context context) {
						Container jsp = context.findChild("jsp");
						if (jsp instanceof Wrapper) {
							((Wrapper) jsp).addInitParameter("development", "false");
						}

					}

				});
			}
		};
	}
}
