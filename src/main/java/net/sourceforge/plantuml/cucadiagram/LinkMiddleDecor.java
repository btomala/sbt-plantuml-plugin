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
package net.sourceforge.plantuml.cucadiagram;

import net.sourceforge.plantuml.svek.extremity.MiddleCircleCircledMode;
import net.sourceforge.plantuml.svek.extremity.MiddleFactory;
import net.sourceforge.plantuml.svek.extremity.MiddleFactoryCircle;
import net.sourceforge.plantuml.svek.extremity.MiddleFactoryCircleCircled;

public enum LinkMiddleDecor {

	NONE, CIRCLE, CIRCLE_CIRCLED, CIRCLE_CIRCLED1, CIRCLE_CIRCLED2;

	public MiddleFactory getMiddleFactory() {
		if (this == CIRCLE) {
			return new MiddleFactoryCircle();
		}
		if (this == CIRCLE_CIRCLED) {
			return new MiddleFactoryCircleCircled(MiddleCircleCircledMode.BOTH);
		}
		if (this == CIRCLE_CIRCLED1) {
			return new MiddleFactoryCircleCircled(MiddleCircleCircledMode.MODE1);
		}
		if (this == CIRCLE_CIRCLED2) {
			return new MiddleFactoryCircleCircled(MiddleCircleCircledMode.MODE2);
		}
		throw new UnsupportedOperationException();
	}

}
