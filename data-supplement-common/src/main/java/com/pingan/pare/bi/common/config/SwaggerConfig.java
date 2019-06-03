package com.pingan.pare.bi.common.config;


import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
@Data
@NoArgsConstructor
public class SwaggerConfig {

	private String port;

	private String path;

	private String array;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.select()  // 选择那些路径和api会生成document
				.apis(SwaggerConfig.basePackage(getArray())) // 对所有api进行监控
				.paths(PathSelectors.any()) // 对所有路径进行监控
				.build();
	}

    public static Predicate<RequestHandler> basePackage(final String basePackage){
		return new Predicate<RequestHandler>(){
			public boolean apply(RequestHandler input){
				return declaringClass(input).transform(handlerPackage(basePackage)).or(true);
			}
		};
	}

    private static Function<Class<?>,Boolean> handlerPackage(final String basePackage){
		return new Function<Class<?>, Boolean>() {
			@Override
			public Boolean apply(Class<?> input) {

				for (String strPackage:basePackage.split(";")){
					boolean isMatch=input.getPackage().getName().startsWith(strPackage);
					if (isMatch){
						return true;
					}
				}
				return false;
			}
		};
	}

	private static Optional<? extends Class<?>> declaringClass(RequestHandler input){
	    return Optional.fromNullable(input.declaringClass());
    }

	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("权限控制")
				.description("权限管理接口文档")
				.termsOfServiceUrl("http://localhost:"+getPort()+getPath()+"/swagger-ui.html")
				.version("@project.version@")
				.build();
	}
}
