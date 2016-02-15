/*
 * Copyright (C) 2015 Davide Mainardi <ingmainardi at live.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dmainardi.manageTree.business.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Entity
@DiscriminatorValue(value = "ext")
public class ExternalNode extends Node {

    @ManyToOne(optional = false)
    @NotNull
    private ExternalElement element;

    @Override
    public String getDescription() {
        return element.getCode() + " - " + element.getDescription();
    }

    @Override
    public String getUnitMeasure() {
        return element.getUm().getSymbol();
    }

    public ExternalElement getElement() {
        return element;
    }

    public void setElement(ExternalElement element) {
        this.element = element;
    }
}
