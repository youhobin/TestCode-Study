package sample.cafekiosk.spring;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.client.mail.MailSendClient;

@ActiveProfiles("test")
@SpringBootTest
public abstract class IntegrationTestSupport {

    // 1. mockBean 이 다른 테스트에도 들어가게됨.
    // 2. 테스트 환경을 2개 만들어서 처리.
    @MockBean
    protected MailSendClient mailSendClient;

}
