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
 * Created: September 11, 2017
 *
 */

package com.odysseusinc.arachne.portal.api.v1.dto.converters.study;

import com.odysseusinc.arachne.portal.api.v1.dto.PermissionsDTO;
import com.odysseusinc.arachne.portal.api.v1.dto.ShortUserDTO;
import com.odysseusinc.arachne.portal.api.v1.dto.StudyListDTO;
import com.odysseusinc.arachne.portal.api.v1.dto.converters.BaseConversionServiceAwareConverter;
import com.odysseusinc.arachne.portal.api.v1.dto.dictionary.StudyStatusDTO;
import com.odysseusinc.arachne.portal.api.v1.dto.dictionary.StudyTypeDTO;
import com.odysseusinc.arachne.portal.model.AbstractUserStudyListItem;
import com.odysseusinc.arachne.portal.model.ParticipantRole;
import com.odysseusinc.arachne.portal.model.Study;
import com.odysseusinc.arachne.portal.model.User;
import com.odysseusinc.arachne.portal.service.StudyService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.GenericConversionService;

public abstract class BaseUserStudyListItemToStudyListDTOConverter<DTO extends StudyListDTO> extends BaseConversionServiceAwareConverter<AbstractUserStudyListItem, DTO> {

    @Autowired
    private StudyService studyService;

    @Override
    public DTO convert(AbstractUserStudyListItem userStudyLink) {

        DTO studyDTO = createResultObject();
        Study source = userStudyLink.getStudy();
        List<User> studyLeadList = studyService.findLeads(source);

        studyDTO.setStatus(conversionService.convert(source.getStatus(), StudyStatusDTO.class));
        studyDTO.setTitle(source.getTitle());
        studyDTO.setType(conversionService.convert(source.getType(), StudyTypeDTO.class));
        studyDTO.setEndDate(source.getEndDate());
        studyDTO.setStartDate(source.getStartDate());
        studyDTO.setDescription(source.getDescription());
        studyDTO.setUpdated(source.getUpdated());
        studyDTO.setCreated(source.getCreated());
        studyDTO.setId(source.getId());
        StringBuilder stringBuilder = new StringBuilder();
        for (String participantRole : userStudyLink.getRole() != null
                ? userStudyLink.getRole().split(",")
                : new String[]{}) {
            stringBuilder.append(ParticipantRole.valueOf(participantRole).toString()).append(", ");
        }
        if (stringBuilder.length() > 1) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            studyDTO.setRole(stringBuilder.toString());
        }
        studyDTO.setPermissions(conversionService.convert(source, PermissionsDTO.class));
        studyDTO.setFavourite(userStudyLink.getFavourite());
        studyDTO.setLeadList(
                studyLeadList.stream()
                        .map(studyLead -> conversionService.convert(studyLead, ShortUserDTO.class))
                        .collect(Collectors.toList())
        );

        proceedAdditionalFields(studyDTO, userStudyLink);

        return studyDTO;
    }


}
