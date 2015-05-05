/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.abien.messageme.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;


public class ConnectionManagerTest {

	@Test
	public void testGetConnection() throws Exception {
		// 
		String host = "localhost";
		int port = 5432;
		String dbname = "jasperdb9";
		String username = "postgres";
		String password = "postgres";
		ConnectionManager connectionManager = new ConnectionManager(host, port, dbname, username, password);
		assertNotNull(connectionManager.getConnection());
	}

}
