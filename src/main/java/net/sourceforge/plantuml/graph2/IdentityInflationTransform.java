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
package net.sourceforge.plantuml.graph2;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IdentityInflationTransform implements IInflationTransform {

	public void addInflationX(double xpos, double inflation) {

	}

	public void addInflationY(double ypos, double inflation) {

	}

	public double getTotalInflationX() {
		return 0;
	}

	public double getTotalInflationY() {
		return 0;
	}

	public Point2D inflatePoint2D(Point2D point) {
		return point;
	}

	public List<Line2D.Double> inflate(Collection<Line2D.Double> segments) {
		return new ArrayList<Line2D.Double>(segments);
	}

}
