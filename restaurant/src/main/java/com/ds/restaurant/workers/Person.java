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
package com.ds.restaurant.workers;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode(of = { "name", "age" })
@ToString
@Getter
public class Person implements Comparable<Person> {

    private final String name;

    private final Integer age;

    @Override
    public int compareTo(final Person o) {
        if (this.age.compareTo(o.age) == 0) {
            return this.name.compareTo(o.getName());
        }
        return this.age.compareTo(o.age);
    }

    public static void main(final String[] args) {
        final Person sarvesh = new Person("Sarvesh", 25);
        final Person tanjila = new Person("Tanjila", 25);

        final Set<Person> hashSet = new HashSet<>();
        hashSet.add(sarvesh);
        hashSet.add(tanjila);
        System.out.println("Hashset = " + hashSet);

        final Set<Person> treeSet = new TreeSet<>();
        treeSet.add(tanjila);
        treeSet.add(sarvesh);
        System.out.println("Treeset = " + treeSet);

    }

    static class PersonAgeComparator implements Comparator<Person> {

        @Override
        public int compare(final Person o1, final Person o2) {
            return o1.getAge()
                     .compareTo(o2.getAge());
        }

    }

}
