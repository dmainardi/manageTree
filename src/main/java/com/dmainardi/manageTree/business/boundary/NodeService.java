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
package com.dmainardi.manageTree.business.boundary;

import com.dmainardi.manageTree.business.entity.GroupNode;
import com.dmainardi.manageTree.business.entity.Node;
import com.dmainardi.manageTree.business.entity.Node_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Stateless
public class NodeService {
    @PersistenceContext
    EntityManager em;
    
    public Node saveNode(Node node) {
        if (node.getId() == null)
            em.persist(node);
        else
            return em.merge(node);
        
        return null;
    }
    
    public Node readNode(Long id) {
        return em.find(Node.class, id);
    }
    
    public void deleteNode(Node node) {
        em.remove(em.merge(node));
    }

    /**
     * Only lists root ("father is null") nodes
     * @return List of root nodes
     */
    public List<GroupNode> listNodes() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GroupNode> query = cb.createQuery(GroupNode.class);
        Root<GroupNode> root = query.from(GroupNode.class);
        query.select(root);
        
        List<Predicate> conditions = new ArrayList<>();
        conditions.add(cb.isNull(root.get(Node_.father)));
        if (!conditions.isEmpty())
            query.where(conditions.toArray(new Predicate[conditions.size()]));
        
        return em.createQuery(query).getResultList();
    }
}
