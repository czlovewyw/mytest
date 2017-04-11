/*
 * ZooInspector
 * 
 * Copyright 2010 Colin Goodheart-Smithe

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.cz.inspector.encryption;

/**
 * A class which describes how data should be encrypted and decrypted
 * 
 * @author CGSmithe
 * 
 */
public interface DataEncryptionManager
{
	/**
	 * @param data
	 *            - the data to be encrypted
	 * @return the encrypted data
	 * @throws Exception
	 */
	public byte[] encryptData(String data) throws Exception;

	/**
	 * @param encrypted
	 *            - the data to be decrypted
	 * @return the decrypted data
	 * @throws Exception
	 */
	public String decryptData(byte[] encrypted) throws Exception;
}
