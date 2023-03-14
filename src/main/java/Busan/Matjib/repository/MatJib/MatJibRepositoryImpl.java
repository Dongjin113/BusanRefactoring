package Busan.Matjib.repository.MatJib;

import Busan.Matjib.dto.MatJib.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static Busan.Matjib.domain.busan.matjib.QMatJib.matJib;

public class MatJibRepositoryImpl implements MatJibRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MatJibRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<MatJibBoardDto> searchMatJib(MatJibBoardSearch condition, Pageable pageable){
        List<MatJibBoardDto> matJibContext = queryFactory.
                select(new QMatJibBoardDto(
                        matJib.id.as("MatJibId"),
                        matJib.matJibMain_title,
                        matJib.matJibLocation.lng,
                        matJib.matJibLocation.lat,
                        matJib.matJibintroduce.Tel,
                        matJib.matJibintroduce.introduce,
                        matJib.matJibintroduce.Business_hours,
                        matJib.matJibintroduce.Ward,
                        matJib.matJibintroduce.Addr,
                        matJib.matJibMenu,
                        matJib.matJibintroduce.ImgAddr,
                        matJib.cnt
                ))
                .from(matJib)
                .where(
                        MatJibMenuEq(condition.getMatJibMenu()),
                        MatJibMainTitleEq(condition.getMatJibMain_title()),
                        MatJibWardEq(condition.getMatJibWard())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.
                select(matJib.count())
                .from(matJib)
                .where(
                        MatJibMenuEq(condition.getMatJibMenu()),
                        MatJibMainTitleEq(condition.getMatJibMain_title()),
                        MatJibWardEq(condition.getMatJibWard())
                );

        return PageableExecutionUtils.getPage(matJibContext, pageable , count::fetchOne);

    }

    @Override
    public List<MatJibBoardDto> matJibTop3(){
        return queryFactory.
                select(new QMatJibBoardDto(
                        matJib.id.as("MatJibId"),
                        matJib.matJibMain_title,
                        matJib.matJibLocation.lng,
                        matJib.matJibLocation.lat,
                        matJib.matJibintroduce.Tel,
                        matJib.matJibintroduce.introduce,
                        matJib.matJibintroduce.Business_hours,
                        matJib.matJibintroduce.Ward,
                        matJib.matJibintroduce.Addr,
                        matJib.matJibMenu,
                        matJib.matJibintroduce.ImgAddr,
                        matJib.cnt
                ))
                .from(matJib)
                .orderBy(matJib.cnt.desc()
                ,matJib.id.asc()
                )
                .offset(0)
                .limit(3)
                .fetch();
    }

    @Override
    public List<MatJibWard> wardCount() {
        return queryFactory
                .select( new QMatJibWard(
                        matJib.matJibintroduce.Ward, matJib.count().intValue()
                ))
                .from(matJib)
                .groupBy(matJib.matJibintroduce.Ward)
                .fetch();
    }

    private BooleanExpression MatJibMainTitleEq(String mainTitle) {
        return StringUtils.hasText(mainTitle) ? matJib.matJibMain_title.contains(mainTitle) : null;
    }

    private BooleanExpression MatJibMenuEq(String menu) {
        return StringUtils.hasText(menu) ? matJib.matJibMenu.contains(menu) : null;
    }

    private BooleanExpression MatJibWardEq(String ward) {
        return StringUtils.hasText(ward) ? matJib.matJibintroduce.Ward.contains(ward) :null;
    }



}
