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
 * Created: April 12, 2017
 *
 */

package com.odysseusinc.arachne.portal.api.v1.dto.converters;

import com.odysseusinc.arachne.commons.api.v1.dto.CommonArachneUserDTO;
import com.odysseusinc.arachne.commons.api.v1.dto.CommonArachneUserTypeDTO;
import com.odysseusinc.arachne.portal.model.User;
import com.odysseusinc.arachne.portal.api.v1.dto.converters.BaseConversionServiceAwareConverter;
import org.springframework.stereotype.Component;

/**
 * @author vkoulakov
 * @since 4/12/17.
 */
@Component
public class UserToCommonArachneUserDTOConverter extends BaseConversionServiceAwareConverter<User, CommonArachneUserDTO> {


    @Override
    public CommonArachneUserDTO convert(User user) {

        final CommonArachneUserDTO dto = new CommonArachneUserDTO();
        dto.setFirstName(user.getFirstname());
        dto.setLastName(user.getLastname());
        dto.setLogin(user.getUsername());
        dto.setType(CommonArachneUserTypeDTO.PORTAL);

        return dto;
    }
}
