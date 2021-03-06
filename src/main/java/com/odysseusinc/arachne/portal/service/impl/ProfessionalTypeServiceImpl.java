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
 * Created: October 20, 2016
 *
 */

package com.odysseusinc.arachne.portal.service.impl;

import com.odysseusinc.arachne.portal.exception.NotExistException;
import com.odysseusinc.arachne.portal.exception.NotUniqueException;
import com.odysseusinc.arachne.portal.model.ProfessionalType;
import com.odysseusinc.arachne.portal.repository.ProfessionalTypeRepository;
import com.odysseusinc.arachne.portal.service.ProfessionalTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("unused")
@Transactional(rollbackFor = Exception.class)
public class ProfessionalTypeServiceImpl extends CRUDLServiceImpl<ProfessionalType> implements ProfessionalTypeService {

    @Autowired
    private ProfessionalTypeRepository professionalTypeRepository;

    @Override
    public Class<ProfessionalType> getType() {
        return ProfessionalType.class;
    }

    @Override
    public ProfessionalType create(ProfessionalType professionalType) throws NotUniqueException {

        List<ProfessionalType> professionalTypes = professionalTypeRepository.findByName(professionalType.getName());
        if (!professionalTypes.isEmpty()) {
            throw new NotUniqueException("name", "Not uniquie");
        }
        return professionalTypeRepository.save(professionalType);
    }

    @Override
    public CrudRepository<ProfessionalType, Long> getRepository() {

        return professionalTypeRepository;
    }


    @Override
    public ProfessionalType update(ProfessionalType professionalType) throws NotExistException, NotUniqueException {

        if (!professionalTypeRepository.exists(professionalType.getId())) {
            throw new NotExistException("update: professionalType with id=" + professionalType.getId() + " not exist",
                    ProfessionalType.class);
        }
        List<ProfessionalType> professionalTypes = professionalTypeRepository.findByName(professionalType.getName());
        if (!professionalTypes.isEmpty()) {
            throw new NotUniqueException("name", "Not uniquie");
        }
        return professionalTypeRepository.save(professionalType);
    }


}
