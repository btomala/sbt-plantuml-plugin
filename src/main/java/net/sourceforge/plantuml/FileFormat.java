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

/**
 * Format for output files generated by PlantUML.
 * 
 * @author Arnaud Roques
 * 
 */
public enum FileFormat {
	PNG, SVG, EPS, EPS_TEXT, ATXT, UTXT, XMI_STANDARD, XMI_STAR, XMI_ARGO, PDF, MJPEG, ANIMATED_GIF, HTML, HTML5, VDX, LATEX, BASE64;

	/**
	 * Returns the file format to be used for that format.
	 * 
	 * @return a string starting by a point.
	 */
	public String getFileSuffix() {
		if (name().startsWith("XMI")) {
			return ".xmi";
		}
		if (this == MJPEG) {
			return ".avi";
		}
		if (this == ANIMATED_GIF) {
			return ".gif";
		}
		if (this == EPS_TEXT) {
			return EPS.getFileSuffix();
		}
		return "." + StringUtils.goLowerCase(name());
	}

	/**
	 * Check if this file format is Encapsulated PostScript.
	 * 
	 * @return <code>true</code> for EPS.
	 */
	public boolean isEps() {
		if (this == EPS) {
			return true;
		}
		if (this == EPS_TEXT) {
			return true;
		}
		return false;
	}

	public String changeName(String fileName, int cpt) {
		if (cpt == 0) {
			return changeName(fileName, getFileSuffix());
		}
		return changeName(fileName, "_" + String.format("%03d", cpt) + getFileSuffix());
	}

	private String changeName(String fileName, String replacement) {
		String result = fileName.replaceAll("\\.\\w+$", replacement);
		if (result.equals(fileName)) {
			result = fileName + replacement;
		}
		return result;
	}

	public File computeFilename(File pngFile, int i) {
		if (i == 0) {
			return pngFile;
		}
		final File dir = pngFile.getParentFile();
		return new File(dir, computeFilename(pngFile.getName(), i));
		// String name = pngFile.getName();
		// name = name.replaceAll("\\" + getFileSuffix() + "$", "_" + String.format("%03d", i) + getFileSuffix());
		// return new File(dir, name);

	}

	public String computeFilename(String name, int i) {
		if (i == 0) {
			return name;
		}
		return name.replaceAll("\\" + getFileSuffix() + "$", "_" + String.format("%03d", i) + getFileSuffix());
	}

}
