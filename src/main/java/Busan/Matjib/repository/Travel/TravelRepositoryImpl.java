package Busan.Matjib.repository.Travel;

import Busan.Matjib.domain.busan.travel.QTravel;
import Busan.Matjib.dto.Travel.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static Busan.Matjib.domain.busan.travel.QTravel.travel;

public class TravelRepositoryImpl implements TravelRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public TravelRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<TravelBoardDto> searchTravel(TravelBoardSearch condition, Pageable pageable) {
        List<TravelBoardDto> travelContext = queryFactory
                .select(new QTravelBoardDto(
                        travel.id.as("travelId"),
                        travel.travelPlace,
                        travel.travelTitle,
                        travel.travelLocation.lng,
                        travel.travelLocation.lat,
                        travel.travelDisabled,
                        travel.travelPrice,
                        travel.travelIntroduce.Tel,
                        travel.travelPublic_transport,
                        travel.travelDayOff,
                        travel.travelSubtitle,
                        travel.travelIntroduce.introduce,
                        travel.travelIntroduce.Business_hours,
                        travel.travelIntroduce.Ward,
                        travel.travelIntroduce.Addr,
                        travel.travelHomepage,
                        travel.travelIntroduce.ImgAddr,
                        travel.cnt
                ))
                .from(travel)
                .where(
                    TravelPlaceEq(condition.getTravelPlace()),
                        TravelTitleEq(condition.getTravelTitle()),
                        TravelWardEq(condition.getTravelWard())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(travel.count())
                .from(travel)
                .where(
                        TravelPlaceEq(condition.getTravelPlace()),
                        TravelTitleEq(condition.getTravelTitle()),
                        TravelWardEq(condition.getTravelWard()));

        return PageableExecutionUtils.getPage(travelContext, pageable, count::fetchOne);
    }

    @Override
    public List<TravelBoardDto> travelTop3() {
        return queryFactory
                .select(new QTravelBoardDto(
                        QTravel.travel.id.as("travelId"),
                        QTravel.travel.travelPlace,
                        QTravel.travel.travelTitle,
                        QTravel.travel.travelLocation.lng,
                        QTravel.travel.travelLocation.lat,
                        QTravel.travel.travelDisabled,
                        QTravel.travel.travelPrice,
                        QTravel.travel.travelIntroduce.Tel,
                        QTravel.travel.travelPublic_transport,
                        QTravel.travel.travelDayOff,
                        QTravel.travel.travelSubtitle,
                        QTravel.travel.travelIntroduce.introduce,
                        QTravel.travel.travelIntroduce.Business_hours,
                        QTravel.travel.travelIntroduce.Ward,
                        QTravel.travel.travelIntroduce.Addr,
                        QTravel.travel.travelHomepage,
                        QTravel.travel.travelIntroduce.ImgAddr,
                        QTravel.travel.cnt
                ))
                .from(travel)
                .orderBy(travel.cnt.desc()
                        , travel.id.asc())
                .offset(0)
                .limit(3)
                .fetch();
    }

    @Override
    public List<TravelWard> wardCount() {
        return queryFactory
                .select(new QTravelWard(
                        travel.travelIntroduce.Ward, travel.count().intValue()))
                .from(travel)
                .groupBy(travel.travelIntroduce.Ward)
                .fetch();
    }

    private BooleanExpression TravelPlaceEq(String place) {
        return StringUtils.hasText(place) ? travel.travelPlace.contains(place) : null;
    }

    private BooleanExpression TravelTitleEq(String title) {
        return StringUtils.hasText(title) ? travel.travelTitle.contains(title) : null;
    }

    private BooleanExpression TravelWardEq(String ward) {
        return StringUtils.hasText(ward) ? travel.travelIntroduce.Ward.contains(ward) :null;
    }

}
