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
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressionZlib implements Compression {

	public byte[] compress(byte[] in) {
		int len = in.length * 2;
		byte[] result = null;
		while (result == null) {
			result = tryCompress(in, len);
			len *= 2;
		}
		return result;
	}

	private byte[] tryCompress(byte[] in, final int len) {
		// Compress the bytes
		final Deflater compresser = new Deflater(9, true);
		compresser.setInput(in);
		compresser.finish();

		final byte[] output = new byte[len];
		final int compressedDataLength = compresser.deflate(output);
		if (compresser.finished() == false) {
			return null;
		}
		final byte[] result = copyArray(output, compressedDataLength);
		return result;
	}

	public byte[] decompress(byte[] in) throws IOException {

		final byte in2[] = new byte[in.length + 256];
		for (int i = 0; i < in.length; i++) {
			in2[i] = in[i];
		}

		int len = in.length * 5;
		byte[] result = null;
		while (result == null) {
			result = tryDecompress(in2, len);
			len *= 2;
		}
		return result;
	}

	private byte[] tryDecompress(byte[] in, final int len) throws IOException {
		// Decompress the bytes
		final byte[] tmp = new byte[len];
		final Inflater decompresser = new Inflater(true);
		decompresser.setInput(in);
		try {
			final int resultLength = decompresser.inflate(tmp);
			if (decompresser.finished() == false) {
				return null;
			}
			decompresser.end();

			final byte[] result = copyArray(tmp, resultLength);
			return result;
		} catch (DataFormatException e) {
			// e.printStackTrace();
			throw new IOException(e.toString());
		}
	}

	private byte[] copyArray(final byte[] data, final int len) {
		final byte[] result = new byte[len];
		for (int i = 0; i < result.length; i++) {
			result[i] = data[i];
		}
		return result;
	}

}
