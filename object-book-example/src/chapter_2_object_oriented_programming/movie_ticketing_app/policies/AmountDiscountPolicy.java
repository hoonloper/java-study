package chapter_2_object_oriented_programming.movie_ticketing_app.policies;

import chapter_2_object_oriented_programming.movie_ticketing_app.conditions.DiscountCondition;
import chapter_2_object_oriented_programming.movie_ticketing_app.Money;
import chapter_2_object_oriented_programming.movie_ticketing_app.Screening;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {
    private Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
