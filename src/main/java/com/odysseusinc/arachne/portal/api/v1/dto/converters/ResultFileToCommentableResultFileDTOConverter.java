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
 * Created: May 03, 2017
 *
 */

package com.odysseusinc.arachne.portal.api.v1.dto.converters;

import com.odysseusinc.arachne.portal.api.v1.dto.CommentableResultFileDTO;
import com.odysseusinc.arachne.portal.model.ResultFile;
import org.springframework.beans.factory.annotation.Autowired;
import com.odysseusinc.arachne.portal.api.v1.dto.converters.BaseConversionServiceAwareConverter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;

@Component
public class ResultFileToCommentableResultFileDTOConverter extends BaseConversionServiceAwareConverter<ResultFile, CommentableResultFileDTO> {

    @Override
    public CommentableResultFileDTO convert(ResultFile source) {

        final CommentableResultFileDTO dto = new CommentableResultFileDTO();
        dto.setLabel(source.getLabel());
        dto.setUuid(source.getUuid());
        dto.setName(source.getRealName());
        dto.setCreated(source.getCreated());
        dto.setSubmissionId(source.getSubmission().getId());
        dto.setCommentTopicId(source.getCommentTopic().getId());
        dto.setCommentCount(source.getCommentTopic().getCount());
        dto.setDocType(source.getContentType());
        return dto;
    }
}
