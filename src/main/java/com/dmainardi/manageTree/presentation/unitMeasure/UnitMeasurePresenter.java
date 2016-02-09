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
package com.dmainardi.manageTree.presentation.unitMeasure;

import com.dmainardi.manageTree.business.boundary.UnitMeasureService;
import com.dmainardi.manageTree.business.entity.UnitMeasure;
import java.io.Serializable;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Named
@FlowScoped("unitMeasure")
public class UnitMeasurePresenter implements Serializable {
    @Inject
    UnitMeasureService unitMeasureService;
    
    private UnitMeasure unitMeasure;
        
    public void deleteUnitMeasure(UnitMeasure unitMeasure) {
        unitMeasureService.deleteUnitMeasure(unitMeasure);
    }
    
    public String saveUnitMeasure() {
        unitMeasureService.saveUnitMeasure(unitMeasure);
        
        return "unitsMeasure?faces-redirect=true";
    }
    
    public String detailUnitMeasure(Long id) {
        if (id == null)
            unitMeasure = new UnitMeasure();
        else
            unitMeasure = unitMeasureService.readUnitMeasure(id);
        
        return "unitMeasure?faces-redirect=true";
    }

    public UnitMeasure getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(UnitMeasure unitMeasure) {
        this.unitMeasure = unitMeasure;
    }
}
