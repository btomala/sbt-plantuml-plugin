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
package net.sourceforge.plantuml.cute;

import net.sourceforge.plantuml.ugraphic.UGraphic;

public class Triangle implements CuteShape {

	private final CutePath cutePath;

	public Triangle(VarArgs varArgs) {
		this(varArgs.getPointList("points"));
	}

	private Triangle(CutePath cutePath) {
		this.cutePath = cutePath;
		// if (points.size() != 3) {
		// throw new IllegalArgumentException();
		// }
	}

	public Triangle rotateZoom(final RotationZoom angle) {
		if (angle.isNone()) {
			return this;
		}
		return new Triangle(cutePath.rotateZoom(angle));
	}

	public void drawU(UGraphic ug) {
		cutePath.drawU(ug);
		// ug = ug.apply(new UChangeBackColor(null)).apply(new UChangeColor(HtmlColorUtils.BLACK));
		// cutePath.withNoTension().drawU(
		// ug.apply(new UChangeBackColor(null)).apply(new UChangeColor(HtmlColorUtils.BLACK)));

	}
}
