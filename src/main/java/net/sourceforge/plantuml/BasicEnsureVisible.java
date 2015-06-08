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

public class BasicEnsureVisible implements EnsureVisible {

	private double minX = Double.MAX_VALUE;
	private double maxX = -Double.MAX_VALUE;
	private double minY = Double.MAX_VALUE;
	private double maxY = -Double.MAX_VALUE;

	public void ensureVisible(double x, double y) {
		if (x > maxX) {
			maxX = x;
		}
		if (x < minX) {
			minX = x;
		}
		if (y > maxY) {
			maxY = y;
		}
		if (y < minY) {
			minY = y;
		}
	}

	public String getCoords(double scale) {
		if (minX == Double.MAX_VALUE) {
			return "0,0,0,0";
		}
		final int x1 = (int) (minX * scale);
		final int y1 = (int) (minY * scale);
		final int x2 = (int) (maxX * scale);
		final int y2 = (int) (maxY * scale);
		return "" + x1 + "," + y1 + "," + x2 + "," + y2;
	}

	public double getSurface() {
		if (minX == Double.MAX_VALUE) {
			return 0;
		}
		return (maxX - minX) * (maxY - minY);
	}

}
