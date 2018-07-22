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
package com.ds.design.pattern.creational.builder;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Sandwich {

    private String bread;

    private String cheese;

    private String meat;

    private String[] veggies;

    private String[] dressings;

    private final String style;

    private Sandwich(final SandwichBuilder sandwichBuilder) {
        this.style = sandwichBuilder.getStyle();
        this.bread = sandwichBuilder.getBread()
                                    .orElse(StringUtils.EMPTY);
        this.cheese = sandwichBuilder.getCheese()
                                     .orElse(StringUtils.EMPTY);
        this.meat = sandwichBuilder.getMeat()
                                   .orElse(StringUtils.EMPTY);
        this.veggies = sandwichBuilder.getVeggies()
                                      .orElse(null);
        this.dressings = sandwichBuilder.getDressings()
                                        .orElse(null);
    }

    @Getter
    public static class SandwichBuilder {

        private Optional<String> bread = Optional.empty();

        private Optional<String> cheese = Optional.empty();

        private Optional<String> meat = Optional.empty();

        private Optional<String[]> veggies = Optional.empty();

        private Optional<String[]> dressings = Optional.empty();

        private final String style;

        public SandwichBuilder(final String style) {
            this.style = style;
        }

        public SandwichBuilder bread(final String bread) {
            this.bread = Optional.ofNullable(bread);
            return this;
        }

        public SandwichBuilder cheese(final String cheese) {
            this.cheese = Optional.ofNullable(cheese);
            return this;
        }

        public SandwichBuilder meat(final String meat) {
            this.meat = Optional.ofNullable(meat);
            return this;
        }

        public SandwichBuilder veggies(final String... veggies) {
            this.veggies = Optional.ofNullable(veggies);
            return this;
        }

        public SandwichBuilder dressings(final String... dressings) {
            this.dressings = Optional.ofNullable(dressings);
            return this;
        }

        public Sandwich build() {
            return new Sandwich(this);
        }

    }

}
