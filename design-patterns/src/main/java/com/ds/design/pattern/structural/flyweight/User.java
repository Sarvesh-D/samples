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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@Slf4j
public class User {

    private static final Map<Long, User> userRegistry = new HashMap<>();

    private final long id;

    private final String name;

    private User(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static User create(final Long id, final String name) {
        if (get(id).isPresent()) {
            log.info("Returning User with ID {} from registry", id);
            return get(id).get();
        }
        synchronized (User.class) {
            final User user = new User(id, name);
            if (!userRegistry.containsKey(id)) {
                log.info("Adding User {} to registry", user);
                userRegistry.put(user.getId(), user);
            }
        }
        return userRegistry.get(id);
    }

    private static Optional<User> get(final long id) {
        return Optional.ofNullable(userRegistry.get(id));
    }

}
