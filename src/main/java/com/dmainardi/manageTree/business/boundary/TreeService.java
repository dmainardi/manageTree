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
package com.dmainardi.manageTree.business.boundary;

import com.dmainardi.manageTree.business.entity.Tree;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Stateless
public class TreeService {
    @PersistenceContext
    EntityManager em;
    
    public Tree saveTree(Tree tree) {
        if (tree.getId() == null)
            em.persist(tree);
        else
            return em.merge(tree);
        
        return null;
    }
    
    public Tree readTree(Long id) {
        return em.find(Tree.class, id);
    }
    
    public void deleteTree(Tree tree) {
        em.remove(em.merge(tree));
    }

    public List<Tree> listTrees() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tree> query = cb.createQuery(Tree.class);
        Root<Tree> root = query.from(Tree.class);
        query.select(root);
        
        return em.createQuery(query).getResultList();
    }
}
