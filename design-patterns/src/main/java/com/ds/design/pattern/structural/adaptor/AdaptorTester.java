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
package com.ds.design.pattern.structural.adaptor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AdaptorTester {

    @Test
    public void testAdaptor() {
        final ADUser adUser = new ADUser("ad_user_f_name", "ad_user_l_name", "ad_user_mail");
        final User user_1 = new ADUserAdaptor(adUser);
        assertEquals("Adaptor not implemented properly", adUser.getFirst_name(), user_1.getFirstName());
        assertEquals("Adaptor not implemented properly", adUser.getLast_name(), user_1.getLastName());
        assertEquals("Adaptor not implemented properly", adUser.getE_mail(), user_1.getEmailId());

        final CRMUser crmUser = new CRMUser("crm_user_f_name", "crm_user_l_name", "crm_user_mail");
        final User user_2 = new CRMUserAdaptor(crmUser);
        assertEquals("Adaptor not implemented properly", crmUser.getFName(), user_2.getFirstName());
        assertEquals("Adaptor not implemented properly", crmUser.getLName(), user_2.getLastName());
        assertEquals("Adaptor not implemented properly", crmUser.getMail(), user_2.getEmailId());

    }

}
