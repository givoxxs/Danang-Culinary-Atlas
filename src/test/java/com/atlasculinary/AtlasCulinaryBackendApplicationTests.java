package com.atlasculinary;

// Import các class cần được mock
import com.atlasculinary.securities.JwtTokenProvider; // Quan trọng: import đúng class của bạn
import com.cloudinary.Cloudinary;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class AtlasCulinaryBackendApplicationTests {

    // Bean đã mock thành công từ trước
    @MockBean
    private Cloudinary cloudinary;

    // Bean đã mock thành công từ trước
    @MockBean
    private JavaMailSender javaMailSender;

    /**
     * ======================================================================
     * BỔ SUNG QUAN TRỌNG NHẤT
     * ======================================================================
     * Giả lập JwtTokenProvider. Đây là Bean yêu cầu thuộc tính 'jwt.secret'
     * để khởi tạo. Bằng cách mock nó, chúng ta ngăn Spring cố gắng tạo
     * Bean thật, qua đó loại bỏ hoàn toàn lỗi liên quan đến cấu hình JWT.
     */
    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void contextLoads() {
        // Test này sẽ kiểm tra context có thể load thành công
        // với TẤT CẢ các phụ thuộc ngoại vi đã được giả lập hay không.
    }

}