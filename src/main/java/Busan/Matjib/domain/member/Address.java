package Busan.Matjib.domain.member;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String postcode;
    private String roadAddress;
    private String detailAddress;

    protected Address() {
    }

    public Address(String postcode, String roadAddress, String detailAddress) {
        this.postcode = postcode;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
    }
}
