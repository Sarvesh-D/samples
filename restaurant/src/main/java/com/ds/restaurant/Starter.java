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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ds.restaurant.workers.Chef;
import com.ds.restaurant.workers.Waiter;
import com.ds.subway.sandwich.common.Sandwich;
import com.ds.subway.sandwich.common.Utils;

public class Starter {

    private static final Object key = new Object();

    private static Chef<Sandwich> chef;

    private static Waiter<Sandwich> waiter;

    private static final int ORDER_QTY = 99;

    private static final int TOTAL_CHEFS = 3;

    private static final int TOTAL_WAITERS = 10;

    private static final int MAX_ITEMS_PER_CHEF = 3;

    private static final int MAX_ITEMS_PER_WAITER = 4;

    private static final List<Sandwich> BUFFER = new ArrayList<>();

    public static void main(final String[] args) {
        startRestaurant();
        prepareOrder(ORDER_QTY);
        // deliverOrder(ORDER_QTY);
    }

    private static void startRestaurant() {
        chef = Utils::randomEnglishSanwiches;

        waiter = (qty) -> {
            final List<Sandwich> sandwichesToDeliver = BUFFER.stream()
                                                             .limit(qty)
                                                             .collect(Collectors.toList());
            BUFFER.removeAll(sandwichesToDeliver);
        };
    }

    private static void prepareOrder(final int orderQty) {
        // Producer
        final Runnable sandwichProducer = () -> {
            synchronized (key) {
                System.out.println(Thread.currentThread()
                                         .getName());
                BUFFER.addAll(chef.produce(MAX_ITEMS_PER_CHEF)
                                  .collect(Collectors.toList()));
            }
        };

        while (BUFFER.size() <= orderQty) {
            startWorkers(getWorkers(sandwichProducer, TOTAL_CHEFS));
        }

        System.out.println("Finished producing...");
        System.out.println("Sandwiches Produced = " + BUFFER.size());
    }

    private static void deliverOrder(final int orderQty) {
        // Consumer
        final Runnable sandwichWaiter = () -> {
            synchronized (key) {
                waiter.deliver(MAX_ITEMS_PER_WAITER);
            }
        };

        while (BUFFER.size() != 0) {
            startWorkers(getWorkers(sandwichWaiter, TOTAL_WAITERS));
        }

        System.out.println("Sandwiches Delivered...");
        System.out.println("Sandwiches left to deliver : " + BUFFER.size());
    }

    private static List<Thread> getWorkers(final Runnable worker, final int totalWorkers) {
        return Stream.generate(() -> new Thread(worker))
                     .limit(totalWorkers)
                     .collect(Collectors.toList());
    }

    private static void startWorkers(final List<Thread> workers) {
        workers.stream()
               .forEach(Thread::start);
        workers.stream()
               .forEach(worker -> {
                   try {
                       worker.join();
                   } catch (final InterruptedException e) {
                       e.printStackTrace();
                   }
               });
    }

}
