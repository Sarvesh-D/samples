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
package com.ds.design.pattern.structural.flyweight;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FlyWeightTester {

    @Test
    public void testFlyWeight() {
        final User user_1 = User.create(1L, "Ram");
        final User user_2 = User.create(2L, "Sham");
        final User user_3 = User.create(1L, "Ram");

        assertFalse("FlyWeight not implemented properly", user_1.equals(user_2));
        assertTrue("FlyWeight not implemented properly", user_1 == user_3);
        assertTrue("FlyWeight not implemented properly", user_1.equals(user_3));
    }
}
