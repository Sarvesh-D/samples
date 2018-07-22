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

import lombok.Getter;

/**
 * Multiple ways to make Connection Class as singleton
 *
 *
 * @since 18-03-2018
 * @version
 */
@Getter
public class Connection {

    private final String url;

    private final String userName;

    private final String password;

    private Connection(final String url, final String userName, final String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    // Approach 1
    public static final Connection INSTANCE_1 = new Connection("url", "root", "root");

    private static final Connection INSTANCE_2 = new Connection("url", "root", "root");

    public static Connection THREAD_SAFE_INSTANCE;

    // Approach 2
    public static Connection getInstance() {
        return INSTANCE_2;
    }

    // Approach 3
    public static final Connection threadSafeInstance() {
        if (null == THREAD_SAFE_INSTANCE) {
            synchronized (Connection.class) {
                if (null == THREAD_SAFE_INSTANCE) {
                    THREAD_SAFE_INSTANCE = new Connection("url", "root", "root");
                }
            }
        }
        return THREAD_SAFE_INSTANCE;
    }

    // Approach 4 - BEST
    @Getter
    public enum CONNECTION {

        INSTANCE;

        private String url;

        private String userName;

        private String password;

        void setUrl(final String url) {
            this.url = url;
        }

        void setUserName(final String userName) {
            this.userName = userName;
        }

        void setPassword(final String password) {
            this.password = password;
        }

    }

}
