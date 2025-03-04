/*
 * Copyright (c) 2008-2023, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.sql.impl.schema.datalink;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.hazelcast.sql.impl.SqlDataSerializerHook;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.hazelcast.datalink.impl.DataLinkServiceImpl.DataLinkSource;

/**
 * SQL schema POJO class.
 */
public class DataLinkCatalogEntry implements IdentifiedDataSerializable {
    private String name;
    private String type;
    private boolean shared;
    private Map<String, String> options;
    private DataLinkSource source;

    public DataLinkCatalogEntry() {
    }

    public DataLinkCatalogEntry(String name, String type, boolean shared, Map<String, String> options) {
        this.name = name;
        this.type = type;
        this.shared = shared;
        this.options = options;
        this.source = DataLinkSource.SQL;
    }

    public DataLinkCatalogEntry(
            String name,
            String type,
            boolean shared,
            Map<String, String> options,
            DataLinkSource source) {
        this.name = name;
        this.shared = shared;
        this.type = type;
        this.options = options;
        this.source = source;
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public boolean isShared() {
        return shared;
    }

    public Map<String, String> options() {
        return options;
    }

    public DataLinkSource source() {
        return source;
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        name = in.readString();
        type = in.readString();
        shared = in.readBoolean();
        options = in.readObject();
        source = in.readObject();
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeString(name);
        out.writeString(type);
        out.writeBoolean(shared);
        out.writeObject(options);
        out.writeObject(source);
    }

    @Override
    public int getFactoryId() {
        return SqlDataSerializerHook.F_ID;
    }

    @Override
    public int getClassId() {
        return SqlDataSerializerHook.DATA_LINK_CATALOG_ENTRY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataLinkCatalogEntry e = (DataLinkCatalogEntry) o;
        return shared == e.shared && name.equals(e.name) && type.equals(e.type) && options.equals(e.options)
                && source == e.source;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, shared, options, source);
    }
}
