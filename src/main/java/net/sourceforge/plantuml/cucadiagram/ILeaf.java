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

import net.sourceforge.plantuml.cucadiagram.dot.Neighborhood;
import net.sourceforge.plantuml.graphic.USymbol;
import net.sourceforge.plantuml.svek.IEntityImage;

public interface ILeaf extends IEntity {

	public EntityPosition getEntityPosition();

	public void setContainer(IGroup container);

	public boolean isTop();

	public void setTop(boolean top);

	public boolean hasNearDecoration();

	public void setNearDecoration(boolean nearDecoration);

	public int getXposition();

	public void setXposition(int pos);

	public IEntityImage getSvekImage();

	public String getGeneric();

	public void muteToType(LeafType newType, USymbol newSymbol);

	public void setGeneric(String generic);

	public void setSvekImage(IEntityImage svekImage);

	public void setRemoved(boolean removed);

	public void setNeighborhood(Neighborhood neighborhood);

	public Neighborhood getNeighborhood();

}
