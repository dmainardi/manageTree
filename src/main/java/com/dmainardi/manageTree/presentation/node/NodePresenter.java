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
package com.dmainardi.manageTree.presentation.node;

import com.dmainardi.manageTree.business.boundary.NodeService;
import com.dmainardi.manageTree.business.entity.Node;
import java.io.Serializable;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Named
@FlowScoped("node")
public class NodePresenter implements Serializable {

    @Inject
    NodeService nodeService;

    private Node node;

    public void deleteNode(Node node) {
        nodeService.deleteNode(node);
    }

    public String saveNode() {
        nodeService.saveNode(node);

        return "nodes";
    }

    public String detailNode(Long id) {
        if (id == null) {
            node = new Node();
        } else {
            node = nodeService.readNode(id);
        }

        return "node";
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
