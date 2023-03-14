package Busan.Matjib.service.MatJib;

import Busan.Matjib.controller.GetDataController;
import Busan.Matjib.domain.busan.matjib.MatJib;
import Busan.Matjib.dto.Getdata.MatJibDto;
import Busan.Matjib.dto.MatJib.MatJibBoardDto;
import Busan.Matjib.dto.MatJib.MatJibBoardSearch;
import Busan.Matjib.dto.MatJib.MatJibWard;
import Busan.Matjib.repository.MatJib.MatJibRepository;
import org.assertj.core.api.Assertions;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class MatJibServiceTest {

    private final MatJibRepository matJibRepository;
    private final MatJibService matJibService;
    private final GetDataController getDataController;


    @Mock
    private MatJibRepository mockMatJibRepository;

    @InjectMocks
    private MatJibService mockMatJibService;
    @Mock
    private HttpServletRequest request;

    @Autowired
    private ResourceLoader resourceLoader;



    @Autowired
    public MatJibServiceTest(MatJibService matJibService, GetDataController getDataController,MatJibRepository matJibRepository,HttpServletRequest servletRequest) {
        this.matJibService = matJibService;
        this.getDataController = getDataController;
        this.matJibRepository = matJibRepository;
    }

    @BeforeEach
    public void beforeEach() throws Exception {
        getDataController.foodData();
    }

//    @AfterEach
//    public void afterEach(){
//        matJibRepository.deleteAll();
//    }


    @Test
    @DisplayName("전체 페이지, 페이지 사이즈 확인")
    public void searchPageTest(){
        //given
        MatJibBoardSearch matJibBoardSearchNone = new MatJibBoardSearch();

        //when
        PageRequest pageRequest = PageRequest.of(0,5);
        Page<MatJibBoardDto> matJib = matJibService.searchMatJib(matJibBoardSearchNone, pageRequest);

        //then
        assertThat(matJib).hasSize(5);
        assertThat(matJib.getTotalPages()).isEqualTo(30);
    }

    @Test
    @DisplayName("지역별 맛집 검색")
    public void searchWardPageTest(){
        //given
        MatJibBoardSearch matJibBoardSearch = new MatJibBoardSearch();
        matJibBoardSearch.setMatJibWard("사하구");
        //when
        PageRequest pageRequest = PageRequest.of(0,5);
        Page<MatJibBoardDto> matJibWard0page = matJibService.searchMatJib(matJibBoardSearch, pageRequest);

        PageRequest pageRequest1 = PageRequest.of(1,5);
        Page<MatJibBoardDto> matJibWard1page = matJibService.searchMatJib(matJibBoardSearch, pageRequest1);

        //then
        assertThat(matJibWard0page).hasSize(5);
        assertThat(matJibWard1page).hasSize(3);
        assertThat(matJibWard0page).extracting("matJibWard").containsExactlyInAnyOrder("사하구","사하구","사하구","사하구","사하구");
        assertThat(matJibWard1page).extracting("matJibWard").containsExactlyInAnyOrder("사하구","사하구","사하구");
        assertThat(matJibWard1page).extracting("matJibWard").contains("사하구");
    }

    @Test
    @DisplayName("메뉴별 맛집 검색")
    public void searchMenuPageTest(){
        //given
        MatJibBoardSearch matJibBoardSearch = new MatJibBoardSearch();
        matJibBoardSearch.setMatJibMenu("밀면");

        MatJibBoardSearch matJibBoardSearch1 = new MatJibBoardSearch();
        matJibBoardSearch1.setMatJibMenu("오리");

        //when
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<MatJibBoardDto> matJibMenu = matJibService.searchMatJib(matJibBoardSearch, pageRequest);
        Page<MatJibBoardDto> matJibMenu1 = matJibService.searchMatJib(matJibBoardSearch1, pageRequest);

        for (MatJibBoardDto matJibBoardDto : matJibMenu1) {
            System.out.println("반복메뉴"+matJibBoardDto.getMatJibMain_title());
        }

        //then
        assertThat(matJibMenu).hasSize(4);
        assertThat(matJibMenu1).hasSize(4);

    }

    @Test
    @DisplayName("가게 제목으로 맛집 검색")
    public void searchMainTitlePageTest(){
        //given
        MatJibBoardSearch matJibBoardSearch = new MatJibBoardSearch();
        matJibBoardSearch.setMatJibMain_title("만드리");

        MatJibBoardSearch matJibBoardSearch1 = new MatJibBoardSearch();
        matJibBoardSearch1.setMatJibMain_title("갈비");

        //when
        PageRequest pageRequest = PageRequest.of(0,5);
        Page<MatJibBoardDto> matJibMainTitle = matJibService.searchMatJib(matJibBoardSearch, pageRequest);
        Page<MatJibBoardDto> matJibMainTitle1 = matJibService.searchMatJib(matJibBoardSearch1, pageRequest);

        //then
        assertThat(matJibMainTitle).hasSize(1);
        assertThat(matJibMainTitle1).hasSize(3);
    }

    @Test
    @DisplayName("지역별 맛집 수")
    public void countWard(){

        //given
        List<MatJibWard> matJibWard = matJibService.MatJibWard();
        for (MatJibWard jibWard : matJibWard) {
            System.out.println("카운트 = "+jibWard.getMatJibWardCount());
        }
        int totalMatJibCount=0;

        //when
        for (MatJibWard jibWard : matJibWard) {
            totalMatJibCount += jibWard.getMatJibWardCount();
        }

        //then
        assertThat(matJibWard).extracting("ward")
                .containsExactlyInAnyOrder("강서구","금정구","기장군","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구");
        assertThat(matJibWard.get(0).getWard()).isEqualTo("강서구");
        assertThat(matJibWard.get(0).getMatJibWardCount()).isEqualTo(6);
        assertThat(totalMatJibCount).isEqualTo(150);

    }

    @Test
    @DisplayName("맛집 조회수 Top3")
    @Rollback(value = false)
    public void MatJibTop3(){

        matJibService.MatJibCnt(32L);

        List<MatJibBoardDto> matJibTop3 = matJibService.MatJibTop3();

        assertThat(matJibTop3.size()).isEqualTo(3);
        assertThat(matJibTop3.get(0).getMatJibCnt()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시판 글 작성")
    @Rollback(value = false)
    public void MatJibBoardSave() throws IOException {

        //given
        MatJibDto matJibDto = new MatJibDto(
                "메인제목", 12.234, 123.1234, "전화번호",
                "맛집소개", "영업시간", "지역",
                "맛집주소", "맛집메뉴", "맛집 이미지주소"
        );
        request = mock(HttpServletRequest.class);

        MultipartFile imageFile = new MockMultipartFile(
                "test.jpg", "test.jpg", "image/jpeg", new byte[0]
        );

        matJibDto.setImgFile(imageFile);



        //when

        //then

    }

    @Test
    @DisplayName("게시판 글 작성")
    @Rollback(value = false)
    public void ttt() throws IOException {

        MatJib forEdit = matJibService.findForEdit(1L);
        System.out.println("소환술 = "+ forEdit.getMatJibMenu());

    }


}