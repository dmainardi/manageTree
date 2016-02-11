/*
 * Copyright (C) 2016 Davide Mainardi <ingmainardi at live.com>
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
package com.dmainardi.manageTree.presentation.tree;

import com.dmainardi.manageTree.business.entity.ExternalNode;
import com.dmainardi.manageTree.business.entity.GroupNode;
import com.dmainardi.manageTree.business.entity.InternalNode;
import com.dmainardi.manageTree.business.entity.Node;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
public class NodeWrapper {
    private final Node node;

    public NodeWrapper(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }
    
    public String getDescription() {
        if (node instanceof GroupNode)
            return ((GroupNode)node).getDescription();
        if (node instanceof ExternalNode)
            return ((ExternalNode)node).getElement().getCode() + " - " + ((ExternalNode)node).getElement().getDescription();
        if (node instanceof InternalNode)
            return ((InternalNode)node).getElement().getName();
        
        return "---";
    }
    
    public String getUnitMeasure() {
        if (node instanceof ExternalNode)
            return ((ExternalNode)node).getElement().getUm().getSymbol();
        if (node instanceof InternalNode)
            return "h";
        
        return "---";
    }
}
