package chapter_2_object_oriented_programming.movie_ticketing_app.policies;

import chapter_2_object_oriented_programming.movie_ticketing_app.Money;
import chapter_2_object_oriented_programming.movie_ticketing_app.Screening;
import chapter_2_object_oriented_programming.movie_ticketing_app.conditions.DiscountCondition;
import chapter_2_object_oriented_programming.movie_ticketing_app.policies.DefaultDiscountPolicy;

public class PercentDiscountPolicy extends DefaultDiscountPolicy {
    private double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
