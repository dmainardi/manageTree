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

import com.dmainardi.manageTree.business.entity.InternalElement;
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
public class InternalElementService {
    @PersistenceContext
    EntityManager em;
    
    public InternalElement saveInternalElement(InternalElement internalElement) {
        if (internalElement.getId() == null)
            em.persist(internalElement);
        else
            return em.merge(internalElement);
        
        return null;
    }
    
    public InternalElement readInternalElement(Long id) {
        return em.find(InternalElement.class, id);
    }
    
    public void deleteInternalElement(InternalElement internalElement) {
        em.remove(em.merge(internalElement));
    }

    public List<InternalElement> listInternalElements() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<InternalElement> query = cb.createQuery(InternalElement.class);
        Root<InternalElement> root = query.from(InternalElement.class);
        query.select(root);
        return em.createQuery(query).getResultList();
    }
}
