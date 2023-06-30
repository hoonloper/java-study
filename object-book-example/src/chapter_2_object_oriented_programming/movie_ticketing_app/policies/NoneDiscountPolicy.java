package chapter_2_object_oriented_programming.movie_ticketing_app.policies;

import chapter_2_object_oriented_programming.movie_ticketing_app.Money;
import chapter_2_object_oriented_programming.movie_ticketing_app.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {
    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return null;
    }
}
