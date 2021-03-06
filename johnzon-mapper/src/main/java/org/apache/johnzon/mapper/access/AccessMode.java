/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.johnzon.mapper.access;

import org.apache.johnzon.mapper.Adapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Map;

public interface AccessMode {
    interface DecoratedType {
        Type getType();
        <T extends Annotation> T getAnnotation(Class<T> clazz);
        <T extends Annotation> T getClassOrPackageAnnotation(Class<T> clazz);
        Adapter<?, ?> findConverter();
        boolean isNillable();
    }

    interface Writer extends DecoratedType {
        void write(Object instance, Object value);
    }

    interface Reader extends DecoratedType {
        Object read(Object instance);
    }

    interface Factory {
        Object create(Object[] params);
        Type[] getParameterTypes();
        String[] getParameterNames();
        Adapter<?, ?>[] getParameterConverter();
        Adapter<?, ?>[] getParameterItemConverter();
    }

    Factory findFactory(Class<?> clazz);
    Comparator<String> fieldComparator(Class<?> clazz);
    Map<String, Reader> findReaders(Class<?> clazz);
    Map<String, Writer> findWriters(Class<?> clazz);
}
