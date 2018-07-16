import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.org")
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/*
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(
				"/");
	}

	@Bean
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/flatonrent?useUnicode=true&characterEncoding=utf-8");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		//dataSource.setPassword("eyrWIRNNG0nzQw2U");
		return dataSource;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(getDataSource());
		return template;
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	   /* multipartResolver.setMaxUploadSize(20971520);   // 20MB
	    multipartResolver.setMaxInMemorySize(1048576);*/  // 1MB
	    return multipartResolver;
	}
	
}
