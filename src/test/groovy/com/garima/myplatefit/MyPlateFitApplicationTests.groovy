package com.garima.myplatefit;

import com.garima.myplatefit.service.SpoonacularService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class MyPlateFitApplicationTests {

    @MockBean
    private SpoonacularService spoonacularService;

    @Test
    void contextLoads() {
    }
}
