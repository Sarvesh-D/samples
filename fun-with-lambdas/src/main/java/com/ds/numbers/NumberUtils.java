/*******************************************************************************
 * Copyright 2018 <a href=https://github.com/Sarvesh-D/>Sarvesh Dubey</a>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.ds.numbers;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class NumberUtils {

    public static final UnaryOperator<Integer> incrementByOne = i -> i + 1;

    private static final Supplier<Stream<Integer>> wholeNumberSupplier = () -> Stream.iterate(0, incrementByOne);

    private static final Supplier<Stream<Integer>> naturalNumberSupplier = () -> Stream.iterate(1, incrementByOne);

    public static final BiPredicate<Integer, Integer> factor = (i, j) -> i % j == 0;

    public static final Predicate<Integer> even = i -> factor.test(i, 2);

    public static final Predicate<Integer> odd = even.negate();

    public static final Function<Integer, List<Integer>> factors = i -> IntStream.rangeClosed(1, i)
                                                                                 .mapToObj(Integer::valueOf)
                                                                                 .filter(j -> factor.test(i, j))
                                                                                 .collect(Collectors.toList());

    public static final Predicate<Integer> prime = i -> factors.apply(i)
                                                               .size() == 2;

    public static final Predicate<Integer> composite = prime.negate();

    public static final Function<Integer, List<Integer>> primeFactors = i -> IntStream.rangeClosed(1, i)
                                                                                      .mapToObj(Integer::valueOf)
                                                                                      .filter(prime)
                                                                                      .filter(j -> factor.test(i, j))
                                                                                      .collect(Collectors.toList());

    public static final Stream<Integer> naturalNumbers() {
        return naturalNumberSupplier.get();
    }

    public static final Stream<Integer> wholeNumbers() {
        return wholeNumberSupplier.get();
    }
}
