/**
 *
 * Copyright 2017 Observational Health Data Sciences and Informatics
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Company: Odysseus Data Services, Inc.
 * Product Owner/Architecture: Gregory Klebanov
 * Authors: Pavel Grafkin, Alexandr Ryabokon, Vitaly Koulakov, Anton Gackovka, Maria Pozhidaeva, Mikhail Mironov
 * Created: August 03, 2017
 *
 */

package com.odysseusinc.arachne.portal.model.statemachine;


import java.util.function.Predicate;

public interface Transition<O extends HasState, S extends IsState> {

    String getDescription();

    S getTo();
    S getFrom();


    /**
     * Because of transitions are stored in memory, getCopy method should be provided.
     * TODO move to extended interface
     *
     * @return copy of this.
     */
    <T extends Transition<O, S>> T getCopy();


    /*
     * TODO Later we will be able to store additional information in place of info boolean flag.
     */

    default boolean isInfo() {
        return Boolean.FALSE;
    }
    default void setInfo() {
    }

    default Predicate<O> getDiscriminator() {
        return (object) -> Boolean.FALSE;
    }
}
