package Busan.Matjib.domain.busan;

import Busan.Matjib.dto.Getdata.MatJibDto;
import Busan.Matjib.dto.Getdata.TravelDto;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Introduce {

    @Column(length = 9000)
    private String introduce;
    private String Addr;
    private String Tel;
    private String Ward;
    private String Business_hours;
    private String ImgAddr;


    protected Introduce() {
    }

    public Introduce(MatJibDto food) {
        this.introduce = food.getMatJibIntroduce();
        Addr = food.getMatJibAddr();
        Tel = food.getMatJibTel();
        Ward = food.getMatJibWard();
        Business_hours = food.getMatJibBusiness_hours();
        ImgAddr = food.getMatJibImgAddr();
    }

    public Introduce(TravelDto travel) {
        this.introduce = travel.getTravelIntroduce();
        Addr = travel.getTravelAddr();
        Tel = travel.getTravelTel();
        Ward = travel.getTravelWard();
        Business_hours = travel.getTravelBusiness_hours();
        ImgAddr = travel.getTravelImgAddr();
    }

    public void changImgAddr(String imgAddr){
        this.ImgAddr = imgAddr;
    }

}
