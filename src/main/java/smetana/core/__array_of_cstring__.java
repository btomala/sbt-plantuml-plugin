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

package smetana.core;

import java.util.ArrayList;
import java.util.List;

import smetana.core.amiga.Area;
import smetana.core.amiga.BuilderArea;
import smetana.core.amiga.StarStruct;

public class __array_of_cstring__ implements Area {

	private final List<Area> data;
	private final int currentPos;

	private final int UID = StarStruct.CPT++;

	public String getUID36() {
		return Integer.toString(UID, 36);
	}

	public void swap(int i, int j) {
		Area e1 = data.get(i);
		Area e2 = data.get(j);
		data.set(i, e2);
		data.set(j, e1);
	}

	@Override
	public String toString() {
		if (data.get(0) != null) {
			return "__array__ " + getUID36() + " " + currentPos + "/" + data.size() + " " + data.get(0).toString();
		}
		return "__array__ " + getUID36() + " " + currentPos + "/" + data.size();
	}

	public void realloc(int nb) {
		while (data.size() < nb + currentPos) {
			data.add(null);
		}
	}

	// public __ptr__ asPtr() {
	// return new StarArray(this);
	// }

	public int comparePointerInternal(__array_of_cstring__ other) {
		if (this.data != other.data) {
			throw new IllegalArgumentException();
		}
		return this.currentPos - other.currentPos;
	}
	public static __array_of_cstring__ mallocStarChar(int nb) {
		return new __array_of_cstring__(nb, new BuilderArea() {
			public Area createArea() {
				return null;
			}
		});
	}

	private __array_of_cstring__(List<Area> data, int currentPos) {
		this.data = data;
		this.currentPos = currentPos;
		check();
	}

	private __array_of_cstring__(int size, BuilderArea builder) {
		this.data = new ArrayList<Area>();
		this.currentPos = 0;
		for (int i = 0; i < size; i++) {
			final Area tmp = builder.createArea();
			data.add(tmp);
		}
		check();
	}

	private void check() {
		if (getUID36().equals("194")) {
			JUtils.LOG("It's me");
		}
	}

	public __array_of_cstring__ move(int delta) {
		return new __array_of_cstring__(data, currentPos + delta);
	}

	public __array_of_cstring__ plus(int delta) {
		return move(delta);
	}

	public Area getInternal(int idx) {
		return data.get(idx + currentPos);
	}

	public void setInternalByIndex(int idx, Area value) {
		if (value == this) {
			throw new IllegalArgumentException();
		}
		if (value instanceof __array_of_cstring__) {
			throw new IllegalArgumentException();
		}
		data.set(idx + currentPos, value);
	}

	public void memcopyFrom(Area source) {
		throw new UnsupportedOperationException();
	}

	//

	public CString getCString() {
		return (CString) getInternal(0);
	}

	public void setCString(CString value) {
		setInternalByIndex(0, value);
	}

}
