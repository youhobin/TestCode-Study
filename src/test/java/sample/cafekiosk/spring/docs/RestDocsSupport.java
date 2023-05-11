package sample.cafekiosk.spring.docs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(RestDocumentationExtension.class)
//@SpringBootTest
public abstract class RestDocsSupport {

    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper = new ObjectMapper();


    // 스프링 서버를 문서 만들 때 띄워줘야함.
//    @BeforeEach
//    void setUp(WebApplicationContext webApplicationContext,
//               RestDocumentationContextProvider provider) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(MockMvcRestDocumentation.documentationConfiguration(provider))
//                .build();
//    }

    @BeforeEach
    void setUp(RestDocumentationContextProvider provider) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(initController()) // 컨트롤러 주입
                .apply(MockMvcRestDocumentation.documentationConfiguration(provider))
                .build();
    }

    protected abstract Object initController();

}
