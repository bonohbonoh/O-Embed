package com.example.OEmbed.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerTest {

    private String URL = "/api/social-embed?url=";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OembedController oembedController;

    @BeforeAll
    public void settingMvcBuilder() {
        mvc = MockMvcBuilders
                .standaloneSetup(oembedController)
                .addFilter(new CharacterEncodingFilter("utf-8", true))
                .build();
    }

    @Test
    @DisplayName("링크 조회 실패 테스트")
    public void getUrlFailTest() throws Exception {

        //given
        String requestUrl = "https://www.failtest.com/v2asd";
        //when
        mvc.perform(
                        MockMvcRequestBuilders.get(URL + requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("인스타그램 링크 조회 성공 테스트")
    public void getInstagramUrlSuccessTest() throws Exception {

        //given
        String requestUrl = "https://www.instagram.com/p/BUawPlPF_Rx/";
        //when
        mvc.perform(
                        MockMvcRequestBuilders.get(URL + requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("인스타그램 게시물 조회 실패 테스트")
    public void getInstagramUrlFailTest() throws Exception {

        //given
        String requestUrl = "https://www.instagram.com/p/fail/";
        //when
        mvc.perform(
                        MockMvcRequestBuilders.get(URL + requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("인스타그램 잘못된 링크 조회 실패 테스트")
    public void getIllegalInstagramUrlFailTest() throws Exception {

        //given
        String requestUrl = "https://www.instagram.com/illegal/fail/";
        //when
        mvc.perform(
                        MockMvcRequestBuilders.get(URL + requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("트위터 링크 조회 성공 테스트")
    public void getTwitterUrlSuccessTest() throws Exception {

        //given
        String requestUrl = "https://twitter.com/hellopolicy/status/867177144815804416";
        //when
        mvc.perform(
                        MockMvcRequestBuilders.get(URL + requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("트위터 게시물 조회 실패 테스트")
    public void getTwitterUrlFailTest() throws Exception {

        //given
        String requestUrl = "https://twitter.com/fail/status/failcode1234";
        //when
        mvc.perform(
                        MockMvcRequestBuilders.get(URL + requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("트위터 잘못된 링크 조회 실패 테스트")
    public void getIllegalTwitterUrlFailTest() throws Exception {

        //given
        String requestUrl = "https://twitter.com/fail/illegal/failcode1234";
        //when
        mvc.perform(
                        MockMvcRequestBuilders.get(URL + requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("비미오 링크 조회 성공 테스트")
    public void getVimeoUrlSuccessTest() throws Exception {

        //given
        String requestUrl = "https://vimeo.com/20097015";
        //when
        mvc.perform(
                        MockMvcRequestBuilders.get(URL + requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("비미오 게시물 조회 실패 테스트")
    public void getVimeoUrlFailTest() throws Exception {

        //given
        String requestUrl = "https://vimeo.com/12341324735";
        //when
        mvc.perform(
                        MockMvcRequestBuilders.get(URL + requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("비미오 잘못된 링크 조회 실패 테스트")
    public void getIllegalVimeoUrlFailTest() throws Exception {

        //given
        String requestUrl = "https://vimeo.com/illegal";
        //when
        mvc.perform(
                        MockMvcRequestBuilders.get(URL + requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("유튜브 링크 조회 성공 테스트")
    public void getYoutubeUrlSuccessTest() throws Exception {

        //given
        String requestUrl = "https://www.youtube.com/watch?v=dBD54EZIrZo";
        //when
        mvc.perform(
                        MockMvcRequestBuilders.get(URL + requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("유튜브 게시물 조회 실패 테스트")
    public void getYoutubeUrlFailTest() throws Exception {

        //given
        String requestUrl = "https://www.youtube.com/watch?v=12";
        //when
        mvc.perform(
                        MockMvcRequestBuilders.get(URL + requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("유튜브 잘못된 링크 조회 실패 테스트")
    public void getIllegalYoutubeUrlFailTest() throws Exception {

        //given
        String requestUrl = "https://www.youtube.com/illegal?v=12";
        //when
        mvc.perform(
                        MockMvcRequestBuilders.get(URL + requestUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //then
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print());
    }

}
