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
 * Created: April 20, 2017
 *
 */

package com.odysseusinc.arachne.portal.api.v1.dto.converters;

import com.odysseusinc.arachne.portal.api.v1.dto.CommentDTO;
import com.odysseusinc.arachne.portal.api.v1.dto.CommentTopicDTO;
import com.odysseusinc.arachne.portal.model.CommentTopic;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import com.odysseusinc.arachne.portal.api.v1.dto.converters.BaseConversionServiceAwareConverter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;

@Component
public class CommentTopicToCommentTopicDTOConverter extends BaseConversionServiceAwareConverter<CommentTopic, CommentTopicDTO> {

    @Override
    public CommentTopicDTO convert(CommentTopic source) {

        final CommentTopicDTO commentTopicDTO = new CommentTopicDTO();
        final List<CommentDTO> commentDTOS = source.getComments()
                .stream()
                .map(c -> conversionService.convert(c, CommentDTO.class))
                .collect(Collectors.toList());
        commentTopicDTO.setId(source.getId());
        commentTopicDTO.setComments(commentDTOS);
        return commentTopicDTO;
    }
}
