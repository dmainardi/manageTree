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
package com.dmainardi.manageTree.presentation.externalElement;

import com.dmainardi.manageTree.business.boundary.ExternalElementService;
import com.dmainardi.manageTree.business.entity.ExternalElement;
import java.io.Serializable;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Named
@FlowScoped("externalElement")
public class ExternalElementPresenter implements Serializable {

    @Inject
    ExternalElementService externalElementService;

    private ExternalElement externalElement;

    public void deleteExternalElement(ExternalElement externalElement) {
        externalElementService.deleteExternalElement(externalElement);
    }

    public String saveExternalElement() {
        externalElementService.saveExternalElement(externalElement);

        return "externalElements?faces-redirect=true";
    }

    public String detailExternalElement(Long id) {
        if (id == null) {
            externalElement = new ExternalElement();
        } else {
            externalElement = externalElementService.readExternalElement(id);
        }

        return "externalElement?faces-redirect=true";
    }

    public ExternalElement getExternalElement() {
        return externalElement;
    }

    public void setExternalElement(ExternalElement externalElement) {
        this.externalElement = externalElement;
    }
}
