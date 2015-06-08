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
package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.graphic.UDrawable;

abstract class Extremity implements UDrawable {


	protected double manageround(double angle) {
		final double deg = angle * 180.0 / Math.PI;
		if (isCloseToo(0, deg)) {
			return 0;
		}
		if (isCloseToo(90, deg)) {
			return 90.0 * Math.PI / 180.0;
		}
		if (isCloseToo(180, deg)) {
			return 180.0 * Math.PI / 180.0;
		}
		if (isCloseToo(270, deg)) {
			return 270.0 * Math.PI / 180.0;
		}
		if (isCloseToo(360, deg)) {
			return 0;
		}
		return angle;
	}

	private boolean isCloseToo(double value, double variable) {
		if (Math.abs(value - variable) < 0.05) {
			return true;
		}
		return false;
	}

}
