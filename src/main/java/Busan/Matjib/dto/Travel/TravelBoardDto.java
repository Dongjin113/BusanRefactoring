package Busan.Matjib.dto.Travel;

import Busan.Matjib.domain.busan.travel.Travel;
import com.querydsl.core.annotations.QueryProjection;

public class TravelBoardDto {

    private Long travelId;
    private String travelPlace;     //PLACE
    private String travelTitle;     //TITLE
    private double travelLng;   //<LNG> 경도
    private double travelLat;   //<LAT> 위도
    private String travelDisabled;  //<MIDDLE_SIZE_RM1> 장애인 접근 시설
    private String travelPrice;  //<USAGE_AMOUNT> 가격
    private String travelTel;    //<CNTCT_TEL> 시설 전화번호
    private String travelPublic_transport;  //<TRFC_INFO> 대중교통
    private String travelDayOff;    //<HLDY_INFO> 휴일
    private String travelSubtitle;  //<SUBTITLE> 부제목
    private String travelIntroduce; //<ITEMCNTNTS> 관광지소개

    private String travelBusiness_hours;    //<USAGE_DAY_WEEK_AND_TIME> 영업시간
    private String travelWard;  //<GUGUN_NM> 지역
    private String travelAddr;  //<ADDR1> 주소
    private String travelHomepage;  //<HOMEPAGE_URL> 홈페이지 주소
    private String travelImgAddr;   // <MAIN_IMG_THUMB> 이미지주소
    private Integer travelCnt;

    @QueryProjection
    public TravelBoardDto(Long travelId, String travelPlace, String travelTitle, double travelLng, double travelLat, String travelDisabled, String travelPrice, String travelTel, String travelPublic_transport, String travelDayOff, String travelSubtitle, String travelIntroduce, String travelBusiness_hours, String travelWard, String travelAddr, String travelHomepage, String travelImgAddr, Integer travelCnt) {
        this.travelId = travelId;
        this.travelPlace = travelPlace;
        this.travelTitle = travelTitle;
        this.travelLng = travelLng;
        this.travelLat = travelLat;
        this.travelDisabled = travelDisabled;
        this.travelPrice = travelPrice;
        this.travelTel = travelTel;
        this.travelPublic_transport = travelPublic_transport;
        this.travelDayOff = travelDayOff;
        this.travelSubtitle = travelSubtitle;
        this.travelIntroduce = travelIntroduce;
        this.travelBusiness_hours = travelBusiness_hours;
        this.travelWard = travelWard;
        this.travelAddr = travelAddr;
        this.travelHomepage = travelHomepage;
        this.travelImgAddr = travelImgAddr;
        this.travelCnt = travelCnt;
    }

    public TravelBoardDto(Travel travel) {
        this.travelId =  travel.getId();
        this.travelPlace =  travel.getTravelPlace();
        this.travelTitle =  travel.getTravelTitle();
        this.travelLng =  travel.getTravelLocation().getLng();
        this.travelLat =  travel.getTravelLocation().getLat();
        this.travelDisabled =  travel.getTravelDisabled();
        this.travelPrice =  travel.getTravelPrice();
        this.travelTel =  travel.getTravelIntroduce().getTel();
        this.travelPublic_transport =  travel.getTravelPublic_transport();
        this.travelDayOff =  travel.getTravelDayOff();
        this.travelSubtitle =  travel.getTravelSubtitle();
        this.travelIntroduce =  travel.getTravelIntroduce().getIntroduce();
        this.travelBusiness_hours =  travel.getTravelIntroduce().getBusiness_hours();
        this.travelWard =  travel.getTravelIntroduce().getWard();
        this.travelAddr =  travel.getTravelIntroduce().getAddr();
        this.travelHomepage = travel.getTravelHomepage();
        this.travelImgAddr = travel.getTravelIntroduce().getImgAddr();
        this.travelCnt = travel.getCnt();
    }



}
