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
package com.ds.design.pattern.structural.composite;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ds.design.pattern.structural.flyweight.User;
import com.ds.utils.Random;

import lombok.extern.slf4j.Slf4j;

@RunWith(JUnit4.class)
@Slf4j
public class CompositeTester {

    @Test
    public void testComposite() {
        final Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("uuid", RandomUtils.nextLong());
        jsonData.put("age", Random.age(18, 40));
        jsonData.put("firstName", Random.firstName.get());
        jsonData.put("lastName", Random.lastName.get());
        jsonData.put("sex", Random.sex.get());

        final List<Node> nodes = JsonTree.from(jsonData);
        log.info("Data = \n{}", nodes);
        assertTrue("Composite not implemented properly", nodes.size() == jsonData.size());
        final Object age = nodes.stream()
                                .filter(node -> "age".equals(node.getKey()))
                                .findFirst()
                                .get()
                                .getValue();

        assertTrue("Composite not implemented properly", Integer.class.isAssignableFrom(age.getClass()));

        final Map<String, Object> jsonData_1 = new HashMap<>();
        // jsonData_1.put("developer", new Developer("Sarvesh", "Dubey", 27, new
        // HashSet<>()));
        jsonData_1.put("user", User.create(RandomUtils.nextLong(), "Sarvesh"));
        final List<Node> objectNodes = JsonTree.from(jsonData_1);
        log.info("Object Node = \n{}", objectNodes.get(0)
                                                  .getValue());

    }

}
