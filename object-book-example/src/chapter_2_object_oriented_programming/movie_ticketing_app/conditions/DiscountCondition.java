package chapter_2_object_oriented_programming.movie_ticketing_app.conditions;

import chapter_2_object_oriented_programming.movie_ticketing_app.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
