package Busan.Matjib.dto.MatJib;

import Busan.Matjib.domain.busan.matjib.MatJib;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MatJibBoardDto {

    private Long matJibId;
    private String matJibMain_title; // MAIN_TITLE 음식점이름
    private Double matJibLng; //<LNG> 경도
    private Double matJibLat;//<LAT> 위도
    private String matJibTel; //<CNTCT_TEL> 음식점 번호
    private String matJibIntroduce; //ITEMCNTNTS 음식점소개
    private String matJibBusiness_hours;//<USAGE_DAY_WEEK_AND_TIME> 영업시간
    private String matJibWard;//<GUGUN_NM> 지역구
    private String matJibAddr;//<ADDR1> 주소
    private String matJibMenu;//<RPRSNTV_MENU> 대표메뉴명
    private String matJibImgAddr;//<MAIN_IMG_THUMB> 이미지주소
    private Integer matJibCnt;



    @QueryProjection
    public MatJibBoardDto(Long matJibId, String matJibMain_title, Double matJibLng, Double matJibLat, String matJibTel, String matJibIntroduce, String matJibBusiness_hours, String matJibWard, String matJibAddr, String matJibMenu, String matJibImgAddr, Integer matJibCnt) {
        this.matJibId = matJibId;
        this.matJibMain_title = matJibMain_title;
        this.matJibLng = matJibLng;
        this.matJibLat = matJibLat;
        this.matJibTel = matJibTel;
        this.matJibIntroduce = matJibIntroduce;
        this.matJibBusiness_hours = matJibBusiness_hours;
        this.matJibWard = matJibWard;
        this.matJibAddr = matJibAddr;
        this.matJibMenu = matJibMenu;
        this.matJibImgAddr = matJibImgAddr;
        this.matJibCnt = matJibCnt;
    }

    public MatJibBoardDto(MatJib matJib) {
        this.matJibId = matJib.getId();
        this.matJibMain_title = matJib.getMatJibMain_title();
        this.matJibLng = matJib.getMatJibLocation().getLng();
        this.matJibLat = matJib.getMatJibLocation().getLat();
        this.matJibTel = matJib.getMatJibintroduce().getTel();
        this.matJibIntroduce = matJib.getMatJibintroduce().getIntroduce();
        this.matJibBusiness_hours = matJib.getMatJibintroduce().getBusiness_hours();
        this.matJibWard = matJib.getMatJibintroduce().getWard();
        this.matJibAddr = matJib.getMatJibintroduce().getAddr();
        this.matJibMenu = matJib.getMatJibMenu();
        this.matJibImgAddr = matJib.getMatJibintroduce().getImgAddr();
        this.matJibCnt = matJib.getCnt();
    }


}
