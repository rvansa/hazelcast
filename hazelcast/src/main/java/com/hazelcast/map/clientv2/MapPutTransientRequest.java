/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.map.clientv2;

import com.hazelcast.map.MapPortableHook;
import com.hazelcast.map.PutTransientOperation;
import com.hazelcast.map.TryPutOperation;
import com.hazelcast.nio.serialization.Data;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;

public class MapPutTransientRequest extends MapPutRequest {

    public MapPutTransientRequest() {
    }

    public MapPutTransientRequest(String name, Data key, Data value, int threadId, long ttl) {
        super(name, key, value, threadId, ttl);
    }

    public int getClassId() {
        return MapPortableHook.PUT_TRANSIENT;
    }

    public Object process() throws Exception {
        System.err.println("Running MapPutTransientRequest");
        PutTransientOperation op = new PutTransientOperation(name, key, value, ttl);
        op.setThreadId(threadId);
        return clientEngine.invoke(getServiceName(), op, key);
    }
}
