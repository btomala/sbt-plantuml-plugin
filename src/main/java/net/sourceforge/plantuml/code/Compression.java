/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2014, Arnaud Roques
 *
 * Project Info:  http://plantuml.sourceforge.net
 * 
 * This file is part of PlantUML.
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
 *
 *
 * Original Author:  Arnaud Roques
 */
package net.sourceforge.plantuml.code;

import java.io.IOException;

public interface Compression {

	/**
	 * Shrinks the given <code>in</code> array with length <code>len</code>
	 * 
	 * @return a newly created array with the compressed data.
	 */
	byte[] compress(final byte[] in);

	/**
	 * Grows the given <code>in</code> array with length <code>len</code>
	 * compressed with the <code>shrink</code> method.
	 * 
	 * @return a newly created array with the expanded data.
	 */
	byte[] decompress(byte[] in) throws IOException;

}
