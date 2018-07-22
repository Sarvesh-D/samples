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

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode(of = "key")
@ToString
public class LeafNode implements Node {

    @NonNull
    private final String key;

    private final Object value;

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getValueAsString() {
        return null != getValue() ? getValue().toString() : null;
    }

    @Override
    public boolean getValueAsBoolean() {
        return Boolean.valueOf(getValueAsString());
    }

    @Override
    public int getValueAsInt() {
        return Integer.valueOf(getValueAsString());
    }

    @Override
    public String get() {
        return key + "=" + getValueAsString();
    }

    @Override
    public String getKey() {
        return key;
    }

}
