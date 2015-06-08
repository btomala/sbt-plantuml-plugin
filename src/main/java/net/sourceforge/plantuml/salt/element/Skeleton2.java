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
package net.sourceforge.plantuml.salt.element;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.URectangle;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class Skeleton2 {

	private final List<Entry> entries = new ArrayList<Entry>();
	private static final double sizeX = 8;

	static class Entry {
		private final int level;
		private final double ypos;

		Entry(int level, double y) {
			System.err.println("level=" + level);
			this.level = level;
			this.ypos = y;
		}

		void drawHline(UGraphic ug) {
			final double xpos = getXStartForLevel(level);
			ug.apply(new UTranslate(xpos + sizeX - 1, ypos - 1)).draw(new URectangle(2, 2));
			ug.apply(new UTranslate(xpos, ypos)).draw(new ULine(sizeX, 0));
		}

		public void drawVline(UGraphic ug, double lastY) {
			System.err.println("ypos=" + ypos);
			final double xpos = getXStartForLevel(level);
			ug.apply(new UTranslate(xpos, lastY)).draw(new ULine(0, ypos - lastY));
		}
	}

	public void add(int level, double y) {
		entries.add(new Entry(level, y));
	}

	public void draw(UGraphic ug) {
		for (int i = 0; i < entries.size(); i++) {
			final Entry en = entries.get(i);
			en.drawHline(ug);
			final Entry up = getMotherOrSister(i);
			en.drawVline(ug, up == null ? 0 : up.ypos);
		}
	}

	private Entry getMotherOrSister(int idx) {
		final int currentLevel = entries.get(idx).level;
		for (int i = idx - 1; i >= 0; i--) {
			final int otherLevel = entries.get(i).level;
			if (otherLevel == currentLevel || otherLevel == currentLevel - 1) {
				return entries.get(i);
			}
		}
		return null;
	}

	private static double getXStartForLevel(int level) {
		return level * sizeX;
	}

	public double getXEndForLevel(int level) {
		return getXStartForLevel(level) + sizeX;
	}

	// public void drawOld(UGraphic ug, double x, double y) {
	// for (int i = 0; i < entries.size(); i++) {
	// final Entry en = entries.get(i);
	// if (i + 1 < entries.size() && entries.get(i + 1).xpos > en.xpos) {
	// en.drawRectangle(ug);
	// }
	// Entry parent = null;
	// for (int j = 0; j < i; j++) {
	// final Entry en0 = entries.get(j);
	// if (en0.xpos < en.xpos) {
	// parent = en0;
	// }
	// }
	// if (parent != null) {
	// drawChild(ug, parent, en);
	// }
	// }
	// }
	//
	// private void drawChild(UGraphic ug, Entry parent, Entry child) {
	// final double dy = child.ypos - parent.ypos - 2;
	// ug.apply(new UTranslate(parent.xpos + 1, parent.ypos + 3)).draw(new ULine(0, dy));
	//
	// final double dx = child.xpos - parent.xpos - 2;
	// ug.apply(new UTranslate(parent.xpos + 1, child.ypos + 1)).draw(new ULine(dx, 0));
	//
	// }

}
