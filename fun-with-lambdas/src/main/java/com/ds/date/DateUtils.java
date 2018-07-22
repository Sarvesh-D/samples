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
package com.ds.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class DateUtils {

    public static final Supplier<LocalDate> currentDate = LocalDate::now;

    public static final Supplier<LocalTime> currentTime = LocalTime::now;

    public static final Supplier<LocalDateTime> currentDateTime = LocalDateTime::now;

    public static final BiFunction<LocalDateTime, Integer, LocalDateTime> addDays = (instance, days) -> instance.plus(Period.ofDays(days));

    public static final BiFunction<LocalDateTime, Integer, LocalDateTime> addMonths = (instance, months) -> instance.plus(Period.ofMonths(months));

    public static final BiFunction<LocalDateTime, Integer, LocalDateTime> addYears = (instance, years) -> instance.plus(Period.ofYears(years));

    public static final BiFunction<LocalDateTime, Integer, LocalDateTime> minusDays = (instance, days) -> instance.minus(Period.ofDays(days));

    public static final BiFunction<LocalDateTime, Integer, LocalDateTime> minusMonths = (instance, months) -> instance.minus(Period.ofMonths(months));

    public static final BiFunction<LocalDateTime, Integer, LocalDateTime> minusYears = (instance, years) -> instance.minus(Period.ofYears(years));

    public static final BiFunction<LocalDateTime, LocalDateTime, Long> daysBetween = ChronoUnit.DAYS::between;

    public static final BiFunction<LocalDateTime, LocalDateTime, Long> monthsBetween = ChronoUnit.MONTHS::between;

    public static final Supplier<ZonedDateTime> timeInPakistan = () -> ZonedDateTime.now(ZoneId.of("Asia/Karachi"));

    public static final Supplier<ZonedDateTime> timeInIndia = () -> ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));

}
