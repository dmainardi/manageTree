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
package com.dmainardi.manageTree.presentation.internalElement;

import com.dmainardi.manageTree.business.boundary.InternalElementService;
import com.dmainardi.manageTree.business.entity.InternalElement;
import java.io.Serializable;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Named
@FlowScoped("internalElement")
public class InternalElementPresenter implements Serializable {
    @Inject
    InternalElementService internalElementService;
    
    private InternalElement internalElement;
        
    public void deleteInternalElement(InternalElement internalElement) {
        internalElementService.deleteInternalElement(internalElement);
    }
    
    public String saveInternalElement() {
        internalElementService.saveInternalElement(internalElement);
        
        return "internalElements?faces-redirect=true";
    }
    
    public String detailInternalElement(Long id) {
        if (id == null)
            internalElement = new InternalElement();
        else
            internalElement = internalElementService.readInternalElement(id);
        
        return "internalElement?faces-redirect=true";
    }

    public InternalElement getInternalElement() {
        return internalElement;
    }

    public void setInternalElement(InternalElement internalElement) {
        this.internalElement = internalElement;
    }
}
