package com.danangculinary.danangculinaryatlas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Import ĐÚNG class application chính từ ĐÚNG package của nó
import com.atlasculinary.AtlasCulinaryBackendApplication;

// Chỉ định rõ class cấu hình cần load cho bài test
@SpringBootTest(classes = AtlasCulinaryBackendApplication.class)
class DanangculinaryatlasApplicationTests {

    @Test
    void contextLoads() {
    }

}