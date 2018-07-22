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
package com.ds.design.pattern.creational.singleton;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ds.design.pattern.creational.singleton.Connection.CONNECTION;

@RunWith(JUnit4.class)
public class SingletonTester {

    @Test
    public void testSingletonApproaches() {
        assertTrue("Approach 1 : Not a Singleton", Connection.INSTANCE_1 == Connection.INSTANCE_1);
        assertTrue("Approach 2 : Not a Singleton", Connection.getInstance() == Connection.getInstance());
        assertTrue("Approach 3 : Not a Singleton", Connection.threadSafeInstance() == Connection.threadSafeInstance());

        final CONNECTION connection_1 = Connection.CONNECTION.INSTANCE;
        connection_1.setUrl("url");
        connection_1.setUserName("root");
        connection_1.setPassword("root");

        final CONNECTION connection_2 = Connection.CONNECTION.INSTANCE;
        assertTrue("Approach 4 : Not a Singleton", Connection.CONNECTION.INSTANCE == Connection.CONNECTION.INSTANCE);
        assertTrue("Improper password set", connection_2.getPassword()
                                                        .equals("root"));
    }

}
