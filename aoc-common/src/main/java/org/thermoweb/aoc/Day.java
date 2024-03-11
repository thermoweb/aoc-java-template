package org.thermoweb.aoc;

import java.math.BigInteger;
import java.util.Optional;

public interface Day {

    Optional<BigInteger> partOne(String input);

    Optional<BigInteger> partTwo(String input);
}
