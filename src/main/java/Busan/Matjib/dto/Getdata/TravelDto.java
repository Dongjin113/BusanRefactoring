package Busan.Matjib.dto.Getdata;

import lombok.Getter;

@Getter
public class TravelDto {

    private String travelPlace;//PLACE
    private String travelTitle;//TITLE
    private double travelLng;//<LNG> 경도
    private double travelLat;//<LAT> 위도
    private String travelDisabled;//<MIDDLE_SIZE_RM1> 장애인 접근 시설
    private String travelPrice;//<USAGE_AMOUNT> 가격
    private String travelTel;//<CNTCT_TEL> 시설 전화번호
    private String travelPublic_transport;//<TRFC_INFO> 대중교통
    private String travelDayOff;//<HLDY_INFO> 휴일
    private String travelSubtitle;//<SUBTITLE> 부제목
    private String travelIntroduce; //<ITEMCNTNTS> 관광지소개

    private String travelBusiness_hours;//<USAGE_DAY_WEEK_AND_TIME> 영업시간
    private String travelWard;//<GUGUN_NM> 지역
    private String travelAddr;//<ADDR1> 주소
    private String travelHomepage;//<HOMEPAGE_URL> 홈페이지 주소
    private String travelImgAddr;// <MAIN_IMG_THUMB> 이미지주소

    public TravelDto(TravelDto travel) {
        this.travelPlace = travel.travelPlace;
        this.travelTitle = travel.travelTitle;
        this.travelLng = travel.travelLng;
        this.travelLat = travel.travelLat;
        this.travelDisabled = travel.travelDisabled;
        this.travelPrice = travel.travelPrice;
        this.travelTel = travel.travelTel;
        this.travelPublic_transport = travel.travelPublic_transport;
        this.travelDayOff = travel.travelDayOff;
        this.travelSubtitle = travel.travelSubtitle;
        this.travelIntroduce = travel.travelIntroduce;
        this.travelBusiness_hours = travel.travelBusiness_hours;
        this.travelWard = travel.travelWard;
        this.travelAddr = travel.travelAddr;
        this.travelHomepage = travel.travelHomepage;
        this.travelImgAddr = travel.travelImgAddr;
    }

    public TravelDto(
            String travelPlace, String travelTitle, double travelLng, double travelLat,
            String travelDisabled, String travelPrice, String travelTel, String travelPublic_transport,
            String travelDayOff, String travelSubtitle, String travelIntroduce, String travelBusiness_hours,
            String travelWard, String travelAddr, String travelHomepage, String travelImgAddr
    ) {
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
    }

}
