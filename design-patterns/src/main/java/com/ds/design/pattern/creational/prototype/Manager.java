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

import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
final final @EqualsAndHashCode(callSuper = true) public class Manager extends Employee {

    public Manager(final String firstName, final String lastName, final int age, final Set<String> roles) {
        super(RandomUtils.nextLong(), firstName, lastName, age, roles);
    }

}
