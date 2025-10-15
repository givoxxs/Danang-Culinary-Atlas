package com.atlasculinary;

// Import các class cần được mock với TÊN ĐÚNG
import com.atlasculinary.securities.JwtDecoder; // <-- TÊN ĐÚNG LÀ JWTDECODER
import com.cloudinary.Cloudinary;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class AtlasCulinaryBackendApplicationTests {

    @MockBean
    private Cloudinary cloudinary;

    @MockBean
    private JavaMailSender javaMailSender;

    /**
     * Sửa lỗi: Tên class xử lý JWT thực tế trong project này là 'JwtDecoder',
     * không phải 'JwtTokenProvider'.
     */
    @MockBean
    private JwtDecoder jwtDecoder; // <-- SỬA LẠI TÊN CHO ĐÚNG

    @Test
    void contextLoads() {
    }
}