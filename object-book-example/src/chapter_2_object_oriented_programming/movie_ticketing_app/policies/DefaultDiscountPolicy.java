package chapter_2_object_oriented_programming.movie_ticketing_app.policies;

import chapter_2_object_oriented_programming.movie_ticketing_app.conditions.DiscountCondition;
import chapter_2_object_oriented_programming.movie_ticketing_app.Money;
import chapter_2_object_oriented_programming.movie_ticketing_app.Screening;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DefaultDiscountPolicy implements DiscountPolicy {
    private List<DiscountCondition> conditions = new ArrayList<>();

    public DefaultDiscountPolicy(DiscountCondition ... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    public Money calculateDiscountAmount(Screening screening) {
        for(DiscountCondition each : conditions) {
            if(each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }
        return Money.ZERO;
    }

    protected abstract Money getDiscountAmount(Screening screening);
}
