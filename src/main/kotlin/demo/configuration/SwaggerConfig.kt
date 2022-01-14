package demo.configuration

import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Tag
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.Optional

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("demo.controller"))
            .paths(Predicates.not(PathSelectors.regex("/error")))
            .build()
            .enable(true)
            .apiInfo(metadata())
            .useDefaultResponseMessages(false)
            .tags(Tag("ping", "Healthcheck endpoint"))
            .genericModelSubstitutes(Optional::class.java)
    }

    private fun metadata(): ApiInfo? {
        return ApiInfoBuilder()
            .title("Demo API")
            .description("Demo API")
            .version("0.0.1")
            .build()
    }
}