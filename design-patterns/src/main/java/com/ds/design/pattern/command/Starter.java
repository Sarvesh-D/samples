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
package com.ds.design.pattern.command;

import java.util.stream.IntStream;

public class Starter {

    private static final int TOTAL_THREADS = 1000000;

    public static void main(final String[] args) throws InterruptedException {

        final LongWrapper longWrapper = new LongWrapper(0);
        final Thread[] threads = new Thread[TOTAL_THREADS];

        final Runnable r = () -> IntStream.range(0, 1000)
                                          .forEach(i -> longWrapper.increment());

        IntStream.range(0, TOTAL_THREADS)
                 .forEach(i -> threads[i] = new Thread(r));

        IntStream.range(0, TOTAL_THREADS)
                 .forEach(i -> threads[i].start());

        IntStream.range(0, TOTAL_THREADS)
                 .forEach(i -> {
                     try {
                         threads[i].join();
                     } catch (final InterruptedException e) {
                         e.printStackTrace();
                     }
                 });

        System.out.println(longWrapper.getVal());

        final DeadLock deadLock = new DeadLock();

        IntStream.range(0, TOTAL_THREADS / 2)
                 .forEach(i -> threads[i] = new Thread(deadLock::a));
        IntStream.range(TOTAL_THREADS / 2, TOTAL_THREADS)
                 .forEach(i -> threads[i] = new Thread(deadLock::b));

        IntStream.range(0, TOTAL_THREADS)
                 .forEach(i -> threads[i].start());

        IntStream.range(0, TOTAL_THREADS)
                 .forEach(i -> {
                     try {
                         threads[i].join();
                     } catch (final InterruptedException e) {
                         e.printStackTrace();
                     }
                 });

    }

}
