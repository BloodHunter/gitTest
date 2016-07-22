package com.wbl.test.Enum;

/**
 * Created by Simple_love on 2015/10/20.
 */
public interface Competitor<T extends Competitor> {
        Outcome compete(T competitor);
}
