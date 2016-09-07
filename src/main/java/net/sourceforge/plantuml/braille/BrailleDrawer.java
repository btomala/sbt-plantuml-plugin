/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2017, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
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
package net.sourceforge.plantuml.braille;

import net.sourceforge.plantuml.graphic.HtmlColorSetSimple;
import net.sourceforge.plantuml.graphic.HtmlColorUtils;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.ugraphic.UChangeBackColor;
import net.sourceforge.plantuml.ugraphic.UChangeColor;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class BrailleDrawer implements UDrawable {

	private final BrailleGrid grid;
	private final double step = 9;
	private final double spotSize = 5;

	public BrailleDrawer(BrailleGrid grid) {
		this.grid = grid;
	}

	public void drawU(UGraphic ug) {
		ug = ug.apply(new UChangeColor(new HtmlColorSetSimple().getColorIfValid("#F0F0F0")));
		for (int x = grid.getMinX(); x <= grid.getMaxX(); x++) {
			ug.apply(new UTranslate(x * step + spotSize + 1, 0)).draw(
					new ULine(0, (grid.getMaxY() - grid.getMinY()) * step));
		}
		for (int y = grid.getMinY(); y <= grid.getMaxY(); y++) {
			ug.apply(new UTranslate(0, y * step + spotSize + 1)).draw(
					new ULine((grid.getMaxX() - grid.getMinX()) * step, 0));
		}
		ug = ug.apply(new UChangeColor(HtmlColorUtils.BLACK)).apply(new UChangeBackColor(HtmlColorUtils.BLACK));
		for (int x = grid.getMinX(); x <= grid.getMaxX(); x++) {
			for (int y = grid.getMinY(); y <= grid.getMaxY(); y++) {
				if (grid.getState(x, y)) {
					drawCircle(ug, x, y);
				}
			}
		}
	}

	private void drawCircle(UGraphic ug, int x, int y) {
		final double cx = x * step;
		final double cy = y * step;
		ug.apply(new UTranslate(cx, cy)).draw(new UEllipse(spotSize, spotSize));
	}

}
