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

import com.ds.restaurant.workers.Chef;
import com.ds.restaurant.workers.Waiter;
import com.ds.subway.sandwich.common.Sandwich;
import com.ds.subway.sandwich.common.Utils;
import com.ds.utils.CollectionUtils;

public class Starter_1 {

    private static final int CHEF_LIMIT = 3;

    private static final int WAITER_LIMIT = 4;

    private static final int BUFFER_LIMIT = 48;

    private static final int TOTAL_CHEFS = 4;

    private static final int TOTAL_WAITERS = 5;

    static List<Sandwich> sandwiches = new ArrayList<>();

    static int sandwichesDeliverd = 0;

    private static final Object lock = new Object();

    public static void main(final String[] args) throws InterruptedException {
        final Chef<Sandwich> chef = Utils::randomEnglishSanwiches;
        final Waiter<Sandwich> waiter = (i) -> CollectionUtils.removeRandomElems(sandwiches, i);

        final Runnable producer = () -> {
            synchronized (lock) {
                while (BUFFER_LIMIT != sandwichesDeliverd) {
                    if (BUFFER_LIMIT == sandwiches.size()) {
                        try {
                            System.out.println("Waiting for delivering");
                            lock.wait();
                        } catch (final InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    final int qtyRemaining = BUFFER_LIMIT - sandwiches.size();
                    final int qty = CHEF_LIMIT <= qtyRemaining ? CHEF_LIMIT : qtyRemaining;
                    sandwiches.addAll(chef.produce(qty)
                                          .collect(Collectors.toList()));
                    System.out.println(String.format("[%s] Producing [%d] sandwiches", Thread.currentThread()
                                                                                             .getName(), qty));
                    lock.notify(); // notify waiters that sandwiches are produced
                    try {
                        Thread.sleep(2000); // rest the chef for 2 seconds while waiter delivers
                    } catch (final InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        final Runnable consumer = () -> {
            synchronized (lock) {
                while (BUFFER_LIMIT != sandwichesDeliverd) {
                    if (sandwiches.isEmpty()) {
                        try {
                            System.out.println("Waiting for production");
                            lock.wait();
                        } catch (final InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    final int qty = sandwiches.size() - WAITER_LIMIT <= 0 ? sandwiches.size() : WAITER_LIMIT <= sandwiches.size() ? WAITER_LIMIT : sandwiches.size();
                    waiter.deliver(qty);
                    sandwichesDeliverd += qty;
                    System.out.println(String.format("[%s] Delivering [%d] sandwiches", Thread.currentThread()
                                                                                              .getName(), qty));
                    lock.notify();
                }
            }
        };

        final Thread producerThread = new Thread(producer, "Producer Thread");
        final Thread consumerThread = new Thread(consumer, "Consumer Thread");

        consumerThread.start();
        producerThread.start();

        consumerThread.join();
        producerThread.join();

        System.out.println("Number of sandwiches delivered = " + sandwichesDeliverd);
        System.out.println("Number of undelivered sandwiches = " + sandwiches.size());
    }

}
