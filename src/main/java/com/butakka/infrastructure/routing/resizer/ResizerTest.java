package com.butakka.infrastructure.routing.resizer;

import akka.routing.Resizer;
import akka.routing.Routee;
import scala.collection.immutable.IndexedSeq;

/**
 * Created by Jesus Barquín on 28/02/15.
 */
public class ResizerTest implements Resizer {

    @Override
    public boolean isTimeForResize(long l) {
        return false;
    }

    @Override
    public int resize(IndexedSeq<Routee> routeeIndexedSeq) {
        return 0;
    }
}
