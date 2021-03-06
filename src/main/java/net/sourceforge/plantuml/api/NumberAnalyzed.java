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
package net.sourceforge.plantuml.api;

public class NumberAnalyzed implements INumberAnalyzed {

	private int nb;
	private int sum;
	private int min;
	private int max;

	public NumberAnalyzed() {

	}

	private NumberAnalyzed(int nb, int sum, int min, int max) {
		this.nb = nb;
		this.sum = sum;
		this.min = min;
		this.max = max;
	}

	public synchronized INumberAnalyzed getCopyImmutable() {
		final NumberAnalyzed copy = new NumberAnalyzed(nb, sum, min, max);
		return copy;
	}

	public synchronized void addValue(int v) {
		nb++;
		if (nb == 1) {
			sum = v;
			min = v;
			max = v;
			return;
		}
		sum += v;
		if (v > max) {
			max = v;
		}
		if (v < min) {
			min = v;
		}
	}

	synchronized public final int getNb() {
		return nb;
	}

	synchronized public final int getSum() {
		return sum;
	}

	synchronized public final int getMin() {
		return min;
	}

	synchronized public final int getMax() {
		return max;
	}

	synchronized public final int getMean() {
		if (nb == 0) {
			return 0;
		}
		return sum / nb;
	}

}
