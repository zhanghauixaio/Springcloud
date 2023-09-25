package example;

import org.example.controller.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockTest {

    @MockBean
    private HelloController helloController;

    @Test
    void test() {
        // BDDMockito.given(this.helloController.hello()).willReturn(str)
    }
}
