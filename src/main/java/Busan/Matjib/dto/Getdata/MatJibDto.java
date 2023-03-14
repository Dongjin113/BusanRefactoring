package Busan.Matjib.dto.Getdata;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class MatJibDto {

    private Long matJibId;
    private String matJibMain_title; // MAIN_TITLE 음식점이름
    private double matJibLng; //<LNG> 경도
    private double matJibLat;//<LAT> 위도
    private String matJibTel; //<CNTCT_TEL> 음식점 번호
    private String matJibIntroduce; //ITEMCNTNTS 음식점소개
    private String matJibBusiness_hours;//<USAGE_DAY_WEEK_AND_TIME> 영업시간
    private String matJibWard;//<GUGUN_NM> 지역구
    private String matJibAddr;//<ADDR1> 주소
    private String matJibMenu;//<RPRSNTV_MENU> 대표메뉴명
    private String matJibImgAddr;//<MAIN_IMG_THUMB> 이미지주소
    private int matJibCnt;
    private MultipartFile imgFile;


    public void setMatJibImgAddr(String matJibImgAddr) {
        this.matJibImgAddr = matJibImgAddr;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public MatJibDto(
            String matJibMain_title, double matJibLng, double matJibLat, String matJibTel,
            String matJibIntroduce, String matJibBusiness_hours, String matJibWard,
            String matJibAddr, String matJibMenu, String matJibImgAddr
    ) {
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
    }

    public MatJibDto(MatJibDto food) {
        this.matJibMain_title = food.matJibMain_title;
        this.matJibLng = food.matJibLng;
        this.matJibLat = food.matJibLat;
        this.matJibTel = food.matJibTel;
        this.matJibIntroduce = food.matJibIntroduce;
        this.matJibBusiness_hours = food.matJibBusiness_hours;
        this.matJibWard = food.matJibWard;
        this.matJibAddr = food.matJibAddr;
        this.matJibMenu = food.matJibMenu;
        this.matJibImgAddr = food.matJibImgAddr;
    }
}
