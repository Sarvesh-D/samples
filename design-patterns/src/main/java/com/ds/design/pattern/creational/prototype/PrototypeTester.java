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

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ds.design.pattern.creational.prototype.EmployeeRegistry.EmployeeType;

@RunWith(JUnit4.class)
public class PrototypeTester {

    @Test
    public void testPrototype() throws CloneNotSupportedException {
        final Employee developer_1 = EmployeeRegistry.get(EmployeeType.DEVELOPER);
        final Employee developer_2 = (Employee) developer_1.clone();
        assertTrue("Prototype Not cloned properly", developer_1.getId() != developer_2.getId());
        assertTrue("Prototype Not cloned properly", developer_1.getFirstName()
                                                               .equals(developer_2.getFirstName()));
        assertTrue("Prototype Not cloned properly", developer_1.getLastName()
                                                               .equals(developer_2.getLastName()));
        assertTrue("Prototype Not cloned properly", developer_1.getAge() == developer_2.getAge());
        assertTrue("Prototype Not cloned properly", developer_1.getRoles()
                                                               .equals(developer_2.getRoles()));
        assertTrue("Prototype Not cloned properly", developer_1.getRoles() != developer_2.getRoles());

        developer_1.getRoles()
                   .add("R6");
        assertTrue("Prototype Not cloned properly", developer_1.getRoles()
                                                               .size() > developer_2.getRoles()
                                                                                    .size());
    }

}
