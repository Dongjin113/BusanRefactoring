package Busan.Matjib.domain.busan;

import Busan.Matjib.dto.Getdata.MatJibDto;
import Busan.Matjib.dto.Getdata.TravelDto;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Location {

    private double lng;
    private double lat;

    public Location() {
    }

    public Location(MatJibDto food) {
        this.lng = food.getMatJibLng();
        this.lat = food.getMatJibLat();
    }

    public Location(TravelDto travel) {
        this.lng = travel.getTravelLng();
        this.lat = travel.getTravelLat();
    }

}
