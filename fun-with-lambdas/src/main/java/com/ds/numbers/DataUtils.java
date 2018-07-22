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

import java.util.function.Supplier;
import java.util.stream.Stream;

import com.ds.model.Person;
import com.ds.utils.Random;

public class DataUtils {

    public static final Supplier<Stream<String>> firstNamesSupplier = () -> Stream.generate(Random.firstName);

    public static final Supplier<Stream<String>> lastNamesSupplier = () -> Stream.generate(Random.lastName);

    public static final Supplier<Stream<String>> citiesSupplier = () -> Stream.generate(Random.city);

    public static final Supplier<Person> randomPerson = () -> new Person(Random.firstName.get(), Random.lastName.get(), Random.age(18, 40), Random.city.get(), Random.sex.get());

    public static final Supplier<Stream<Person>> personSupplier = () -> Stream.generate(randomPerson);

    public static Stream<String> firstNames() {
        return firstNamesSupplier.get();
    }

    public static Stream<String> lastNames() {
        return lastNamesSupplier.get();
    }

    public static Stream<String> cities() {
        return citiesSupplier.get();
    }

    public static Stream<Person> persons() {
        return personSupplier.get();
    }

}
