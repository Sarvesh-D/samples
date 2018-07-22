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
package com.ds.lambdas;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.ds.model.Message;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunStarter {

    public static void main(final String[] args) throws URISyntaxException, IOException {

        log.info("Current Date {}", LocalDate.now());
        log.info("Current Time {}", LocalTime.now());
        log.info("Current Date Time {}", LocalDateTime.now());
        log.info("Current Day {}", LocalDate.now()
                                            .getDayOfWeek()
                                            .name());
        log.info("Current Time in Pak {}", LocalDateTime.now(ZoneId.of("Asia/Karachi")));
        log.info("Date after 5 days {}", LocalDate.now()
                                                  .plus(5, ChronoUnit.DAYS));
        log.info("Days Since Joining {}", Period.between(LocalDate.of(2018, 04, 02), LocalDate.now())
                                                .getDays());

        final Stream<String> lines = Files.lines(Paths.get(FunStarter.class.getClassLoader()
                                                                           .getResource("chat.txt")
                                                                           .toURI()));

        final Stream<Message> messages = lines.map(line -> new Message(time(line), author(line), null));

        messages.map(Message::toString)
                .forEach(log::info);

    }

    private static LocalDateTime time(@NonNull final String chatLine) {
        try {
            return LocalDateTime.parse(StringUtils.substringBefore(chatLine, "-")
                                                  .trim(), DateTimeFormatter.ofPattern("M/dd/yy, h:mm a"));
        } catch (final Exception e) {
            return null;
        }
    }

    private static String author(@NonNull final String chatLine) {
        try {
            return StringUtils.substringBetween(chatLine, "-", ":")
                              .trim();
        } catch (final Exception e) {
            return StringUtils.EMPTY;
        }
    }
}
