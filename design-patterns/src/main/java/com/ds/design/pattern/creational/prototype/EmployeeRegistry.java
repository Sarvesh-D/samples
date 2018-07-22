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
package com.ds.design.pattern.creational.prototype;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;

import com.ds.utils.Random;

public class EmployeeRegistry {

    public enum EmployeeType {
        DEVELOPER,
        MANAGER
    }

    private static final EnumMap<EmployeeType, Employee> registry = new EnumMap<>(EmployeeType.class);

    static {
        registry.put(EmployeeType.DEVELOPER, new Developer(Random.firstName.get(), Random.lastName.get(), Random.age(18, 40), new HashSet<>(Arrays.asList("R1", "R2"))));
        registry.put(EmployeeType.MANAGER, new Manager(Random.firstName.get(), Random.lastName.get(), Random.age(30, 60), new HashSet<>(Arrays.asList("R3", "R4"))));
    }

    public static Employee get(final EmployeeType type) {
        return registry.get(type);
    }

}
