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
package net.sourceforge.plantuml.creole;

import java.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.salt.element.Skeleton2;
import net.sourceforge.plantuml.ugraphic.UChangeColor;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class AtomTree implements Atom {

	private final HtmlColor lineColor;
	private final List<Atom> cells = new ArrayList<Atom>();
	private final Map<Atom, Integer> levels = new HashMap<Atom, Integer>();
	private final double margin = 2;

	public AtomTree(HtmlColor lineColor) {
		this.lineColor = lineColor;
	}

	public Dimension2D calculateDimension(StringBounder stringBounder) {
		final Skeleton2 skeleton = new Skeleton2();
		double width = 0;
		double height = 0;
		for (Atom cell : cells) {
			final Dimension2D dim = cell.calculateDimension(stringBounder);
			height += dim.getHeight();
			final int level = getLevel(cell);
			width = Math.max(width, skeleton.getXEndForLevel(level) + margin + dim.getWidth());
		}
		return new Dimension2DDouble(width, height);
	}

	public double getStartingAltitude(StringBounder stringBounder) {
		return 0;
	}

	public void drawU(final UGraphic ugInit) {
		final Skeleton2 skeleton = new Skeleton2();
		double y = 0;
		UGraphic ug = ugInit;
		for (Atom cell : cells) {
			final int level = getLevel(cell);
			cell.drawU(ug.apply(new UTranslate(margin + skeleton.getXEndForLevel(level), 0)));
			final Dimension2D dim = cell.calculateDimension(ug.getStringBounder());
			skeleton.add(level, y + dim.getHeight() / 2);
			ug = ug.apply(new UTranslate(0, dim.getHeight()));
			y += dim.getHeight();
		}
		skeleton.draw(ugInit.apply(new UChangeColor(this.lineColor)));
	}

	private int getLevel(Atom atom) {
		return levels.get(atom);
	}

	public void addCell(Atom cell, int level) {
		this.cells.add(cell);
		this.levels.put(cell, level);
	}

}
