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
import com.dmainardi.manageTree.business.entity.InternalElement;
import com.dmainardi.manageTree.business.entity.InternalNode;
import com.dmainardi.manageTree.business.entity.Node;
import com.dmainardi.manageTree.business.entity.Tree;
import java.io.Serializable;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
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

    public enum NodeType {
        GROUP_NODE,
        INTERNAL_NODE,
        EXTERNAL_NODE
    }

    private Tree tree;
    private Node node;
    private TreeNode root;
    private TreeNode selectedNode;
    private InternalElement selectedInternalElement;

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
        populateTree();

        return "tree?faces-redirect=true";
    }

    private void populateTree() {
        root = new DefaultTreeNode("group", tree.getRoot(), null);
        if (tree.getRoot().getChildren() != null) {
            for (Node node : tree.getRoot().getChildren()) {
                populateTreeNodes(root, node);
            }
        }
    }

    private void populateTreeNodes(TreeNode parent, Node current) {
        String nodeType = "unknown";
        if (current instanceof GroupNode) {
            nodeType = "grp";
        }
        if (current instanceof ExternalNode) {
            nodeType = "ext";
        }
        if (current instanceof InternalNode) {
            nodeType = "int";
        }

        TreeNode nodeTemp = new DefaultTreeNode(nodeType, current, parent);

        if (current.getChildren() != null) {
            for (Node node : current.getChildren()) {
                populateTreeNodes(nodeTemp, node);
            }
        }
    }

    public void deleteSelectedNode() {
        treeService.deleteNode((Node) selectedNode.getData(), tree.getRoot());
        selectedNode.getChildren().clear();
        selectedNode.getParent().getChildren().remove(selectedNode);
        selectedNode.setParent(null);

        selectedNode = null;
    }

    public String addNode(NodeType nodeType) {
        switch (nodeType) {
            case EXTERNAL_NODE:
                node = new ExternalNode();
                return "externalNode?faces-redirect=true";
            case GROUP_NODE:
                node = new GroupNode();
                return "groupNode?faces-redirect=true";
            case INTERNAL_NODE:
                node = new InternalNode();
                return "internalNode?faces-redirect=true";
            default:
                return null;
        }
    }

    public String insertIntoTree() {
        TreeNode father;
        if (selectedNode == null) {
            //root will be the father
            node.setFather(tree.getRoot());
            tree.getRoot().getChildren().add(node);
            father = root;
        } else if (!selectedNode.getType().equalsIgnoreCase("grp")) {
            //selectedNode's father will be the father
            node.setFather(((Node)selectedNode.getData()).getFather());
            ((Node)selectedNode.getData()).getFather().getChildren().add(node);
            father = selectedNode.getParent();
        } else {
            //selectedNode will be the father
            node.setFather((Node)selectedNode.getData());
            ((Node)selectedNode.getData()).getChildren().add(node);
            father = selectedNode;
        }
        populateTreeNodes(father, node);
        return "tree?faces-redirect=true";
    }
    
    public void onInternalElementSelect(SelectEvent event) {
        ((InternalNode)node).setElement((InternalElement) event.getObject());
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

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public InternalElement getSelectedInternalElement() {
        return selectedInternalElement;
    }

    public void setSelectedInternalElement(InternalElement selectedInternalElement) {
        this.selectedInternalElement = selectedInternalElement;
    }
    
}
