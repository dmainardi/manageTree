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
package com.dmainardi.manageTree.presentation.tree;

import com.dmainardi.manageTree.business.boundary.TreeService;
import com.dmainardi.manageTree.business.entity.Tree;
import java.io.Serializable;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Named
@FlowScoped("tree")
public class TreePresenter implements Serializable {

    @Inject
    TreeService treeService;

    private Tree tree;

    public void deleteTree(Tree tree) {
        treeService.deleteTree(tree);
    }

    public String saveTree() {
        treeService.saveTree(tree);

        return "trees?faces-redirect=true";
    }

    public String detailTree(Long id) {
        if (id == null) {
            tree = new Tree();
        } else {
            tree = treeService.readTree(id);
        }

        return "tree?faces-redirect=true";
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }
}
