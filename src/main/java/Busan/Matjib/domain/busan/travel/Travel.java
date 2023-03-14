package Busan.Matjib.domain.busan.travel;

import Busan.Matjib.domain.busan.Introduce;
import Busan.Matjib.domain.busan.Location;
import Busan.Matjib.dto.Getdata.TravelDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travelId")
    private  Long id;

    private String travelPlace;
    private String travelTitle;
    private String travelSubtitle;
    private String travelDisabled;
    private String travelPrice;
    @Column(length = 400)
    private String travelPublic_transport;
    private String travelDayOff;
    private String travelHomepage;

    @ColumnDefault("0")
    private int cnt;

    @Embedded
    private Introduce travelIntroduce;

    @Embedded
    private Location travelLocation;

    public Travel(TravelDto travel) {
        this.travelPlace = travel.getTravelPlace();
        this.travelTitle = travel.getTravelTitle();
        this.travelSubtitle = travel.getTravelSubtitle();
        this.travelDisabled = travel.getTravelDisabled();
        this.travelPrice = travel.getTravelPrice();
        this.travelPublic_transport = travel.getTravelPublic_transport();
        this.travelDayOff = travel.getTravelDayOff();
        this.travelHomepage = travel.getTravelHomepage();
        this.travelIntroduce = new Introduce(travel);
        this.travelLocation = new Location(travel);
    }
}
