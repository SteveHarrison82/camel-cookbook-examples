/*
 * Copyright (C) Scott Cranton, Jakub Korab, and Christian Posta
 * https://github.com/CamelCookbook
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camelcookbook.rest.hello;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.AvailablePortFinder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class HelloWorldTest extends CamelTestSupport {
    private final int port1 = AvailablePortFinder.getNextAvailable();

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new HelloWorldRouteBuilder(port1);
    }

    @Test
    public void testHello() throws Exception {
        String out = fluentTemplate().to("http://localhost:" + port1 + "/say/hello").request(String.class);
        assertEquals("Hello World", out);
    }

    @Test
    public void testBye() throws Exception {
        String out = fluentTemplate().to("http://localhost:" + port1 + "/say/bye").request(String.class);
        assertEquals("Bye World", out);
    }
}