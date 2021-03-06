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
package net.sourceforge.plantuml.project;

class FormalAdditionInstantDuration implements Formal {

	private final Expression exp1;
	private final Expression exp2;
	private final InstantArithmetic math;

	public FormalAdditionInstantDuration(Expression exp1, Expression exp2, InstantArithmetic math) {
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.math = math;
	}

	public String getDescription() {
		return "addID " + exp1 + " " + exp2;
	}

	public NumericType getNumericType() {
		return exp1.getNumericType();
	}

	public Numeric getValue() {
		if (exp2.getNumericType() == NumericType.NUMBER) {
			final Duration d = new Duration((NumericNumber) exp2.getValue());
			return math.add((Instant) exp1.getValue(), d);
		}

		return math.add((Instant) exp1.getValue(), (Duration) exp2.getValue());
	}

}
