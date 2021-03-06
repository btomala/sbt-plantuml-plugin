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
package net.sourceforge.plantuml.hector;

import java.util.Comparator;

public class SkeletonConfigurationComparator implements Comparator<SkeletonConfiguration> {

	private final SkeletonConfigurationEvaluator evaluator;

	public SkeletonConfigurationComparator(SkeletonConfigurationEvaluator evaluator) {
		this.evaluator = evaluator;
	}

	public int compare(SkeletonConfiguration sc1, SkeletonConfiguration sc2) {
		final double price1 = evaluator.getPrice(sc1);
		final double price2 = evaluator.getPrice(sc2);
		if (price1 > price2) {
			return 1;
		}
		if (price1 < price2) {
			return -1;
		}
		return 0;
	}

}
