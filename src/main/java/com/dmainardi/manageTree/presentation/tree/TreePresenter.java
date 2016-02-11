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
import com.dmainardi.manageTree.business.entity.ExternalNode;
import com.dmainardi.manageTree.business.entity.GroupNode;
import com.dmainardi.manageTree.business.entity.InternalNode;
import com.dmainardi.manageTree.business.entity.Node;
import com.dmainardi.manageTree.business.entity.Tree;
import java.io.Serializable;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

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
    private TreeNode root;

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
            populateTree();
        }

        return "tree?faces-redirect=true";
    }
    
    private void populateTree() {
        root = new DefaultTreeNode("group", new NodeWrapper(tree.getRoot()), null);
        if (tree.getRoot().getChildren() != null)
            for (Node node : tree.getRoot().getChildren())
                populateTreeNodes(root, node);
    }
    
    private void populateTreeNodes(TreeNode parent, Node current) {
        String nodeType = "unknown";
        if (current instanceof GroupNode)
            nodeType = "grp";
        if (current instanceof ExternalNode)
            nodeType = "ext";
        if (current instanceof InternalNode)
            nodeType = "int";
        
        TreeNode nodeTemp = new DefaultTreeNode(nodeType, new NodeWrapper(current), parent);
        
        if (current.getChildren() != null)
            for (Node node : current.getChildren())
                populateTreeNodes(nodeTemp, node);
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
