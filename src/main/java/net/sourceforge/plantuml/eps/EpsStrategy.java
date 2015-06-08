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
package net.sourceforge.plantuml.eps;

public enum EpsStrategy {

	VERY_SIMPLE, WITH_MACRO, WITH_MACRO_AND_TEXT;

	public EpsGraphics creatEpsGraphics() {
		if (this == VERY_SIMPLE) {
			return new EpsGraphics();
		}
		if (this == WITH_MACRO) {
			return new EpsGraphicsMacro();
		}
		if (this == WITH_MACRO_AND_TEXT) {
			return new EpsGraphicsMacroAndText();
		}
		throw new IllegalArgumentException();
	}

	public static EpsStrategy getDefault2() {
		return WITH_MACRO;
	}

}
