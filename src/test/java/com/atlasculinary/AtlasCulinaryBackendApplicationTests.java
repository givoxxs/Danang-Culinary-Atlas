package com.atlasculinary;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Đây là file test chuẩn.
 * 1. Nó nằm trong cùng package với file application chính.
 * 2. Vì vậy, @SpringBootTest không cần tham số (classes = ...)
 *    nữa, nó sẽ tự động tìm thấy cấu hình.
 */
@SpringBootTest
class AtlasCulinaryBackendApplicationTests {

    @Test
    void contextLoads() {
        // Bài test đơn giản này chỉ kiểm tra xem
        // ApplicationContext có thể được load thành công hay không.
    }

}