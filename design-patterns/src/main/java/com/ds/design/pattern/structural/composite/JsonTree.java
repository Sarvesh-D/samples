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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.ds.design.pattern.structural.flyweight.User;

public class JsonTree {

    private JsonTree() {
    }

    private static final Set<Class<?>> WRAPPER_TYPES = new HashSet<>();

    private static final User INSTANCE = User.create(0L, "any");

    static {
        WRAPPER_TYPES.add(Boolean.class);
        WRAPPER_TYPES.add(Character.class);
        WRAPPER_TYPES.add(Byte.class);
        WRAPPER_TYPES.add(Short.class);
        WRAPPER_TYPES.add(Integer.class);
        WRAPPER_TYPES.add(Long.class);
        WRAPPER_TYPES.add(Float.class);
        WRAPPER_TYPES.add(Double.class);
        WRAPPER_TYPES.add(Void.class);
        WRAPPER_TYPES.add(String.class);
    }

    public static boolean isWrapperType(final Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    public static List<Node> from(final Map<String, Object> data) {
        final List<Node> nodes = data.entrySet()
                                     .stream()
                                     .filter(entry -> WRAPPER_TYPES.contains(entry.getValue()
                                                                                  .getClass()))
                                     .map(entry -> new LeafNode(entry.getKey(), entry.getValue()))
                                     .collect(Collectors.toList());

        nodes.addAll(data.entrySet()
                         .stream()
                         .filter(entry -> !WRAPPER_TYPES.contains(entry.getValue()
                                                                       .getClass()))
                         .map(JsonTree::toObjectNode)
                         .collect(Collectors.toList()));
        return nodes;
    }

    public static Node toObjectNode(final Entry<String, Object> entry) {
        try {
            final ObjectNode objectNode = new ObjectNode(entry.getKey());
            Arrays.stream(entry.getValue()
                               .getClass()
                               .getDeclaredFields())
                  .map(field -> {
                      try {
                          return new LeafNode(field.getName(), field.get(INSTANCE));
                      } catch (IllegalArgumentException | IllegalAccessException e) {
                          e.printStackTrace();
                          return null;
                      }
                  })
                  .forEach(objectNode::add);
            return objectNode;
        } catch (IllegalArgumentException | SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

}
