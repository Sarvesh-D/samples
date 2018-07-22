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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode(of = "key")
@ToString
public class ObjectNode implements Node {

    @NonNull
    private final String key;

    private final List<Node> childNodes = new ArrayList<>();

    @Override
    public void add(final Node node) {
        childNodes.add(node);
    }

    @Override
    public void remove(final Node node) {
        childNodes.remove(node);
    }

    @Override
    public String getValue() {
        return childNodes.stream()
                         .map(Node::getValueAsString)
                         .collect(Collectors.joining(",", "{", "}"));
    }

    @Override
    public String getValueAsString() {
        return getValue();
    }

    @Override
    public boolean getValueAsBoolean() {
        throw new UnsupportedOperationException("Cannot get boolean value for Object Node");
    }

    @Override
    public int getValueAsInt() {
        throw new UnsupportedOperationException("Cannot get Integer value for Object Node");
    }

    @Override
    public List<Node> getChildren() {
        return Collections.unmodifiableList(childNodes);
    }

    @Override
    public String get() {
        return key + "=" + getValue();
    }

    @Override
    public String getKey() {
        return key;
    }

}
