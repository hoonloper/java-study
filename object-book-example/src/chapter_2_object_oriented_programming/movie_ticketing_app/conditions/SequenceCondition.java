package chapter_2_object_oriented_programming.movie_ticketing_app.conditions;

import chapter_2_object_oriented_programming.movie_ticketing_app.Screening;

public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    public boolean isSatisfiedBy(Screening screening) {
        return screening.isSequence(sequence);
    }
}
