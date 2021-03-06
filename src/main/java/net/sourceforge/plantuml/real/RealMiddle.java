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
package net.sourceforge.plantuml.real;

class RealMiddle extends AbstractReal implements Real {

	private final RealMoveable p1;
	private final RealMoveable p2;
	private final double delta;

	private RealMiddle(RealMoveable p1, RealMoveable p2, double delta) {
		super(p1.getLine());
		this.p1 = p1;
		this.p2 = p2;
		this.delta = delta;
	}

	RealMiddle(RealMoveable p1, RealMoveable p2) {
		this(p1, p2, 0);
	}

	@Override
	double getCurrentValueInternal() {
		return (p1.getCurrentValue() + p2.getCurrentValue()) / 2 + delta;
	}

	public Real addFixed(double diff) {
		return new RealMiddle(p1, p2, delta + diff);
	}

	public Real addAtLeast(double delta) {
		throw new UnsupportedOperationException();
	}

	public void ensureBiggerThan(Real other) {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		return "[Middle " + p1.getName() + " and " + p2.getName() + "]";
	}

	public void printCreationStackTrace() {
		throw new UnsupportedOperationException();
	}

}
