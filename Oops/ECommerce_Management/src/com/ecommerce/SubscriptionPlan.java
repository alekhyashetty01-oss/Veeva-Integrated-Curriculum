package com.ecommerce;
import java.time.Period;
public enum SubscriptionPlan {
    MONTHLY(Period.ofMonths(1)),
    QUARTERLY(Period.ofMonths(3)),
    HALF_YEARLY(Period.ofMonths(6)),
    ANNUAL(Period.ofYears(1));

    private final Period duration;
    SubscriptionPlan(Period duration) { this.duration = duration; }
    public Period getDuration() { return duration; }
}
