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
package com.ds.design.pattern.creational.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FactoryTester {

    @Test
    public void testFactory() {
        final UIComponent button = UIComponentFactory.get(UIComponents.BUTTON);
        final UIComponent dialogBox = UIComponentFactory.get(UIComponents.DIALOG);
        button.display();
        dialogBox.display();
        assertTrue("Factory not implemented correctly", Button.class.isAssignableFrom(button.getClass()));
        assertFalse("Factory not implemented correctly", DialogBox.class.isAssignableFrom(button.getClass()));
        assertTrue("Factory not implemented correctly", DialogBox.class.isAssignableFrom(dialogBox.getClass()));
        assertFalse("Factory not implemented correctly", Button.class.isAssignableFrom(dialogBox.getClass()));
    }

}
