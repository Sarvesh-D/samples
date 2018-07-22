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
package com.ds.design.pattern.creational.abstractfactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AbstractFactoryTester {

    @Test
    public void testAbstractFactory() {
        final UIComponentAbstractFactory macFactory = UIComponentAbstractFactoryFactory.getFactoryFor(System.MAC);
        final UIComponentAbstractFactory windowFactory = UIComponentAbstractFactoryFactory.getFactoryFor(System.WINDOW);
        macFactory.getButton()
                  .display();
        windowFactory.getButton()
                     .display();
        macFactory.getDialogBox()
                  .display();
        windowFactory.getDialogBox()
                     .display();

        assertTrue("Abstract Factory not implemented correctly", MacButton.class.isAssignableFrom(macFactory.getButton()
                                                                                                            .getClass()));
        assertFalse("Abstract Factory not implemented correctly", MacButton.class.isAssignableFrom(windowFactory.getButton()
                                                                                                                .getClass()));
        assertTrue("Abstract Factory not implemented correctly", WindowsDialogBox.class.isAssignableFrom(windowFactory.getDialogBox()
                                                                                                                      .getClass()));
        assertFalse("Abstract Factory not implemented correctly", WindowsDialogBox.class.isAssignableFrom(macFactory.getDialogBox()
                                                                                                                    .getClass()));
    }

}
