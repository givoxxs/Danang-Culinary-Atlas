package com.atlasculinary;

// Import các class cần thiết
import com.cloudinary.Cloudinary;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class AtlasCulinaryBackendApplicationTests {

    /**
     * @MockBean sẽ thay thế Bean Cloudinary thật trong ApplicationContext
     * bằng một đối tượng giả lập (mock).
     * Điều này đảm bảo không có kết nối nào đến dịch vụ Cloudinary được thực hiện.
     */
    @MockBean
    private Cloudinary cloudinary;

    /**
     * Tương tự, @MockBean sẽ thay thế Bean JavaMailSender thật
     * bằng một đối tượng giả lập.
     * Điều này đảm bảo ứng dụng không cố gắng kết nối đến mail server.
     */
    @MockBean
    private JavaMailSender javaMailSender;

    @Test
    void contextLoads() {
        // Bài test này bây giờ sẽ kiểm tra xem ApplicationContext
        // có thể khởi động thành công với các Bean đã được giả lập hay không.
    }

}