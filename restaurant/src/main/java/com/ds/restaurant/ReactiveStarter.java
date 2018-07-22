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
package com.ds.restaurant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.ds.subway.sandwich.common.Sandwich;
import com.ds.subway.sandwich.common.Utils;

import io.reactivex.Observable;

public class ReactiveStarter {

    public static void main(final String[] args) throws InterruptedException {

        final List<Sandwich> sandwiches = Utils.randomEnglishSanwiches(100)
                                               .collect(Collectors.toList());
        final Observable<Sandwich> sandwichStream = Observable.fromIterable(sandwiches);

        // sandwichStream.subscribeOn(Schedulers.computation()).subscribe(ReactiveStarter::printThreadInfo,
        // t -> t.printStackTrace(), () -> System.out.println("Completed..."));
        final StringBuilder pattern = new StringBuilder();

        Observable.fromIterable(stars(50))
                  .scan(pattern, (acc, star) -> {
                      return acc.append(star);
                  })
                  .subscribe(acc -> System.out.println(pattern.toString()));

        Thread.sleep(2000);

    }

    private static void printThreadInfo(final Object... args) {
        final String threadName = String.format("Thread - {%s} | ", Thread.currentThread()
                                                                          .getName());
        final String info = Stream.of(args)
                                  .map(Object::toString)
                                  .collect(Collectors.joining(" , ", threadName, StringUtils.EMPTY));
        System.out.println(info);
    }

    private static List<String> stars(final int count) {
        return Stream.generate(() -> "*")
                     .limit(count)
                     .collect(Collectors.toList());
    }

}
