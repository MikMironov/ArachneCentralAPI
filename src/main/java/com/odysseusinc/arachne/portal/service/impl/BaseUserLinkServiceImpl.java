/**
 *
 * Copyright 2017 Observational Health Data Sciences and Informatics
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Company: Odysseus Data Services, Inc.
 * Product Owner/Architecture: Gregory Klebanov
 * Authors: Pavel Grafkin, Alexandr Ryabokon, Vitaly Koulakov, Anton Gackovka, Maria Pozhidaeva, Mikhail Mironov
 * Created: September 20, 2017
 *
 */

package com.odysseusinc.arachne.portal.service.impl;

import com.odysseusinc.arachne.portal.model.User;
import com.odysseusinc.arachne.portal.model.UserLink;
import com.odysseusinc.arachne.portal.repository.UserLinkRepository;
import com.odysseusinc.arachne.portal.service.BaseUserLinkService;
import com.odysseusinc.arachne.portal.service.UserLinkService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseUserLinkServiceImpl<U extends User, UL extends UserLink> extends CRUDLServiceImpl<UL> implements BaseUserLinkService<U, UL> {

    @Autowired
    private UserLinkRepository<UL> userLinkRepository;

    @Override
    public CrudRepository<UL, Long> getRepository() {

        return userLinkRepository;
    }

    @Override
    public List<UL> findByUser(U user) {

        return userLinkRepository.findByUser(user);
    }
}
