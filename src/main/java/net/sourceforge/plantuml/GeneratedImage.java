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
package net.sourceforge.plantuml;

import java.io.File;

import net.sourceforge.plantuml.core.Diagram;

public class GeneratedImage implements Comparable<GeneratedImage> {

	private final File pngFile;
	private final String description;
	private final BlockUml blockUml;

	public GeneratedImage(File pngFile, String description, BlockUml blockUml) {
		this.blockUml = blockUml;
		this.pngFile = pngFile;
		this.description = description;
	}

	public File getPngFile() {
		return pngFile;
	}

	public String getDescription() {
		return description;
	}

	public int lineErrorRaw() {
		final Diagram system = blockUml.getDiagram();
		if (system instanceof PSystemError) {
			return ((PSystemError) system).getHigherErrorPosition() + blockUml.getStartLine();
		}
		return -1;
	}

	@Override
	public String toString() {
		return pngFile.getAbsolutePath() + " " + description;
	}

	public int compareTo(GeneratedImage this2) {
		final int cmp = this.pngFile.compareTo(this2.pngFile);
		if (cmp != 0) {
			return cmp;
		}
		return this.description.compareTo(this2.description);
	}

	@Override
	public int hashCode() {
		return pngFile.hashCode() + description.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		final GeneratedImage this2 = (GeneratedImage) obj;
		return this2.pngFile.equals(this.pngFile) && this2.description.equals(this.description);
	}

}
