package pl.coderslab.hero;


import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.coderslab.hero.util.PageableParameterBuilderPlugin;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Hero api")
                .description("desc")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                .apis((RequestHandlerSelectors.basePackage("pl.coderslab.hero")))
                .build();
    }
    @Bean
    PageableParameterBuilderPlugin pageableParameterBuilderPlugin (TypeNameExtractor nameExtractor, TypeResolver resolver){
        return new PageableParameterBuilderPlugin(nameExtractor, resolver);
    }
}
