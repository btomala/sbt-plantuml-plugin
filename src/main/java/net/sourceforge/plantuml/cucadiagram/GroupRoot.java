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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.FontParam;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.Url;
import net.sourceforge.plantuml.cucadiagram.entity.EntityFactory;
import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.USymbol;
import net.sourceforge.plantuml.svek.IEntityImage;
import net.sourceforge.plantuml.svek.PackageStyle;
import net.sourceforge.plantuml.svek.SingleStrategy;
import net.sourceforge.plantuml.ugraphic.UStroke;

public class GroupRoot implements IGroup {

	private final EntityFactory entityFactory;

	public GroupRoot(EntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	public Collection<ILeaf> getLeafsDirect() {
		final List<ILeaf> result = new ArrayList<ILeaf>();
		for (ILeaf ent : entityFactory.getLeafs().values()) {
			if (ent.getParentContainer() == this) {
				result.add(ent);
			}
		}
		return Collections.unmodifiableCollection(result);

	}

	public boolean isGroup() {
		return true;
	}

	public Display getDisplay() {
		throw new UnsupportedOperationException();

	}

	public void setDisplay(Display display) {
		throw new UnsupportedOperationException();

	}

	public LeafType getEntityType() {
		throw new UnsupportedOperationException();
	}

	public String getUid() {
		throw new UnsupportedOperationException();

	}

	public Url getUrl99() {
		return null;

	}

	public Stereotype getStereotype() {
		throw new UnsupportedOperationException();

	}

	public void setStereotype(Stereotype stereotype) {
		throw new UnsupportedOperationException();

	}

	public TextBlock getBody(PortionShower portionShower, FontParam fontParam, ISkinParam skinParam) {
		throw new UnsupportedOperationException();

	}

	public Code getCode() {
		return Code.of("__ROOT__");
	}

	public LongCode getLongCode() {
		return null;
	}

	public void addUrl(Url url) {
		throw new UnsupportedOperationException();

	}

	public HtmlColor getSpecificBackColor() {
		throw new UnsupportedOperationException();

	}

	public void setSpecificBackcolor(HtmlColor specificBackcolor) {
		throw new UnsupportedOperationException();

	}

	public IGroup getParentContainer() {
		return null;
	}

	public boolean containsLeafRecurse(ILeaf entity) {
		throw new UnsupportedOperationException();

	}

	public Collection<IGroup> getChildren() {
		final List<IGroup> result = new ArrayList<IGroup>();
		for (IGroup ent : entityFactory.getGroups().values()) {
			if (ent.getParentContainer() == this) {
				result.add(ent);
			}
		}
		return Collections.unmodifiableCollection(result);
	}

	public void moveEntitiesTo(IGroup dest) {
		throw new UnsupportedOperationException();
	}

	public int size() {
		throw new UnsupportedOperationException();
	}

	public GroupType getGroupType() {
		return null;
	}

	public Code getNamespace2() {
		throw new UnsupportedOperationException();

	}

	public boolean isAutonom() {
		throw new UnsupportedOperationException();

	}

	public void setAutonom(boolean autonom) {
		throw new UnsupportedOperationException();

	}

	public PackageStyle getPackageStyle() {
		throw new UnsupportedOperationException();

	}

	public void overideImage(IEntityImage img, LeafType state) {
		throw new UnsupportedOperationException();
	}

	public boolean isHidden() {
		return false;
	}

	public USymbol getUSymbol() {
		return null;
		// throw new UnsupportedOperationException();
	}

	public void setUSymbol(USymbol symbol) {
		throw new UnsupportedOperationException();
	}

	public SingleStrategy getSingleStrategy() {
		return SingleStrategy.SQUARRE;
	}

	public boolean isRemoved() {
		return false;
	}

	public HtmlColor getSpecificLineColor() {
		return null;
	}

	public void setSpecificLineColor(HtmlColor specificLinecolor) {
		throw new UnsupportedOperationException();
	}

	public UStroke getSpecificLineStroke() {
		return null;
	}

	public void setSpecificLineStroke(UStroke specificLineStoke) {
		throw new UnsupportedOperationException();
	}

	public boolean hasUrl() {
		return false;
	}

	public int getHectorLayer() {
		throw new UnsupportedOperationException();
	}

	public void setHectorLayer(int layer) {
		throw new UnsupportedOperationException();
	}

	public FontParam getTitleFontParam() {
		throw new UnsupportedOperationException();
	}

	public int getRawLayout() {
		throw new UnsupportedOperationException();
	}

	public char getConcurrentSeparator() {
		throw new UnsupportedOperationException();
	}

	public void setConcurrentSeparator(char separator) {
		throw new UnsupportedOperationException();
	}

	public void putTip(String member, Display display) {
		throw new UnsupportedOperationException();
	}

	public Map<String, Display> getTips() {
		throw new UnsupportedOperationException();
	}

	public Bodier getBodier() {
		throw new UnsupportedOperationException();
	}

}
