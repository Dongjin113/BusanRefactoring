package Busan.Matjib.domain.busan.matjib;

import Busan.Matjib.domain.busan.Introduce;
import Busan.Matjib.domain.busan.Location;
import Busan.Matjib.dto.Getdata.MatJibDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "Matjib")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatJib {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matjibId")
    private Long id;
    private String matJibMain_title;
    private String matJibMenu;
    @Embedded
    private Introduce matJibintroduce;
    @Embedded
    private Location matJibLocation;
    @ColumnDefault("0")
    private int cnt;

    public MatJib(MatJibDto food) {
        this.matJibMain_title = food.getMatJibMain_title();
        this.matJibMenu = food.getMatJibMenu();
        this.matJibintroduce = new Introduce(food);
        this.matJibLocation = new Location(food);
    }

    public void updateMatJib(MatJibDto updateMatJibDto){
        this.matJibMain_title = updateMatJibDto.getMatJibMain_title();
        this.matJibMenu = updateMatJibDto.getMatJibMenu();
        this.matJibintroduce = new Introduce(updateMatJibDto);
        this.matJibLocation = new Location(updateMatJibDto);
    }

    public void cntPlus() {
        this.cnt++;
    }

}
