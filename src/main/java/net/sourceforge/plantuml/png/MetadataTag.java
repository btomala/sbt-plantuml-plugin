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
package net.sourceforge.plantuml.png;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class MetadataTag {

	private final File f;
	private final String tag;

	public MetadataTag(File f, String tag) {
		this.f = f;
		this.tag = tag;
	}

	public String getData() throws IOException {
		final ImageInputStream iis = ImageIO.createImageInputStream(f);
		final Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

		if (readers.hasNext()) {
			// pick the first available ImageReader
			final ImageReader reader = readers.next();

			// attach source to the reader
			reader.setInput(iis, true);

			// read metadata of first image
			final IIOMetadata metadata = reader.getImageMetadata(0);

			final String[] names = metadata.getMetadataFormatNames();
			final int length = names.length;
			for (int i = 0; i < length; i++) {
				final String result = displayMetadata(metadata.getAsTree(names[i]));
				if (result != null) {
					return result;
				}
			}
		}

		return null;
	}

	private String displayMetadata(Node root) {
		return displayMetadata(root, 0);
	}

	private String displayMetadata(Node node, int level) {
		final NamedNodeMap map = node.getAttributes();
		if (map != null) {
			final Node keyword = map.getNamedItem("keyword");
			if (keyword != null && tag.equals(keyword.getNodeValue())) {
				final Node text = map.getNamedItem("value");
				if (text != null) {
					return text.getNodeValue();
				}
			}
		}

		Node child = node.getFirstChild();

		// children, so close current tag
		while (child != null) {
			// print children recursively
			final String result = displayMetadata(child, level + 1);
			if (result != null) {
				return result;
			}
			child = child.getNextSibling();
		}

		return null;

	}

}
