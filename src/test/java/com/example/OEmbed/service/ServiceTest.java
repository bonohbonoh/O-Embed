package com.example.OEmbed.service;

import com.example.OEmbed.dto.ResponseDto;
import com.example.OEmbed.dto.TwitterDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServiceTest {

    @Autowired
    private OembedService oembedService;

    @Test
    @DisplayName("잘못된 링크 서비스 테스트")
    public void urlServiceFailTest() throws Exception{

        //given
        String requestUrl = "https://fail.com";

        //when
        RuntimeException e = assertThrows(RuntimeException.class, () -> oembedService.callUrl(requestUrl));

        //then
        assertThat(e.getMessage()).isEqualTo("잘못된 Url 입니다.");
    }

    @Test
    @DisplayName("인스타그램 링크 조회 서비스 성공 테스트")
    public void getInstagramUrlSuccessTest() throws Exception {

        //given
        String requestUrl = "https://www.instagram.com/p/BUawPlPF_Rx/";

        //when
        ResponseDto dto = oembedService.callUrl(requestUrl);

        //then
        assertThat(dto.getProvider_url()).isEqualTo("https://www.instagram.com");
        assertThat(dto.getProvider_name()).isEqualTo("Instagram");

    }

    @Test
    @DisplayName("인스타그램 게시물 조회 서비스 실패 테스트")
    public void getInstagramUrlServiceFailTest() throws Exception {

        //given
        String requestUrl = "https://www.instagram.com/p/fail/";

        //when
        RuntimeException e = assertThrows(RuntimeException.class, () -> oembedService.callUrl(requestUrl));

        //then
        assertThat(e.getMessage()).isEqualTo("존재하지 않는 게시물이거나 O-embed에 등록되지 않은 게시물입니다.");

    }

    @Test
    @DisplayName("인스타그램 잘못된 링크 조회 서비스 실패 테스트")
    public void getIllegalInstagramUrlTest() throws Exception {

        //given
        String requestUrl = "https://www.instagram.com/illegal/failcase1234/";

        //when
        RuntimeException e = assertThrows(RuntimeException.class, () -> oembedService.callUrl(requestUrl));

        //then
        assertThat(e.getMessage()).isEqualTo("올바른 Instagram 주소형식이 아닙니다.");

    }

    @Test
    @DisplayName("트위터 링크 조회 서비스 성공 테스트")
    public void getTwitterUrlServiceSuccessTest() throws Exception{

        //given
        String requestUrl = "https://twitter.com/hellopolicy/status/867177144815804416";

        //when
        ResponseDto dto = oembedService.callUrl(requestUrl);

        //then
        assertThat(dto.getProvider_url()).isEqualTo("https://twitter.com");
        assertThat(dto.getProvider_name()).isEqualTo("Twitter");

    }

    @Test
    @DisplayName("트위터 게시물 조회 서비스 실패 테스트")
    public void getTwitterUrlServiceFailTest() throws Exception{

        //given
        String requestUrl = "https://twitter.com/fail/status/failcode1234";

        //when
        RuntimeException e = assertThrows(RuntimeException.class, () -> oembedService.callUrl(requestUrl));

        //then
        assertThat(e.getMessage()).isEqualTo("존재하지 않는 게시물이거나 O-embed에 등록되지 않은 게시물입니다.");

    }

    @Test
    @DisplayName("트위터 잘못된 링크 조회 서비스 실패 테스트")
    public void getIllegalTwitterUrlServiceTest() throws Exception{

        //given
        String requestUrl = "https://twitter.com/fail/illegal/failcode1234";

        //when
        RuntimeException e = assertThrows(RuntimeException.class, () -> oembedService.callUrl(requestUrl));

        //then
        assertThat(e.getMessage()).isEqualTo("올바른 Twitter 주소형식이 아닙니다.");

    }

    @Test
    @DisplayName("비미오 링크 조회 서비스 성공 테스트")
    public void getVimeoUrlSuccessTest() throws Exception {

        //given
        String requestUrl = "https://vimeo.com/20097015";

        //when
        ResponseDto dto = oembedService.callUrl(requestUrl);

        //then
        assertThat(dto.getProvider_url()).isEqualTo("https://vimeo.com/");
        assertThat(dto.getProvider_name()).isEqualTo("Vimeo");

    }

    @Test
    @DisplayName("비미오 게시물 조회 서비스 실패 테스트")
    public void getVimeoUrlFailTest() throws Exception {

        //given
        String requestUrl = "https://vimeo.com/1234141234";

        //when
        RuntimeException e = assertThrows(RuntimeException.class, () -> oembedService.callUrl(requestUrl));

        //then
        assertThat(e.getMessage()).isEqualTo("존재하지 않는 게시물이거나 O-embed에 등록되지 않은 게시물입니다.");

    }

    @Test
    @DisplayName("비미오 잘못된 링크 조회 서비스 실패 테스트")
    public void getIllegalVimeoUrlTest() throws Exception {

        //given
        String requestUrl = "https://vimeo.com/illegal1234";

        //when
        RuntimeException e = assertThrows(RuntimeException.class, () -> oembedService.callUrl(requestUrl));

        //then
        assertThat(e.getMessage()).isEqualTo("올바른 Vimeo 주소 형식이 아닙니다.");

    }

    @Test
    @DisplayName("유튜브 링크 조회 서비스 성공 테스트")
    public void getYoutubeUrlSuccessTest() throws Exception {

        //given
        String requestUrl = "https://www.youtube.com/watch?v=dBD54EZIrZo";

        //when
        ResponseDto dto = oembedService.callUrl(requestUrl);

        //then
        assertThat(dto.getProvider_url()).isEqualTo("https://www.youtube.com/");
        assertThat(dto.getProvider_name()).isEqualTo("YouTube");

    }

    @Test
    @DisplayName("유튜브 게시물 조회 서비스 실패 테스트")
    public void getYoutubeUrlFailTest() throws Exception {

        //given
        String requestUrl = "https://www.youtube.com/watch?v=12";

        //when
        RuntimeException e = assertThrows(RuntimeException.class, () -> oembedService.callUrl(requestUrl));

        //then
        assertThat(e.getMessage()).isEqualTo("존재하지 않는 게시물이거나 O-embed에 등록되지 않은 게시물입니다.");

    }

    @Test
    @DisplayName("유튜브 잘못된 링크 조회 서비스 실패 테스트")
    public void getIllegalYoutubeUrlTest() throws Exception {

        //given
        String requestUrl = "https://www.youtube.com/illegal?v=12";

        //when
        RuntimeException e = assertThrows(RuntimeException.class, () -> oembedService.callUrl(requestUrl));

        //then
        assertThat(e.getMessage()).isEqualTo("올바른 Youtube 주소 형식이 아닙니다.");

    }



}
